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

    public String printTree() {
        String indent = "   ";
        StringBuilder print = new StringBuilder("Program:" + "\n");
        for (Decl eachDecl: this.decls) {
            print.append(eachDecl.printTree(indent)).append('\n');
        }
        return print.toString();
    }
}
