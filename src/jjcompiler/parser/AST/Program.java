package jjcompiler.parser.AST;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Program {

    private ArrayList<Decl> decls;

    public Program () {
        decls = new ArrayList<>();
    }

    public void addDecl (Decl decl) {
        decls.add(decl);
    }

    public void printTree (PrintWriter pw) {

    }
}
