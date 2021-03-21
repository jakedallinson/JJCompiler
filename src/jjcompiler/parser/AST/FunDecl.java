package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public class FunDecl extends Decl {

    private Token IDToken;
    private Expression paramsExpression;
    private Statement compoundStatement;

    public FunDecl (Token type, Token ID, Expression expr, Statement stmt) {
        typeToken = type;
        IDToken = ID;
        paramsExpression = expr;
        compoundStatement = stmt;
    }

    public String printTree () {

        StringBuilder print = new StringBuilder("Program" + "\n");

        print.append("     ").append(typeToken.printToken());
        print.append("     ").append(IDToken.printToken()).append("\n");
        print.append("     ").append(paramsExpression.printTree()).append("\n");
        print.append("     ").append(compoundStatement.printTree()).append("\n");

        return print.toString();
    }
}
