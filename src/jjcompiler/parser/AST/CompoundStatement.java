package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.Function;
import jjcompiler.lowlevel.Operand;
import jjcompiler.lowlevel.Operation;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void genLLCode(Function funct) throws CMinusCompilerException {
        for (int i = 0; i < decls.size(); i++) {
            decls.get(i).genLLCode();



//            Operation oper = new Operation(Operation.OperationType.STORE_I, funct.getCurrBlock());
//            Operand operand = new Operand(Operand.OperandType.REGISTER);
//            funct.getNewRegNum();
        }
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