package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.CodeItem;
import jjcompiler.lowlevel.Data;
import jjcompiler.lowlevel.Function;
import jjcompiler.scanner.Token;
import java.util.ArrayList;

public class FunDecl extends Decl {

    private Token IDToken;
    private ArrayList<VarDecl> paramsList;
    private Statement compoundStatement;

    public FunDecl (Token type, Token ID, ArrayList<VarDecl> params, Statement stmt) {
        typeToken = type;
        IDToken = ID;
        paramsList = params;
        compoundStatement = stmt;
    }

    public Function genLLCode () throws CMinusCompilerException {
        // get the return type and string name for the function
        int dataType;
        if (typeToken.getType() == Token.TokenType.INT) {
            dataType = Data.TYPE_INT;
        } else if (typeToken.getType() == Token.TokenType.VOID) {
            dataType = Data.TYPE_VOID;
        } else {
            throw new CMinusCompilerException("genLLCode",Token.TokenType.INT,typeToken.getType());
        }
        return new Function(dataType, IDToken.getData().toString());
    }

    @Override
    public String printTree(String indent) {

        StringBuilder print = new StringBuilder();

        print.append(indent).append("FUNCTION: ");
        print.append(typeToken.printTokenData()).append(" ");
        print.append(IDToken.printTokenData()).append("\n");

        print.append(indent).append("PARAMS: ");

        if (paramsList.size() < 2) {
            print.append(paramsList.get(0).printTree(indent));
        } else {
            for (VarDecl eachParam : this.paramsList) {
                print.append(eachParam.printTree(indent)).append(", ");
            }
            print.deleteCharAt(print.length()-2);
        }

        print.append("\n");
        print.append(compoundStatement.printTree(indent + "   "));
        return print.toString();
    }
}
