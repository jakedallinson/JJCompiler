package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public class IdExpression extends Expression{

    private Token IDToken;
    private Expression arrIndexExpr;

    public IdExpression(Token token, Expression expr) {
        IDToken = token;
        arrIndexExpr = expr;
    }

    @Override
    public String printTree(String indent) {
        indent += "   ";
        StringBuilder print = new StringBuilder();
        print.append(indent).append("IDEXPRESSION").append('\n');

        return print.toString();
    }
}
