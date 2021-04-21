package jjcompiler.parser.AST;

import jjcompiler.compiler.CMinusCompilerException;
import jjcompiler.lowlevel.BasicBlock;
import jjcompiler.lowlevel.Data;
import jjcompiler.lowlevel.FuncParam;
import jjcompiler.lowlevel.Function;
import jjcompiler.scanner.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class FunDecl extends Decl {

    private ArrayList<VarDecl> paramsList;
    private Statement compoundStatement;

    public FunDecl (Token type, Token ID, ArrayList<VarDecl> params, Statement stmt) {
        typeToken = type;
        IDToken = ID;
        paramsList = params;
        compoundStatement = stmt;
    }

    @Override
    public Function genLLCode () throws CMinusCompilerException {
        int dataType;
        if (typeToken.getType() == Token.TokenType.INT) {
            dataType = Data.TYPE_INT;
        } else if (typeToken.getType() == Token.TokenType.VOID) {
            dataType = Data.TYPE_VOID;
        } else {
            throw new CMinusCompilerException("genLLCode",Token.TokenType.INT,typeToken.getType());
        }

        Function funct = new Function (dataType, IDToken.getData(),  null);
        funct.setOptimize(false);

        HashMap table = funct.getTable();

        FuncParam listParams[] = new FuncParam[0];

        FuncParam firstParam = null;
        FuncParam prevParam  = null;
        FuncParam newParam   = null;


        for (VarDecl param : paramsList) {
            if (table.containsKey(param.IDToken.getData())) {
                throw new CMinusCompilerException("FuncDecl: duplicate name found", param.IDToken.getData());
            }

            table.put(param.IDToken.getData(), funct.getNewRegNum());

            prevParam = newParam;
            newParam = new FuncParam(param.typeToken.complierType(), param.IDToken.getData(), false);

            if (firstParam == null) {
                firstParam = newParam;
            }
            if (prevParam != null) {
                prevParam.setNextParam(newParam);
            }
        }

        funct.setFirstParam(firstParam);

        funct.createBlock0();

        BasicBlock blockOne = new BasicBlock(funct);
        funct.appendBlock(blockOne);
        funct.setCurrBlock(blockOne);

        compoundStatement.genLLCode(funct);

        funct.appendBlock(funct.getReturnBlock());

        if (funct.getFirstUnconnectedBlock() != null) {
            funct.appendBlock(funct.getFirstUnconnectedBlock());
        }

        return funct;
    }

    public void genLLCode(Function funct) {
        /* SHOULDNT HAPPEN */
    }

    @Override
    public String printTree(String indent) {

        StringBuilder print = new StringBuilder();

        print.append(indent).append("FUNCTION: ");
        print.append(typeToken.printTokenData()).append(" ");
        print.append(IDToken.printTokenData()).append("\n");

        print.append(indent).append("PARAMS: ");

        if (paramsList.size() < 2) {
            print.append(paramsList.get(0).printTree(indent));
        } else {
            for (VarDecl eachParam : this.paramsList) {
                print.append(eachParam.printTree(indent)).append(", ");
            }
            print.deleteCharAt(print.length()-2);
        }

        print.append("\n");
        print.append(compoundStatement.printTree(indent + "   "));
        return print.toString();
    }
}
