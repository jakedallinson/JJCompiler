package jjcompiler.main;
import jjcompiler.parser.AST.Program;
import jjcompiler.parser.CMinusParser;
import jjcompiler.parser.CMinusParserException;
import jjcompiler.scanner.CMinusScanner;
import jjcompiler.scanner.Token;

import java.io.*;

public class Main {

    public static boolean TraceScan = false;
    public static boolean TraceParse = false;
    public static boolean Error = false;

    public static PrintWriter pw;

    private final static String testCase = "ex3";

    public static void main(String[] args) throws IOException, CMinusParserException {

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("resources/" + testCase + ".cm"));
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        for (String arg : args) {
            switch (arg) {
                case "-s":
                    scanner(br);
                    TraceScan = true;
                    createOutputFile();
                    break;
                case "-p":
                    // Scan -> Parse
                    TraceParse = true;
                    createOutputFile();
                    parser(br);
                    break;
                case "-f":
                    // TODO File Options FROM ARGS
                    break;
                default:
                    //errorFlag++;
                    Error = true;
            }
        }
    }

    private static void createOutputFile() {

        String outputName = "OUT.txt";

        if (TraceScan) outputName    = "TraceScan_" + outputName;
        if (TraceParse) outputName   = "TraceParse_" + outputName;

        try {
            pw = new PrintWriter(new FileWriter(new File("resources/" + testCase + "_" + outputName)));
        } catch (IOException e) {
            System.out.println("Error creating file.");
        }
    }

    public static void scanner(BufferedReader br) throws IOException{

            CMinusScanner myScanner = new CMinusScanner(br);
            //CMinusScannerB myScanner = new CMinusScannerB(br);

            while (myScanner.viewNextToken().getType() != Token.TokenType.ENDFILE) {
                if (TraceScan) {
                    // advance token
                    Token nextToken = myScanner.viewNextToken();

                    // print the token
                    String output = nextToken.printToken();
                    System.out.println(output);
                    pw.printf(output + "\n");
                }

                // break if error token was found, else get next token
                if (myScanner.viewNextToken().getType() == Token.TokenType.ERROR) {
                    break;
                }
                myScanner.getNextToken();
            }
            if (!TraceParse) {
                pw.close();
            }
    }

    public static void parser(BufferedReader br) throws IOException, CMinusParserException {
        // TODO: notice hardcode test file
        CMinusParser parser = new CMinusParser("ex0");
        Program myProgram = parser.parse();

        if (TraceParse) {
            String printTree = myProgram.printTree();
            pw.printf(printTree);
            pw.close();
        }
    }
}