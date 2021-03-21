package jjcompiler.parser;

import jjcompiler.parser.AST.*;
import jjcompiler.scanner.CMinusScanner;
import jjcompiler.scanner.Token;

import java.io.BufferedReader;
import java.io.IOException;

import static jjcompiler.scanner.Token.TokenType;

public class CMinusParser implements Parser {

    private final CMinusScanner scanner;
    private Token currentToken;

    public CMinusParser(BufferedReader file) throws IOException {
        scanner = new CMinusScanner(file);
    }

    public Token advanceToken() throws IOException {
        return scanner.getNextToken();
    }

    public void matchToken(TokenType expected) throws CMinusParserException, IOException {
        if (currentToken.getType() == expected) {
            advanceToken();
        } else {
            throw new CMinusParserException("PARSE ERROR: Expected Token " + expected + ", got " + currentToken.getType());
        }
    }

    public Program parse() throws IOException, CMinusParserException {
        Program program = new Program();

        // parse decls into program until EOF is reached
        while (scanner.viewNextToken().getType() != TokenType.ENDFILE) {
            Decl decl = parseDecl();
            program.addDecl(decl);
        }
        return program;
    }

    //
    // PARSE FUNCTS
    //

    private Decl parseDecl() throws CMinusParserException, IOException {
        if (currentToken.getType() == TokenType.VOID) {
            Token typeToken = advanceToken();
            Token IDToken = advanceToken();
            Decl funDeclPrime = ParseFunDeclPrime(typeToken, IDToken);
            return funDeclPrime;
        } else if (currentToken.getType() == TokenType.INT) {
            Token typeToken = advanceToken();
            Token IDToken = advanceToken();
            Decl declPrime = ParseDeclPrime(typeToken, IDToken);
            return declPrime;
        } else if (currentToken.getType() == TokenType.LPAREN) {
            // fun-decl'
        } else {
            throw new CMinusParserException("PARSE ERROR");
        }
    }

    private Decl ParseDeclPrime(Token typeToken, Token IDToken) throws CMinusParserException, IOException {
        if (currentToken.getType() == TokenType.SEMI) {
            return new VarDecl(typeToken, IDToken);
        } else if (currentToken.getType() == TokenType.LBRACKET) {

        } else {
            Decl funDeclPrime = ParseFunDeclPrime(typeToken, IDToken);
            return funDeclPrime;
        }
    }

    private Decl ParseFunDeclPrime (Token typeToken, Token IDToken) throws CMinusParserException, IOException {
//        matchToken(TokenType.LPAREN);
//        Expression paramsExpr = parseExpression();
//        matchToken(TokenType.LPAREN);
//        Statement compoundStmt = parseStatement();
        return null;
    }


//    private Expression parseExpression () throws IOException {
//        Token oldToken;
//        Expression lhs = parseTerm();
//
//        while(isAddop(currentToken.getType())) {
//            oldToken = advanceToken();
//            Expression rhs = parseTerm();
//            lhs = createBinopExpr(oldToken.getType(), lhs, rhs);
//        }
//        return lhs;
//    }
//
//    private Expression parseFactor() throws IOException, CMinusParserException {
//        Token oldToken;
//
//        switch (currentToken.getType()) {
//            case TokenType.LPAREN:
//                advanceToken();
//                Expression returnExpr = parseExpression();
//                matchToken(TokenType.RPAREN);
//                return returnExpr;
//            case TokenType.ID:
//                oldToken = advanceToken();
//                return createIdentExpr(oldToken);
//                break;
//            case TokenType.NUM:
//                oldToken = advanceToken();
//                return createNumExpr(oldToken);
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + currentToken.getType());
//        }
//    }
//
//
//
//    private Statement parseIfStmt () throws IOException, CMinusParserException {
//        matchToken(TokenType.IF);
//        matchToken(TokenType.LPAREN);
//        Expression ifExpr = parseExpression();
//        matchToken(TokenType.RPAREN);
//        Statement thenStmt = parseStatement();
//        Statement elseStmt = null;
//
//        if (currentToken.getType() == TokenType.ELSE) {
//            advanceToken();
//            elseStmt = parseStatement();
//        }
//
//        Statement returnStmt = new SelectionStatement(ifExpr, thenStmt, elseStmt);
//        return returnStmt;
//    }
//    private Statement parseStatement() {
//
//        switch (currentToken.getType()) {
//
//        }
//
//        Statement returnStmt = new Statement(stmt1, stmt2);
//        return returnStmt;
//    }
//    private Expression createBinopExpr (TokenType type, Token lhs, Token rhs) {}
//    private Expression createIdentExpr (Token token) {}
//    private Expression createNumExpr (Token token) {}

}

