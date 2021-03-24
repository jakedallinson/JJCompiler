package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public class IdExpression extends Expression{

    private Token IDToken;
    private Expression arrIndexExpr;

    public IdExpression(Token token, Expression expr) {
        IDToken = token;
        arrIndexExpr = expr;
    }


    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append("IDEXPR: ").append(IDToken.printTokenData());

        if (arrIndexExpr != null) {
            print.append("[").append(arrIndexExpr.printTree(indent)).append("]");
        }
        return print.toString();
    }
}
