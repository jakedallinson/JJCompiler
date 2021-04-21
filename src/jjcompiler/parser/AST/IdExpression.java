package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.Function;
import jjcompiler.scanner.Token;

public class IdExpression extends Expression {

    private Token IDToken;
    private Expression arrIndexExpr;

    public IdExpression(Token token, Expression expr) {
        IDToken = token;
        arrIndexExpr = expr;
    }

    @Override
    public int genLLCode(Function funct) throws CMinusCompilerException {

        if (funct.getTable().get(IDToken.getData()) == IDToken.getData()) {
            return (int) funct.getTable().get(IDToken.getData());
        } else {
            throw new CMinusCompilerException("genLLCode", IDToken.getData());
        }

        // IF ELSE FOR GLOBAL
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
