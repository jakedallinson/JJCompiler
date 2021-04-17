package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.CodeItem;
import jjcompiler.scanner.Token;

public abstract class Decl {

    protected Token typeToken;

    public abstract CodeItem genLLCode() throws CMinusCompilerException;

    public abstract String printTree(String indent);
}
