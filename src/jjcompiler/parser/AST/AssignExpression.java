package jjcompiler.parser.AST;

public class AssignExpression extends Expression {

    private Expression lhs;
    private Expression rhs;

    public AssignExpression(Expression expr1, Expression expr2) {
        lhs = expr1;
        rhs = expr2;
    }

    @Override
    public String printTree(String indent) {

        StringBuilder print = new StringBuilder();
        print.append("     ").append("ASSIGNEXPRESSION").append('\n');

        return print.toString();
    }

}
