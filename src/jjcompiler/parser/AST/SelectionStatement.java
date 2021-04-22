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

        funct.appendToCurrentBlock(ifBlock);
        funct.setCurrBlock(ifBlock);

        // 2. genLLCode if expr
        int regNumIfExpr = ifExpr.genLLCode(funct);

        // 3. make branch to post or else
        Operation operBEQ = new Operation(Operation.OperationType.BEQ, ifBlock);
        Operand src0 = new Operand(Operand.OperandType.REGISTER, regNumIfExpr);
        Operand src1 = new Operand(Operand.OperandType.INTEGER, 0);
        Operand src2 = new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum());

        operBEQ.setSrcOperand(0, src0);
        operBEQ.setSrcOperand(1, src1);
        operBEQ.setSrcOperand(2, src2);

        funct.getCurrBlock().appendOper(operBEQ);

        // 4. append then to curr block
        funct.appendToCurrentBlock(thenBlock);
        // 5. CB moves to then block
        funct.setCurrBlock(thenBlock);
        // 6. genLLCode then stmt
        thenStmt.genLLCode(funct);

        // add jump operation to post block
//        Operation jumpOper = new Operation(Operation.OperationType.JMP, funct.getCurrBlock());
//        Operand jumpSrc0 = new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum());
//        jumpOper.setSrcOperand(0, jumpSrc0);
//        funct.getCurrBlock().appendOper(jumpOper);

        // 7. append post to CB
        funct.appendToCurrentBlock(postBlock);

        // IF ELSE BLOCK EXIST

//        if (elseStmt != null) {
//            elseBlock = new BasicBlock(funct);
        // Operation oper = new Operation(Operation.OperationType.BEQ, elseBlock);

            // 8. CB moves to else block

            // 9. genLLCode else stmt

            // 10. add jump to else

            // 11. append else to unconn chain

        // ----

        // 12. CB moves to post
        funct.setCurrBlock(postBlock);

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
