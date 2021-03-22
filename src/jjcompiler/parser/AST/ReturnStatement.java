package jjcompiler.parser.AST;

public class ReturnStatement extends Statement {

    private Expression returnExpr;

    public ReturnStatement(Expression expr) {
        returnExpr = expr;
    }

    @Override
    public String printTree (String indent) {
        StringBuilder print = new StringBuilder();

        if (returnExpr != null) {
            print.append(indent).append(returnExpr.printTree(indent));
        } else {
            print.append("RETURN");
        }

        return print.toString();
    }
}
