package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.Function;

public abstract class Statement {
    public abstract void genLLCode(Function funct) throws CMinusCompilerException;
    public abstract String printTree(String indent);
}
