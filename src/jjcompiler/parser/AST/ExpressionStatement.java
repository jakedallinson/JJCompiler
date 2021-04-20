package jjcompiler.parser.AST;

import jjcompiler.lowlevel.Function;

public class ExpressionStatement extends Statement {

    private Expression expr;

    public ExpressionStatement(Expression expression) {
        expr = expression;
    }

    @Override
    public void genLLCode(Function funct) {
        expr.genLLCode(funct);
    }

    @Override
    public String printTree(String indent) {

        StringBuilder print = new StringBuilder();
        print.append(indent).append("EXPRESSIONEXPR: ").append(expr.printTree(indent + "   "));

        return print.toString();
    }
}
