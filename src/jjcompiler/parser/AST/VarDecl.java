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

        return new Data(typeToken.complierType(), IDToken.getData());
    }

    @Override
    public void genLLCode (Function funct)  throws CMinusCompilerException{

        // working on local var decl
        if (funct.getTable().containsValue(IDToken.getData())) {
            funct.getTable().put(IDToken.getData(), funct.getNewRegNum());
        } else {
            throw new CMinusCompilerException("genLLCode", IDToken.getData());
        }
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
