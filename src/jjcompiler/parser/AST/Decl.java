package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.CodeItem;
import jjcompiler.lowlevel.Function;
import jjcompiler.scanner.Token;

public abstract class Decl {

    protected Token typeToken;
    protected Token IDToken;

    public abstract CodeItem genLLCode() throws CMinusCompilerException;
    public abstract void genLLCode(Function funct);
    public abstract String printTree(String indent);
}
