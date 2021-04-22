package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.Data;
import jjcompiler.lowlevel.Function;
import jjcompiler.scanner.Token;

import static jjcompiler.compiler.CMinusCompiler.globalHash;

public class VarDecl extends Decl {

    private Token arrLengthToken;

    public VarDecl(Token type, Token ID, Token arrLength) {
        typeToken = type;
        IDToken = ID;
        arrLengthToken = arrLength;
    }

    public Data genLLCode () throws CMinusCompilerException {

        globalHash.put(IDToken.getData(), typeToken.complierType());
        return new Data(typeToken.complierType(), IDToken.getData());
    }

    @Override
    public void genLLCode (Function funct)  throws CMinusCompilerException{

            funct.getTable().put(IDToken.getData(), funct.getNewRegNum());
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
