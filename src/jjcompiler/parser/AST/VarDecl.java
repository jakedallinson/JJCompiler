package jjcompiler.parser.AST;

import jjcompiler.scanner.Token;

public class VarDecl extends Decl {

    private Token IDToken;
    private Token arrLengthToken;

    public VarDecl(Token type, Token ID, Token arrLength) {
        typeToken = type;
        IDToken = ID;
        arrLengthToken = arrLength;
    }

    @Override
    public String printTree () {

        StringBuilder print = new StringBuilder();
        print.append("     ").append(typeToken.printToken());
        print.append("     ").append(IDToken.printToken()).append("\n");
        print.append("     ").append(arrLengthToken.printToken()).append("\n");

        return print.toString();
    }
}
