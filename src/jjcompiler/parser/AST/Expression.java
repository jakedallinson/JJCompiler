package jjcompiler.parser.AST;

import jjcompiler.lowlevel.Function;

public abstract class Expression {
    public abstract int genLLCode(Function funct);
    public abstract String printTree(String indent);
}
