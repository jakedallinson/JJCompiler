package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public class Decl {
    protected Token typeToken;

    public String printTree () {
        StringBuilder print = new StringBuilder();

        print.append(typeToken.printToken());

        return print.toString();
    }
}
