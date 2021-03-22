package jjcompiler.parser;

import jjcompiler.parser.AST.*;
import jjcompiler.scanner.CMinusScanner;
import jjcompiler.scanner.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static jjcompiler.scanner.Token.TokenType;

public class CMinusParser implements Parser {

    private final CMinusScanner scanner;
    private Token currentToken;

    public CMinusParser (BufferedReader file) throws IOException {
        scanner = new CMinusScanner(file);
        currentToken = scanner.viewNextToken();
    }

    public Token advanceToken () throws IOException {
        scanner.getNextToken();
        currentToken = scanner.viewNextToken();
        return currentToken;
    }

    public Token matchToken (TokenType expectedType) throws CMinusParserException, IOException {
        if (currentToken.getType() == expectedType) {
            Token oldToken = currentToken;
            advanceToken();
            return oldToken;
        } else {
            throw new CMinusParserException("parseDecl", expectedType, currentToken.getType());
        }
    }

    public Program parse () throws IOException, CMinusParserException {
        Program program = new Program();

        do {
            Decl decl = parseDecl();
            program.addDecl(decl);
        } while (scanner.viewNextToken().getType() != TokenType.ENDFILE);
        return program;
    }

    // ***********************
    // PARSE FUNCTS
    // ***********************

    /**
     * decl
     */
    private Decl parseDecl () throws CMinusParserException, IOException {
        if (currentToken.getType() == TokenType.VOID) {
            Token typeToken = currentToken;
            advanceToken();
            Token IDToken = matchToken(TokenType.ID);
            return parseFunDeclPrime(typeToken, IDToken);
        } else if (currentToken.getType() == TokenType.INT) {
            Token typeToken = currentToken;
            advanceToken();
            Token IDToken = matchToken(TokenType.ID);
            return parseDeclPrime(typeToken, IDToken);
        } else {
            throw new CMinusParserException("parseDecl", TokenType.VOID, currentToken.getType());
        }
    }

    /**
     * decl'
     */
    private Decl parseDeclPrime (Token typeToken, Token IDToken) throws CMinusParserException, IOException {
        if (currentToken.getType() == TokenType.SEMI) {
            advanceToken();
            Token indexToken = null;
            return new VarDecl(typeToken, IDToken, indexToken);
        } else if (currentToken.getType() == TokenType.LBRACKET) {
            advanceToken();
            Token indexToken = matchToken(TokenType.NUM);
            matchToken(TokenType.RBRACKET);
            matchToken(TokenType.SEMI);
            return new VarDecl(typeToken, IDToken, indexToken);
        } else {
            return parseFunDeclPrime(typeToken, IDToken);
        }
    }

    /**
     * fun-decl'
     */
    private Decl parseFunDeclPrime (Token typeToken, Token IDToken) throws CMinusParserException, IOException {
        matchToken(TokenType.LPAREN);
        ArrayList<VarDecl> params = parseParams();
        matchToken(TokenType.RPAREN);
        Statement compoundStmt = parseCompoundStatement();
        return new FunDecl(typeToken, IDToken, params, compoundStmt);
    }

    /**
     * params
     */
    private ArrayList<VarDecl> parseParams () throws IOException, CMinusParserException {
        ArrayList<VarDecl> params = new ArrayList<>();
        if (currentToken.getType() == TokenType.VOID) {
            Token paramTypeToken = currentToken;
            params.add(new VarDecl(paramTypeToken,null, null));
            advanceToken();
        } else if (currentToken.getType() == TokenType.INT) {
            VarDecl param = parseParam();
            params.add(param);
            while (currentToken.getType() == TokenType.COMMA) {
                advanceToken();
                param = parseParam();
                params.add(param);
            }
        } else {
            throw new CMinusParserException("parseParams", TokenType.VOID, currentToken.getType());
        }
        return params;
    }

    /**
     * param
     */
    private VarDecl parseParam () throws IOException, CMinusParserException {
        Token paramTypeToken = currentToken;
        advanceToken();
        Token paramIDToken = matchToken(TokenType.ID);
        Token paramIndexToken = null;
        if (currentToken.getType() == TokenType.LBRACKET) {
            advanceToken();
            paramIndexToken = matchToken(TokenType.NUM);
            matchToken(TokenType.RBRACKET);
        }
        return new VarDecl(paramTypeToken,paramIDToken, paramIndexToken);
    }

    /**
     * compound-stmt
     */
    private Statement parseCompoundStatement () throws IOException, CMinusParserException {
        matchToken(TokenType.LCURLY);
        CompoundStatement compoundStmt = new CompoundStatement();
        // parse decls
        while (currentToken.getType() == TokenType.VOID || currentToken.getType() == TokenType.INT) {
            Decl decl = parseDecl();
            compoundStmt.addDecl(decl);
        }
        // parse stmts
        while (currentToken.getType() == TokenType.SEMI ||
                currentToken.getType() == TokenType.NUM ||
                currentToken.getType() == TokenType.LPAREN ||
                currentToken.getType() == TokenType.ID ||
                currentToken.getType() == TokenType.LCURLY ||
                currentToken.getType() == TokenType.IF ||
                currentToken.getType() == TokenType.WHILE ||
                currentToken.getType() == TokenType.RETURN) {
            Statement stmt = parseStatement();
            compoundStmt.addStmt(stmt);
        }
        matchToken(TokenType.RCURLY);
        return compoundStmt;
    }

    /**
     * stmt
     */
    private Statement parseStatement () throws IOException, CMinusParserException {
        if (currentToken.getType() == TokenType.SEMI ||
                currentToken.getType() == TokenType.NUM ||
                currentToken.getType() == TokenType.LPAREN ||
                currentToken.getType() == TokenType.ID) {
            return parseExpressionStatement();
        } else if (currentToken.getType() == TokenType.LCURLY) {
            return parseCompoundStatement();
        } else if (currentToken.getType() == TokenType.IF) {
            return parseSelectionStatement();
        } else if (currentToken.getType() == TokenType.WHILE) {
            return parseIterationStatement();
        } else if (currentToken.getType() == TokenType.RETURN) {
            return parseReturnStatement();
        } else {
            throw new CMinusParserException("parseStatement", TokenType.IF, currentToken.getType());
        }
    }

    /**
     * expr-stmt
     */
    private Statement parseExpressionStatement () throws IOException, CMinusParserException {
        Expression expr = null;
        if (currentToken.getType() == TokenType.NUM ||
                currentToken.getType() == TokenType.LPAREN ||
                currentToken.getType() == TokenType.ID) {
            expr = parseExpression();
        }
        matchToken(TokenType.SEMI);
        return new ExpressionStatement(expr);
    }

    /**
     * selection-stmt
     */
    private Statement parseSelectionStatement () throws IOException, CMinusParserException {
        matchToken(TokenType.IF);
        matchToken(TokenType.LPAREN);
        Expression ifExpr = parseExpression();
        matchToken(TokenType.RPAREN);
        Statement thenStmt = parseStatement();
        Statement elseStmt = null;

        if (currentToken.getType() == TokenType.ELSE) {
            advanceToken();
            elseStmt = parseStatement();
        }
        return new SelectionStatement(ifExpr, thenStmt, elseStmt);
    }

    /**
     * iteration-stmt
     */
    private Statement parseIterationStatement () throws IOException, CMinusParserException {
        matchToken(TokenType.WHILE);
        matchToken(TokenType.LPAREN);
        Expression paramsExpr = parseExpression();
        matchToken(TokenType.RPAREN);
        Statement loopStmt = parseStatement();
        return new IterationStatement(paramsExpr, loopStmt);
    }

    /**
     * return-stmt
     */
    private Statement parseReturnStatement () throws IOException, CMinusParserException {
        matchToken(TokenType.RETURN);
        Expression returnExpr = null;
        if (currentToken.getType() == TokenType.NUM ||
                currentToken.getType() == TokenType.LPAREN ||
                currentToken.getType() == TokenType.ID) {
            returnExpr = parseExpression();
        }
        matchToken(TokenType.SEMI);
        return new ReturnStatement(returnExpr);
    }

    /**
     * parse-expression
     */
    private Expression parseExpression() throws IOException {

//        FIRST(expr) = { NUM ( ID }
        return new Expression();
    }
//
//    /**
//     * parse-expression-prime
//     */
//    private Expression parseExpressionPrime() throws IOException {
////        FIRST(expr’) = { = [ ( * / 𝜀 }
//
//    }
//
//    /**
//     * parse-expression-double-prime
//     */
//    private Expression parseExpressionDoublePrime() throws IOException {
////        FIRST(expr’’) = { = * / 𝜀 }
//
//    }
//
//    /**
//     * parse-simple-expression
//     */
//    private Expression parseSimpleExpression() throws IOException, CMinusParserException {
//
////        FIRST(simple-expr’) = { * / 𝜀 }
//        Token oldToken;
//        Expression lhs = parseAdditiveExpression();
//
//        while(isRelOp(currentToken.getType())) {
//            oldToken = advanceToken();
//            Expression rhs = parseAdditiveExpression();
//            lhs = createBinopExpr(oldToken.getType(), lhs, rhs);
//        }
//        return lhs;
//    }
//
//    /**
//     * parse-additive-expression
//     */
//    private Expression parseAdditiveExpression() throws IOException, CMinusParserException {
//        Token oldToken;
//        Expression lhs = parseTerm();
//
//        while(isAddOp(currentToken.getType())) {
//            oldToken = advanceToken();
//            Expression rhs = parseTerm();
//            lhs = createBinopExpr(oldToken.getType(), lhs, rhs);
//        }
//        return lhs;
//    }
//
//    /**
//     * parse-term
//     */
//    private Expression parseTerm() throws IOException, CMinusParserException {
//        Token oldToken;
//        Expression lhs = parseFactor();
//
//        while(isMulOp(currentToken.getType())) {
//            oldToken = advanceToken();
//            Expression rhs = parseFactor();
//            lhs = createBinopExpr(oldToken.getType(), lhs, rhs);
//        }
//        return lhs;
//    }
//
//    /**
//     * parse-factor
//     */
//    private Expression parseFactor() throws IOException, CMinusParserException {
//        Token oldToken;
//
//       if (currentToken.getType() == TokenType.LPAREN) {
//           advanceToken();
//           Expression returnExpr = parseExpression();
//           matchToken(TokenType.RPAREN);
//           return returnExpr;
//
//       } else if (currentToken.getType() == TokenType.ID) {
//           oldToken = advanceToken();
//           return createIdentExpr(oldToken);
//
//       } else if (currentToken.getType() ==TokenType.NUM) {
//           oldToken = advanceToken();
//           return createNumExpr(oldToken);
//
//       } else {
//           throw new IllegalStateException("Unexpected value: " + currentToken.getType());
//       }
//    }
//
//    private boolean isAddOp(TokenType type){
//        return type == TokenType.PLUS || type == TokenType.MINUS;
//    }
//
//    private boolean isMulOp(TokenType type){
//        return type == TokenType.TIMES || type == TokenType.DIVIDE;
//    }
//
//    private boolean isRelOp(TokenType type){
//        return type == TokenType.GT || type == TokenType.LT || type == TokenType.GTEQ || type == TokenType.LTEQ || type == TokenType.EQ;
//    }
//
//    /**
//     * create-binop-expression
//     */
//    private Expression createBinopExpr (TokenType type, Expression lhs, Expression rhs) {
//
//        Expression binop = new BinaryOpExpression(type, lhs, rhs);
//        return binop;
//    }
//
//    /**
//     * create-indent-expression
//     */
//    private Expression createIdentExpr (Token token) {
//
//
//    }
//
//    /**
//     * create-num-expression
//     */
//    private Expression createNumExpr (Token token) {
//
//
//    }

}

