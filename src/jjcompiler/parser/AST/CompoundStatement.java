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

    @Override
    public String printTree (String indent) {
        StringBuilder print = new StringBuilder();

        for (Decl eachDecl: decls) {
            print.append(eachDecl.printTree(indent)).append('\n');
        }

        for (Statement eachStmt: stmts) {
            print.append(eachStmt.printTree(indent)).append('\n');
        }

        return print.toString();
    }
}