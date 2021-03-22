package jjcompiler.parser.AST;

public class IterationStatement extends Statement {

    private Expression paramsExpr;
    private Statement loopStmt;

    public IterationStatement(Expression expr, Statement stmt) {
        paramsExpr = expr;
        loopStmt = stmt;
    }

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append("WHILE: (");
        print.append(paramsExpr.printTree(indent));
        print.append(")").append(" {").append('\n');
        print.append(loopStmt.printTree(indent + "   ")).append(indent).append("}");

        return print.toString();
    }
}