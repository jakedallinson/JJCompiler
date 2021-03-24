package jjcompiler.parser.AST;

public class ReturnStatement extends Statement {

    private Expression returnExpr;

    public ReturnStatement(Expression expr) {
        returnExpr = expr;
    }

    @Override
    public String printTree (String indent) {
        StringBuilder print = new StringBuilder();

        print.append(indent).append("return");

        if (returnExpr != null) {
            print.append(" ").append(returnExpr.printTree(indent));
        }
        return print.toString();
    }
}
