package jjcompiler.parser.AST;

import java.io.PrintWriter;
import jjcompiler.scanner.*;

public class FunDecl extends Decl {

    private Token IDToken;
    private Expression paramsExpression;
    private Statement compoundStatement;

    public FunDecl (Token type, Token ID, Expression expr, Statement stmt) {
        typeToken = type;
        IDToken = ID;
        paramsExpression = expr;
        compoundStatement = stmt;
    }

    public void printTree (PrintWriter pw) { }
}
