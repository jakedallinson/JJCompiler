package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.Function;
import jjcompiler.lowlevel.Operand;
import jjcompiler.lowlevel.Operation;
import jjcompiler.scanner.Token;

public class BinaryOpExpression extends Expression{

    private Token type;
    private Expression lhs;
    private Expression rhs;

    public BinaryOpExpression(Token xtype, Expression xlhs, Expression xrhs) {
        this.type = xtype;
        this.lhs  = xlhs;
        this.rhs  = xrhs;
    }

    @Override
    public int genLLCode(Function funct)  throws CMinusCompilerException {
        int regNumLHS = lhs.genLLCode(funct);
        int regNumRHS = rhs.genLLCode(funct);
        int regNumDest = funct.getNewRegNum();
        
        Operation oper = new Operation(createOperType(), funct.getCurrBlock());

        Operand src0 = new Operand(Operand.OperandType.REGISTER, regNumLHS);
        oper.setSrcOperand(0, src0);
        Operand src1 = new Operand(Operand.OperandType.REGISTER, regNumRHS);
        oper.setSrcOperand(1, src1);
        Operand dest0 = new Operand(Operand.OperandType.REGISTER, regNumDest);
        oper.setDestOperand(0, dest0);

        funct.getCurrBlock().appendOper(oper);

        return regNumDest;
    }

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append("BINOPEXPR: ");
        print.append(lhs.printTree(indent)).append(" " + type.printTokenData() + " ").append(rhs.printTree(indent));
        return print.toString();
    }

    private Operation.OperationType createOperType() throws CMinusCompilerException {
        switch (type.getType()) {
            // arithmetic
            case PLUS:
                return Operation.OperationType.ADD_I;
            case MINUS:
                return Operation.OperationType.SUB_I;
            case TIMES:
                return Operation.OperationType.MUL_I;
            case DIVIDE:
                return Operation.OperationType.DIV_I;
            // comparison
            case LT:
                return Operation.OperationType.LT;
            case LTEQ:
                return Operation.OperationType.LTE;
            case GT:
                return Operation.OperationType.GT;
            case GTEQ:
                return Operation.OperationType.GTE;
            case EQ:
                return Operation.OperationType.EQUAL;
            case NOTEQ:
                return Operation.OperationType.NOT_EQUAL;
            default:
                throw new CMinusCompilerException("getOperType", "operType");
        }
    }
}
