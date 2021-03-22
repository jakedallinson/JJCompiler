package jjcompiler.parser.AST;

public class BinaryOpExpression extends Expression{


    @Override
    public String printTree(String indent) {
        indent += "   ";
        StringBuilder print = new StringBuilder();
        print.append("     ").append("BINARYOPEXPRESSION").append('\n');

        return print.toString();
    }
}
