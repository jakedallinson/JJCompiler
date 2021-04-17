package jjcompiler.compiler;

import jjcompiler.parser.AST.Program;
import jjcompiler.x64codegen.X64AssemblyGenerator;
import jjcompiler.parser.*;
import jjcompiler.lowlevel.*;
import jjcompiler.optimizer.*;
import jjcompiler.x86codegen.*;
import jjcompiler.x64codegen.*;
import jjcompiler.dataflow.*;

import java.util.*;
import java.io.*;

public class CMinusCompiler implements Compiler {

    public static HashMap globalHash = new HashMap();
    private static boolean genX64Code = false;

    public CMinusCompiler() { }

    public static void setGenX64Code(boolean useX64) {
        genX64Code = useX64;
    }
    public static boolean getGenX64Code() {
        return genX64Code;
    }

    public void compile (String filePrefix) {

        String fileName = filePrefix + ".cm";
        try {
            Parser myParser = new CMinusParser(fileName);

            Program myProgram = myParser.parse();
            // myParser.printAST(parseTree);

            CodeItem LLC = myProgram.genLLCode();

            fileName = filePrefix + ".ll";
            PrintWriter outFile =
                    new PrintWriter(new BufferedWriter(new FileWriter("output/" + fileName)));
            LLC.printLLCode(outFile);
            outFile.close();

            int optiLevel = 2;
            LowLevelCodeOptimizer lowLevelOpti =
                    new LowLevelCodeOptimizer(LLC, optiLevel);
            lowLevelOpti.optimize();

            fileName = filePrefix + ".opti";
            outFile =
                    new PrintWriter(new BufferedWriter(new FileWriter("output/" + fileName)));
            LLC.printLLCode(outFile);
            outFile.close();

            if (genX64Code) {
                X64CodeGenerator x64gen = new X64CodeGenerator(LLC);
                x64gen.convertToX64();
            }
            else {
                X86CodeGenerator x86gen = new X86CodeGenerator(LLC);
                x86gen.convertToX86();
            }
            fileName = filePrefix + ".x86";
            outFile =
                    new PrintWriter(new BufferedWriter(new FileWriter("output/" + fileName)));
            LLC.printLLCode(outFile);
            outFile.close();

//    lowLevelCode.printLLCode(null);

            // simply walks functions and finds in and out edges for each BasicBlock
            ControlFlowAnalysis cf = new ControlFlowAnalysis(LLC);
            cf.performAnalysis();
//    cf.printAnalysis(null);

            // performs DU analysis, annotating the function with the live range of
            // the value defined by each oper (some merging of opers which define
            // same virtual register is done)
//    DefUseAnalysis du = new DefUseAnalysis(lowLevelCode);
//    du.performAnalysis();
//    du.printAnalysis();

            LivenessAnalysis liveness = new LivenessAnalysis(LLC);
            liveness.performAnalysis();
            liveness.printAnalysis();

            if (genX64Code) {
                int numRegs = 15;
                X64RegisterAllocator regAlloc = new X64RegisterAllocator(LLC,
                        numRegs);
                regAlloc.performAllocation();

                LLC.printLLCode(null);

                fileName = filePrefix + ".s";
                outFile =
                        new PrintWriter(new BufferedWriter(new FileWriter("output/" + fileName)));
                X64AssemblyGenerator assembler =
                        new X64AssemblyGenerator(LLC, outFile);
                assembler.generateX64Assembly();
                outFile.close();
            }
            else {
                int numRegs = 7;
                X86RegisterAllocator regAlloc = new X86RegisterAllocator(LLC,
                        numRegs);
                regAlloc.performAllocation();

                LLC.printLLCode(null);

                fileName = filePrefix + ".s";
                outFile =
                        new PrintWriter(new BufferedWriter(new FileWriter("output/" + fileName)));
                X86AssemblyGenerator assembler =
                        new X86AssemblyGenerator(LLC, outFile);
                assembler.generateAssembly();
                outFile.close();
            }

        } catch (IOException | CMinusParserException | CMinusCompilerException ioe) {
        }

    }

    public static void main(String[] args) {
        String filePrefix = "ex0";
        CMinusCompiler myCompiler = new CMinusCompiler();
        myCompiler.setGenX64Code(true);
        myCompiler.compile(filePrefix);
    }
}
