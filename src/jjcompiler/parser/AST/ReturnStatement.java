package jjcompiler.parser.AST;

import jjcompiler.lowlevel.Function;
import jjcompiler.lowlevel.Operand;
import jjcompiler.lowlevel.Operation;

public class ReturnStatement extends Statement {

    private Expression returnExpr;

    public ReturnStatement(Expression expr) {
        returnExpr = expr;
    }

    @Override
    public void genLLCode(Function funct) {
        if (returnExpr != null) {
            int regNum = returnExpr.genLLCode(funct);
            Operation assignOper = new Operation(Operation.OperationType.ASSIGN, funct.getCurrBlock());

            Operand assignSrc0 = new Operand(Operand.OperandType.REGISTER, regNum);
            assignOper.setSrcOperand(0, assignSrc0);
            Operand assignDest0 = new Operand(Operand.OperandType.MACRO, "RetReg");
            assignOper.setDestOperand(0, assignDest0);

            funct.getCurrBlock().appendOper(assignOper);

            // add jump operation to exit block
            Operation jumpOper = new Operation(Operation.OperationType.JMP, funct.getCurrBlock());
            Operand jumpSrc0 = new Operand(Operand.OperandType.BLOCK, funct.getReturnBlock().getBlockNum());
            jumpOper.setSrcOperand(0, jumpSrc0);

            funct.getCurrBlock().appendOper(jumpOper);
        }
    }

    @Override
    public String printTree (String indent) {
        StringBuilder print = new StringBuilder();

        print.append(indent).append("return");

        if (returnExpr != null) {
            print.append(" ").append(returnExpr.printTree(indent));
        }
        return print.toString();
    }
}
