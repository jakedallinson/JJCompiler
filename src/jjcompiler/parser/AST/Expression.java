package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.Function;

public abstract class Expression {
    public abstract int genLLCode(Function funct) throws CMinusCompilerException;
    public abstract String printTree(String indent);
    public abstract String getData ();
}
