package jjcompiler.parser.AST;


public class SelectionStatement extends Statement {

    Expression expr;
    Statement thenStmt;
    Statement elseStmt;

    public SelectionStatement (Expression express, Statement stmt) {
        this (express, stmt, null);
    }

    public SelectionStatement (Expression express, Statement stmt1, Statement stmt2) {
        expr = express;
        thenStmt = stmt1;
        elseStmt = stmt2;
    }
}
