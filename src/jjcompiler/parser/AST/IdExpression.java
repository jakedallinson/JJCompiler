package jjcompiler.parser.AST;

public class IdExpression extends Expression{

    @Override
    public String printTree(String indent) {
        indent += "   ";
        StringBuilder print = new StringBuilder();
        print.append(indent).append("IDEXPRESSION").append('\n');

        return print.toString();
    }
}
