package jjcompiler.parser.AST;

public class ReturnStatement extends Statement {

    private Expression returnExpr;

    public ReturnStatement(Expression expr) {
        returnExpr = expr;
    }
}
