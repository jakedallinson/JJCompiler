package jjcompiler.main;
import jjcompiler.parser.AST.Program;
import jjcompiler.scanner.*;
import jjcompiler.parser.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static boolean EchoSource   = false;
    public static boolean TraceScan    = false;
    public static boolean TraceParse   = false;
    public static boolean TraceAnalyse = false;
    public static boolean TraceCode    = false;
    public static boolean Error        = false;

    public static PrintWriter pw;

    private final static String testCase = "ex0";

    public static void main(String[] args) throws IOException {

        for (String arg : args) {
            switch (arg) {
                case "-s":
                    EchoSource = true;
                    break;
                case "-l":
                    TraceScan = true;
                    break;
                case "-y":
                    TraceParse = true;
                    break;
                case "-a":
                    TraceAnalyse = true;
                    break;
                case "-c":
                    TraceCode = true;
                    break;
                case "-f":
                    try {
                        BufferedReader br = new BufferedReader(new FileReader("resources/" + arg.toString()));
                    } catch (IOException e) {
                        System.out.println("Error reading file.");
                    }

                    break;
                default:
                    //errorFlag++;
                    Error = true;
            }
        }

        BufferedReader br = null;
        try {
           br = new BufferedReader(new FileReader("resources/" + testCase + ".cm"));
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        createOutputFile();
        List<Token> tokens = scanner(br);
        parser(tokens);

    }

    private static void createOutputFile() {

        String outputName = "OUT.txt";

        if (EchoSource) outputName   = "EchoSource_" + outputName;
        if (TraceScan) outputName    = "TraceScan_" + outputName;
        if (TraceParse) outputName   = "TraceParse_" + outputName;
        if (TraceAnalyse) outputName = "TraceAnalyse_" + outputName;
        if (TraceCode) outputName    = "TraceCode_" + outputName;

        try {
            pw = new PrintWriter(new FileWriter(new File("resources/" + testCase + "_" + outputName)));
        } catch (IOException e) {
            System.out.println("Error creating file.");
        }
    }

    private static List<Token> scanner(BufferedReader br) throws IOException {

            List<Token> tokenList = new ArrayList<>();

            CMinusScanner scanner = new CMinusScanner(br);
            //CMinusScannerB scanner = new CMinusScannerB(br);

            while (scanner.viewNextToken().getType() != Token.TokenType.ENDFILE) {
                if (TraceScan) {
                    // advance token
                    Token nextToken = scanner.viewNextToken();
                    // add to tokenList
                    tokenList.add(nextToken);
                    // print the token

                    // String output = nextToken.printToken();
                    // System.out.println(output);
                    // pw.printf(output + "\n");
                }

                // break if error token was found, else get next token
                if (scanner.viewNextToken().getType() == Token.TokenType.ERROR) { break; }
                scanner.getNextToken();
            }
            tokenList.add(new Token(Token.TokenType.ENDFILE));
            pw.close();
        return tokenList;
    }

    private static void parser(List<Token> t) {
        CMinusParser parser = new CMinusParser(t);
        Program myProgram = parser.parse();
        myProgram.printTree();
    }
}