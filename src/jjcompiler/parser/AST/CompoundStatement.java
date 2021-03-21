package jjcompiler.parser.AST;

import java.util.ArrayList;

public class CompoundStatement extends Statement {

    private ArrayList<Decl> decls;
    private ArrayList<Statement> stmts;

    public CompoundStatement () {
        decls = new ArrayList<>();
        stmts = new ArrayList<>();
    }

    public void addDecl (Decl decl) {
        decls.add(decl);
    }

    public void addStmt (Statement stmt) {
        stmts.add(stmt);
    }

    public String printTree () {

        StringBuilder print = new StringBuilder();
        print = new StringBuilder("Program" + "\n");

        for (Decl eachDecl: decls) {
            print.append("     ").append(eachDecl.printTree());
            print.append('\n');
        }

        for (Statement eachStmt: stmts) {
            print.append("     ").append(eachStmt.printTree());
            print.append('\n');
        }

        return print.toString();
    }

}
