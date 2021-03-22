package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public abstract class Decl {

    protected Token typeToken;

    public String printTree(String indent) {
        StringBuilder print = new StringBuilder(indent + typeToken.printTokenData()).append('\n');
        return print.toString();
    }
}
