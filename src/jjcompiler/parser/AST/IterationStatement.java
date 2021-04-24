package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.BasicBlock;
import jjcompiler.lowlevel.Function;
import jjcompiler.lowlevel.Operand;
import jjcompiler.lowlevel.Operation;

public class IterationStatement extends Statement {

    private Expression paramsExpr;
    private Statement loopStmt;

    public IterationStatement(Expression expr, Statement stmt) {
        paramsExpr = expr;
        loopStmt = stmt;
    }

    @Override
    public void genLLCode(Function funct)  throws CMinusCompilerException {


        // 1. make 2-3 blocks
        BasicBlock paramsExprBlock = new BasicBlock(funct);
        BasicBlock bodyBlock = new BasicBlock(funct);
        BasicBlock postBlock = new BasicBlock(funct);

        funct.appendToCurrentBlock(paramsExprBlock);
        funct.setCurrBlock(paramsExprBlock);

        // 2. genLLCode paramsExpr
        int regNumIfExpr = paramsExpr.genLLCode(funct);

        // 3. make branch to post
        Operation operBNE = new Operation(Operation.OperationType.BEQ, paramsExprBlock);
        Operand src0 = new Operand(Operand.OperandType.REGISTER, regNumIfExpr);
        Operand src1 = new Operand(Operand.OperandType.INTEGER, 0);
        Operand src2 = new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum());


        operBNE.setSrcOperand(0, src0);
        operBNE.setSrcOperand(1, src1);
        operBNE.setSrcOperand(2, src2);

        funct.getCurrBlock().appendOper(operBNE);

        // 4. append then to curr block
        funct.appendToCurrentBlock(bodyBlock);
        // 5. CB moves to then block
        funct.setCurrBlock(bodyBlock);
        // 6. genLLCode then body
        loopStmt.genLLCode(funct);

        // 10. add jump to the params
        Operation jumpOper = new Operation(Operation.OperationType.JMP, funct.getCurrBlock());
        Operand jumpSrc0 = new Operand(Operand.OperandType.BLOCK, paramsExprBlock.getBlockNum());
        jumpOper.setSrcOperand(0, jumpSrc0);

        funct.getCurrBlock().appendOper(jumpOper);

        // 7. append post to CB
        funct.appendToCurrentBlock(postBlock);

        // 12. CB moves to post
        funct.setCurrBlock(postBlock);
    }

    @Override
    public String printTree(String indent) {

        StringBuilder print = new StringBuilder();
        print.append(indent).append("WHILE: ").append("\n");
        print.append(indent).append("PARMS: ");
        print.append(paramsExpr.printTree(indent)).append('\n');
        print.append(loopStmt.printTree(indent + "   ")).append(indent);

        return print.toString();
    }
}