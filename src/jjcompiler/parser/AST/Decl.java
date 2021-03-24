package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public abstract class Decl {

    protected Token typeToken;

    public abstract String printTree(String indent);
}
