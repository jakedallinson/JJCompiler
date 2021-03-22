package jjcompiler.parser.AST;

public class CallExpression extends Expression{


    @Override
    public String printTree(String indent) {
        indent += "   ";
        StringBuilder print = new StringBuilder();
        print.append(indent).append("CALLEXPRESSION").append('\n');

        return print.toString();
    }
}
