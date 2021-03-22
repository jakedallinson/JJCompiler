package jjcompiler.parser.AST;

public class AssignExpression extends Expression{






    @Override
    public String printTree(String indent) {

        StringBuilder print = new StringBuilder();
        print.append("     ").append("ASSIGNEXPRESSION").append('\n');

        return print.toString();
    }

}
