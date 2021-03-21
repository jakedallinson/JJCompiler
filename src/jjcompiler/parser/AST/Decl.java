package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public class Decl {

    protected Token typeToken;

    public String printTree() {
        StringBuilder print = new StringBuilder(typeToken.printToken());
        return print.toString();
    }
}
