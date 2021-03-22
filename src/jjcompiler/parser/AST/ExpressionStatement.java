package jjcompiler.parser.AST;

public class ExpressionStatement extends Statement {

    private Expression expr;

    public ExpressionStatement(Expression expression) {
        expr = expression;
    }

    @Override
    public String printTree(String indent) {

        StringBuilder print = new StringBuilder();
        print.append(indent).append(expr.printTree(indent));

        return print.toString();
    }
}
