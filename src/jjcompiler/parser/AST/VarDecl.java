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
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append(indent).append(typeToken.printTokenData());
        if (IDToken != null) {
            print.append(" ").append(IDToken.printTokenData());
        }
        if (arrLengthToken != null) {
            print.append(" ").append(arrLengthToken.printTokenData());
        }
        print.append('\n');
        return print.toString();
    }
}
