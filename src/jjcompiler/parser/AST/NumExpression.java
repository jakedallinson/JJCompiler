package jjcompiler.parser.AST;

import jjcompiler.lowlevel.Function;
import jjcompiler.lowlevel.Operand;
import jjcompiler.lowlevel.Operation;
import jjcompiler.scanner.Token;

public class NumExpression extends Expression {

    private Token numToken;

    public NumExpression(Token token) {
        numToken = token;
    }

    @Override
    public int genLLCode(Function funct) {
        int regNum = funct.getNewRegNum();
        Operation oper = new Operation(Operation.OperationType.ASSIGN, funct.getCurrBlock());

        Operand src0 = new Operand(Operand.OperandType.INTEGER, Integer.parseInt(numToken.getData()));
        oper.setSrcOperand(0, src0);

        Operand dest0 = new Operand(Operand.OperandType.REGISTER, regNum);
        oper.setDestOperand(0, dest0);

        funct.getCurrBlock().appendOper(oper);
        return regNum;
    }

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append("NUMEXPR: ").append(numToken.printTokenData());
        return print.toString();
    }
}
