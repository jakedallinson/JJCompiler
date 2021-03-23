package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public class BinaryOpExpression extends Expression{

    private Token.TokenType type;
    private Expression lhs;
    private Expression rhs;

    public BinaryOpExpression(Token.TokenType xtype, Expression xlhs, Expression xrhs) {
        this.type = xtype;
        this.lhs  = xlhs;
        this.rhs  = xrhs;
    }

    @Override
    public String printTree(String indent) {
        indent += "   ";
        StringBuilder print = new StringBuilder();
        print.append("     ").append("BINARYOPEXPRESSION").append('\n');

        return print.toString();
    }
}
