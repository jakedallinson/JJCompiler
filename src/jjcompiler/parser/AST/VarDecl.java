package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;
import java.io.PrintWriter;

public class VarDecl extends Decl {

    private Token IDToken;
    private Token arrLengthToken;

    public VarDecl(Token type, Token ID, Token arrLength) {
        typeToken = type;
        IDToken = ID;
        arrLengthToken = arrLength;
    }

    public void printTree (PrintWriter pw) { }
}
