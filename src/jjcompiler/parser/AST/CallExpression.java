package jjcompiler.parser.AST;

import jjcompiler.lowlevel.Function;
import jjcompiler.scanner.Token;

import java.util.ArrayList;

public class CallExpression extends Expression {

    private Token IDToken;
    private ArrayList<Expression> argList;

    public CallExpression(Token token, ArrayList<Expression> args) {
        IDToken = token;
        argList = args;
    }

    @Override
    public int genLLCode(Function funct) {
        return 0;
    }

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();

        print.append("CALLEXPR: ").append(IDToken.printTokenData()).append(" PARMS: ");

        if (argList.isEmpty()) {
            print.append("NONE");
        } else {
            for (Expression eachArg : this.argList) {
                print.append(eachArg.printTree(indent)).append(", ");
            }
            print.deleteCharAt(print.length()-2);
        }
        return print.toString();
    }
}
