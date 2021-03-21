package jjcompiler.parser.AST;

public class ReturnStatement extends Statement {

    private Expression returnExpr;

    public ReturnStatement(Expression expr) {
        returnExpr = expr;
    }

    public String printTree () {
        StringBuilder print = new StringBuilder();

        print.append(returnExpr.printTree());

        return print.toString();
    }
}
