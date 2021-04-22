package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.Function;
import jjcompiler.lowlevel.Operand;
import jjcompiler.lowlevel.Operation;
import jjcompiler.scanner.Token;

import static jjcompiler.compiler.CMinusCompiler.globalHash;

public class IdExpression extends Expression {

    private Token IDToken;
    private Expression arrIndexExpr;

    public IdExpression(Token token, Expression expr) {
        IDToken = token;
        arrIndexExpr = expr;
    }

    @Override
    public int genLLCode(Function funct) throws CMinusCompilerException {

        if (funct.getTable().containsKey(IDToken.getData())) {
            return (int) funct.getTable().get(IDToken.getData());
        } else if (globalHash.containsKey(IDToken.getData())) {

            int assignRegNum = funct.getNewRegNum();

            Operation oper = new Operation(Operation.OperationType.LOAD_I, funct.getCurrBlock());

            Operand src0 = new Operand(Operand.OperandType.STRING, globalHash.get(IDToken.getData()));
            oper.setSrcOperand(0, src0);

            Operand dest0 = new Operand(Operand.OperandType.INTEGER, 0);
            oper.setDestOperand(0, dest0);

            funct.getCurrBlock().appendOper(oper);

            return assignRegNum;

        } else {
            throw new CMinusCompilerException("genLLCode", IDToken.getData());
        }
    }

    @Override
    public String printTree(String indent) {
        StringBuilder print = new StringBuilder();
        print.append("IDEXPR: ").append(IDToken.printTokenData());

        if (arrIndexExpr != null) {
            print.append("[").append(arrIndexExpr.printTree(indent)).append("]");
        }
        return print.toString();
    }
}
