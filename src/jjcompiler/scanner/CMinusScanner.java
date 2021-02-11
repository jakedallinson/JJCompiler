package jjcompiler.scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CMinusScanner implements Scanner {

    private BufferedReader inFile;
    private Token nextToken;

    private enum FAState {
        START,
        INCOMMENT,
        INNUM,
        INID,
        INASSIGN,
        DONE,
        ERROR
    }

    public CMinusScanner (BufferedReader file) {
        inFile = file;
        nextToken = scanToken();
    }

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("resources/example1.cm"));
        CMinusScanner scanner = new CMinusScanner(reader);

        System.out.println(scanner.getNextToken().getType());
    }

    public Token getNextToken () {
        Token returnToken = nextToken;
        if (nextToken.getType() != Token.TokenType.EOF_TOKEN)
            nextToken = scanToken();
        return returnToken;
    }
    public Token viewNextToken () {
        return nextToken;
    }
    // PROJ 1: IMPLEMENT THIS FUNCT
    private Token scanToken () {
        try {
            FAState state = FAState.START;
            StringBuilder content = new StringBuilder();

            int value;
            while (state != FAState.START) {

            }
            while ((value = inFile.read()) != -1) {
                char nextChar = (char) value;

                content.append(nextChar);
            }

            // System.out.println(content.toString());
        } catch (IOException e) {

        }

        return new Token(Token.TokenType.EOF_TOKEN);
    }
}

