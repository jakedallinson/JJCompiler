package jjcompiler.parser.AST;

public class ExpressionStatement extends Statement {

    private Expression expr;

    public ExpressionStatement(Expression expression) {
        expr = expression;
    }
}
