package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public class NumExpression extends Expression {

    private Token numToken;

    public NumExpression(Token token) {
        numToken = token;
    }

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append("NUMEXPR: ").append(numToken.printTokenData());
        return print.toString();
    }
}
