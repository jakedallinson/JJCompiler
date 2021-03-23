package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public class IdExpression extends Expression{

    private Token IDToken;

    public IdExpression(Token token) {
        IDToken = token;
    }


    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append(IDToken.printTokenData());
        return print.toString();
    }
}
