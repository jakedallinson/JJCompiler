package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public abstract class Decl {

    protected Token typeToken;

    public String printTree(String indent) {
        StringBuilder print = new StringBuilder(typeToken.printTokenData());
        return print.toString();
    }
}
