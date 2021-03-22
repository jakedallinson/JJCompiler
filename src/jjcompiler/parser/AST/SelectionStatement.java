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

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append(indent).append("IF (");
        print.append(ifExpr.printTree(indent));
        print.append(") {").append("\n");
        print.append(indent).append(thenStmt.printTree(indent)).append("\n");

        if (elseStmt != null) {
            print.append(indent).append(elseStmt.printTree(indent)).append("\n");
        }
        print.append(indent).append("}");
        return print.toString();
    }
}
