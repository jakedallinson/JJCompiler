package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public class BinaryOpExpression extends Expression{

    private Token type;
    private Expression lhs;
    private Expression rhs;

    public BinaryOpExpression(Token xtype, Expression xlhs, Expression xrhs) {
        this.type = xtype;
        this.lhs  = xlhs;
        this.rhs  = xrhs;
    }

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append("BINOPEXPR: ");
        print.append(lhs.printTree(indent)).append(" " + type.printTokenData() + " ").append(rhs.printTree(indent));
        return print.toString();
    }
}
