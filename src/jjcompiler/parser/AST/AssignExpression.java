package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.Function;
import jjcompiler.lowlevel.Operand;
import jjcompiler.lowlevel.Operation;

public class AssignExpression extends Expression {

    private Expression lhs;
    private Expression rhs;

    public AssignExpression(Expression expr1, Expression expr2) {
        lhs = expr1;
        rhs = expr2;
    }

    @Override
    public int genLLCode(Function funct) throws CMinusCompilerException {

        int regNumLHS = lhs.genLLCode(funct);
        int regNumRHS = rhs.genLLCode(funct);

        int assignRegNum = funct.getNewRegNum();

        Operation oper = new Operation(Operation.OperationType.ASSIGN, funct.getCurrBlock());

        Operand src0 = new Operand(Operand.OperandType.REGISTER, regNumRHS);
        oper.setSrcOperand(0, src0);

        Operand dest0 = new Operand(Operand.OperandType.REGISTER, regNumLHS);
        oper.setDestOperand(0, dest0);

        funct.getCurrBlock().appendOper(oper);
        return assignRegNum;
    }

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append("ASSIGNEXPR: ");
        print.append(lhs.printTree(indent));
        print.append(" = ");
        print.append(rhs.printTree(indent));
        return print.toString();
    }
}
