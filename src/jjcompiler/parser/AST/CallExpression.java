package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.Attribute;
import jjcompiler.lowlevel.Function;
import jjcompiler.lowlevel.Operand;
import jjcompiler.lowlevel.Operation;
import jjcompiler.scanner.Token;

import java.util.ArrayList;

public class CallExpression extends Expression {

    private Token IDToken;
    private ArrayList<Expression> argList;

    public CallExpression (Token token, ArrayList<Expression> args) {
        IDToken = token;
        argList = args;
    }

    @Override
    public int genLLCode (Function funct) throws CMinusCompilerException {
        for (int i = 0; i < argList.size(); i++) {
            // move each param to register or memory
            int regNumArg = argList.get(i).genLLCode(funct);

            Operation oper = new Operation(Operation.OperationType.PASS, funct.getCurrBlock());
            Operand src0 = new Operand(Operand.OperandType.REGISTER, regNumArg);
            oper.setSrcOperand(0, src0);
            oper.addAttribute(new Attribute("PARAM_NUM", Integer.toString(i)));

            funct.getCurrBlock().appendOper(oper);
        }
        // add call oper
        Operation callOper = new Operation(Operation.OperationType.CALL, funct.getCurrBlock());
        Operand callSrc0 = new Operand(Operand.OperandType.STRING, IDToken.getData());
        callOper.setSrcOperand(0, callSrc0);
        callOper.addAttribute(new Attribute("numParams", Integer.toString(argList.size())));

        funct.getCurrBlock().appendOper(callOper);

        // move retreg to gen purpose reg
        Operation assignOper = new Operation(Operation.OperationType.ASSIGN, funct.getCurrBlock());

        Operand assignSrc0 = new Operand(Operand.OperandType.MACRO, "RetReg");
        assignOper.setSrcOperand(0, assignSrc0);

        int assignRegNum = funct.getNewRegNum();
        Operand assignDest0 = new Operand(Operand.OperandType.REGISTER, assignRegNum);
        assignOper.setDestOperand(0, assignDest0);

        funct.getCurrBlock().appendOper(assignOper);

        // return location of moved return value
        return assignRegNum;
    }

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();

        print.append("CALLEXPR: ").append(IDToken.printTokenData()).append(" PARMS: ");

        if (argList.isEmpty()) {
            print.append("NONE");
        } else {
            for (Expression eachArg : this.argList) {
                print.append(eachArg.printTree(indent)).append(", ");
            }
            print.deleteCharAt(print.length()-2);
        }
        return print.toString();
    }
}
