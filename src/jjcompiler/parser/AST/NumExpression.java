package jjcompiler.parser.AST;

public class NumExpression extends Expression{

    @Override
    public String printTree(String indent) {

        StringBuilder print = new StringBuilder();
        print.append("     ").append("NUMEXPRESSION");

        return print.toString();
    }
}
