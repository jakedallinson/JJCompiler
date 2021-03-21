package jjcompiler.parser.AST;
import java.util.ArrayList;

public class Program {

    private ArrayList<Decl> decls;

    public Program () {
        decls = new ArrayList<>();
    }

    public void addDecl (Decl decl) {
        decls.add(decl);
    }

    public String printTree () {

        StringBuilder print = new StringBuilder();
        print = new StringBuilder("Program" + "\n");

        for (Decl eachDecl: decls) {
            print.append("     ").append(eachDecl.printTree());
            print.append('\n');
        }
        return print.toString();
    }
}
