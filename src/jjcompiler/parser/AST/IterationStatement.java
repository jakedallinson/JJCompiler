package jjcompiler.parser.AST;

import jjcompiler.lowlevel.Function;

public class IterationStatement extends Statement {

    private Expression paramsExpr;
    private Statement loopStmt;

    public IterationStatement(Expression expr, Statement stmt) {
        paramsExpr = expr;
        loopStmt = stmt;
    }

    @Override
    public void genLLCode(Function funct) {

    }

    @Override
    public String printTree(String indent) {

        StringBuilder print = new StringBuilder();
        print.append(indent).append("WHILE: ").append("\n");
        print.append(indent).append("PARMS: ");
        print.append(paramsExpr.printTree(indent)).append('\n');
        print.append(loopStmt.printTree(indent + "   ")).append(indent);

        return print.toString();
    }
}