package jjcompiler.parser.AST;


public class SelectionStatement extends Statement {

    Expression ifExpr;
    Statement thenStmt;
    Statement elseStmt;

    public SelectionStatement (Expression expr, Statement stmt1, Statement stmt2) {
        ifExpr = expr;
        thenStmt = stmt1;
        elseStmt = stmt2;
    }
}
