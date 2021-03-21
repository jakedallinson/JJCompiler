package jjcompiler.parser.AST;

import java.util.ArrayList;
import jjcompiler.scanner.*;

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

    public String printTree () {

        StringBuilder print = new StringBuilder("Program" + "\n");

        print.append("     ").append(typeToken.printToken());
        print.append("     ").append(IDToken.printToken()).append("\n");
        print.append("     ").append(paramsExpression.printTree()).append("\n");
        print.append("     ").append(compoundStatement.printTree()).append("\n");

        return print.toString();
    }
}
