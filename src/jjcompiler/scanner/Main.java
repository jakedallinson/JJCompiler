package jjcompiler.scanner;

import java.io.*;

public class Main {

    public static FileWriter fileWriter;
    public static PrintWriter printWriter;
    public static BufferedReader fileReader;

    // Manuel Enable Trace till TODO: Menu is built
    public static boolean TraceScan = true;

    public static void main(String[] args) throws FileNotFoundException, IOException {


        fileReader = new BufferedReader(new FileReader("resources/ex1.cm"));
        CMinusScanner scanner = new CMinusScanner();

        fileWriter = new FileWriter("ex1OUT.txt");
        printWriter = new PrintWriter(fileWriter);


        if (CMinusScanner.scanToken().getType() != Token.TokenType.ENDFILE) {
            // Build and Output Lex
            while (true) {
                if (CMinusScanner.scanToken().getType() == Token.TokenType.ENDFILE) break;
                // Build and Output Lex
            }
        }
    }
}
