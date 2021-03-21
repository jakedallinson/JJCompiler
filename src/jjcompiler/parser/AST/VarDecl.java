package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;
import java.io.PrintWriter;

public class VarDecl extends Decl {
    private Token funTypeToken;
    private Token funIDToken;

    public VarDecl (Token type, Token ID) {
        funTypeToken = type;
        funIDToken = ID;
    }

    public void printTree (PrintWriter pw) { }
}
