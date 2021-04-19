package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.Data;
import jjcompiler.lowlevel.Function;
import jjcompiler.scanner.Token;

public class VarDecl extends Decl {

    private Token arrLengthToken;

    public VarDecl(Token type, Token ID, Token arrLength) {
        typeToken = type;
        IDToken = ID;
        arrLengthToken = arrLength;
    }

    public Data genLLCode () throws CMinusCompilerException {
        int dataType;
        if (typeToken.getType() == Token.TokenType.INT) {
            dataType = Data.TYPE_INT;
        } else if (typeToken.getType() == Token.TokenType.VOID) {
            dataType = Data.TYPE_VOID;
        } else {
            throw new CMinusCompilerException("genLLCode",Token.TokenType.INT,typeToken.getType());
        }
        boolean isArr = false;
        int arrLength = -1;
        if (arrLengthToken != null) {
            isArr = true;
            arrLength = (int) arrLengthToken.getData();
        }

        return new Data(dataType, IDToken.getData().toString(), isArr, arrLength);
    }

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append(indent).append("VARDECL: ").append(typeToken.printTokenData());
        if (IDToken != null) {
            print.append(" ").append(IDToken.printTokenData());
        }
        if (arrLengthToken != null) {
            print.append("[").append(arrLengthToken.printTokenData()).append("] ");
        }

        return print.toString();
    }
}
