package jjcompiler.parser.AST;

import java.io.PrintWriter;
import jjcompiler.scanner.*;

public class FunDecl extends Decl {

    private Token funTypeToken;
    private Token funIDToken;
    private Expression funExpression;
    private Statement funStatement;

    public FunDecl (Token type, Token ID, Expression expr, Statement stmt) {
        funTypeToken = type;
        funIDToken = ID;
        funExpression = expr;
        funStatement = stmt;
    }

    public void printTree (PrintWriter pw) { }
}
