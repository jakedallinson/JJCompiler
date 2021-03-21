package jjcompiler.parser.AST;

import java.io.PrintWriter;
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

    public void printTree (PrintWriter pw) { }
}
