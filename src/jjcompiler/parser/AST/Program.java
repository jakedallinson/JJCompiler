package jjcompiler.parser.AST;
import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.CodeItem;

import java.util.ArrayList;

public class Program {

    private ArrayList<Decl> decls;

    public Program () {
        decls = new ArrayList<>();
    }

    public void addDecl (Decl decl) {
        decls.add(decl);
    }

    public CodeItem genLLCode () throws CMinusCompilerException {
        // get the first code item from the decl list
        // connect rest as a linked list
        CodeItem firstCodeItem = decls.get(0).genLLCode();
        CodeItem currCodeItem = firstCodeItem;
        for (int i = 1; i < decls.size(); ++i) {
            CodeItem nextCodeItem = decls.get(i).genLLCode();
            currCodeItem.setNextItem(nextCodeItem);
            currCodeItem = nextCodeItem;
        }
        return firstCodeItem;
    }

    public String printTree() {
        String indent = "   ";
        StringBuilder print = new StringBuilder("Program:" + "\n");
        for (Decl eachDecl: this.decls) {
            print.append(eachDecl.printTree(indent)).append('\n');
        }
        return print.toString();
    }
}
