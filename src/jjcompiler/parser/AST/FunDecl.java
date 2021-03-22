package jjcompiler.parser.AST;

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

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();

        print.append(indent).append("FUNCTION: ");
        print.append(typeToken.printTokenData()).append(" ");
        print.append(IDToken.printTokenData()).append("\n");

        print.append(indent).append("PARAMS: (");
        if (paramsList.isEmpty()) {
            print.append("NONE");
        } else {
            for (VarDecl eachParam : this.paramsList) {
                print.append(eachParam.printTree(indent));
            }
        }
        print.append(") {\n");
        print.append(compoundStatement.printTree(indent)).append("\n").append(indent).append("}");
        return print.toString();
    }
}
