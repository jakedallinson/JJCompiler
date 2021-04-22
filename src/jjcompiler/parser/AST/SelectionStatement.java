package jjcompiler.parser.AST;


import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.BasicBlock;
import jjcompiler.lowlevel.Function;
import jjcompiler.lowlevel.Operand;
import jjcompiler.lowlevel.Operation;

public class SelectionStatement extends Statement {

    Expression ifExpr;
    Statement thenStmt;
    Statement elseStmt;

    public SelectionStatement (Expression expr, Statement stmt1, Statement stmt2) {
        ifExpr = expr;
        thenStmt = stmt1;
        elseStmt = stmt2;
    }

    @Override
    public void genLLCode (Function funct)  throws CMinusCompilerException {

        // 1. make 2-3 blocks
        BasicBlock ifBlock = new BasicBlock(funct);
        BasicBlock thenBlock = new BasicBlock(funct);
        BasicBlock postBlock = new BasicBlock(funct);

        // 2. genLLCode if expr
        int regNumIfExpr = ifExpr.genLLCode(funct);

        // 3. make branch to post or else
        Operation oper = new Operation(Operation.OperationType.BEQ, ifBlock);
        Operand src0 = new Operand(Operand.OperandType.REGISTER, regNumIfExpr);
        Operand src1 = new Operand(Operand.OperandType.INTEGER, 0);
        Operand src2 = new Operand(Operand.OperandType.BLOCK, thenBlock.getBlockNum());

        // 4. append then to curr block
        // 5. CB moves to then block
        // 6. genLLCode then stmt
        thenStmt.genLLCode(funct);

        // 7. append post to CB
        // 8. CB moves to else block
        // 9. genLLCode else stmt
        // 10. add jump to else
        // 11. append else to unconn chain
        // 12. CB moves to post

//        if (elseStmt != null) {
//            elseBlock = new BasicBlock(funct);
            // Operation oper = new Operation(Operation.OperationType.BEQ, elseBlock);
    }

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append(indent).append("IF: ").append("\n");
        print.append(indent).append("PARMS: ");
        print.append(ifExpr.printTree(indent)).append("\n");
        print.append(indent).append(thenStmt.printTree(indent));
        if (elseStmt != null) {

            print.append(indent).append("ELSE ").append("\n");
            print.append(elseStmt.printTree(indent + "   ")).append("\n");
        }
        return print.toString();
    }
}
