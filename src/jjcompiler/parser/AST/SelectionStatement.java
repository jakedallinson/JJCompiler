package jjcompiler.parser.AST;


import jjcompiler.lowlevel.Function;

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
    public void genLLCode(Function funct) {

    }

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append(indent).append("IF: ").append("\n");
        print.append(indent).append("PARMS: ");
        print.append(ifExpr.printTree(indent)).append("\n");
        print.append(indent).append(thenStmt.printTree(indent));
        if (elseStmt != null) {

            print.append(indent).append("ELSE ").append("\n");
            print.append(elseStmt.printTree(indent + "   ")).append("\n");
        }
        return print.toString();
    }
}
