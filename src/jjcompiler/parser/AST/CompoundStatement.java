package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.Function;

import java.util.ArrayList;

public class CompoundStatement extends Statement {

    private ArrayList<Decl> decls;
    private ArrayList<Statement> stmts;

    public CompoundStatement () {
        decls = new ArrayList<>();
        stmts = new ArrayList<>();
    }

    @Override
    public void genLLCode(Function funct) throws CMinusCompilerException {
        // gen decls
        for (int i = 0; i < decls.size(); i++) {
            decls.get(i).genLLCode(funct);
        }

        // gen stmts
        for (int i = 0; i < stmts.size(); i++) {
            stmts.get(i).genLLCode(funct);
//            Operation oper = new Operation(Operation.OperationType.STORE_I, funct.getCurrBlock());
//            Operand operand = new Operand(Operand.OperandType.REGISTER);
//            funct.getNewRegNum();
        }
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