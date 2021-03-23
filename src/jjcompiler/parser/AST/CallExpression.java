package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

import java.util.ArrayList;

public class CallExpression extends Expression {

    private Token IDToken;
    private ArrayList<Expression> argList;

    public CallExpression(Token token, ArrayList<Expression> args) {
        IDToken = token;
        argList = args;
    }

    @Override
    public String printTree(String indent) {
        indent += "   ";
        StringBuilder print = new StringBuilder();
        print.append(indent).append("CALLEXPRESSION").append('\n');

        return print.toString();
    }
}
