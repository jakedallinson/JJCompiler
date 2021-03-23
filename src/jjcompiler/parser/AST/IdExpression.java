package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public class IdExpression extends Expression{

    private Token IDToken;

    public IdExpression(Token token) {
        IDToken = token;
    }

    @Override
    public String printTree(String indent) {
        indent += "   ";
        StringBuilder print = new StringBuilder();
        print.append(indent).append("IDEXPRESSION").append('\n');

        return print.toString();
    }
}
