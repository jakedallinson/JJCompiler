package jjcompiler.parser.AST;

public class IterationStatement extends Statement {

    private Expression paramsExpr;
    private Statement loopStmt;

    public IterationStatement(Expression expr, Statement stmt) {
        paramsExpr = expr;
        loopStmt = stmt;
    }
}
