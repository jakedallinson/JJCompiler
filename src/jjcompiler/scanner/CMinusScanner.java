package jjcompiler.scanner;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CMinusScanner implements Scanner {

    private BufferedReader inFile;
    private Token nextToken;
    private HashMap<String, Token> reservedWords;

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
        reservedWords = setReservedWords();
    }

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("resources/example1.cm"));
        CMinusScanner scanner = new CMinusScanner(reader);

        // scanner.getNextToken();
    }

    public Token getNextToken () {
        Token returnToken = nextToken;
        if (nextToken.getType() != Token.TokenType.ENDFILE)
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

            System.out.println(getNextChar());
            System.out.println(getNextChar());
            System.out.println(getNextChar());
            ungetNextChar();
            System.out.println(getNextChar());
            System.out.println(getNextChar());
            System.out.println(getNextChar());

        } catch (IOException e) {

        }

        return new Token(Token.TokenType.ENDFILE);
    }

    // fetches the next non-blank char
    private char getNextChar() throws IOException {
        inFile.mark(0); // mark the file here before chomping, to be used in unget
        int value;
        if ((value = inFile.read()) != -1) {
            return (char) value;
        } else {
            return util.EOF;
        }
    }

    // backtracks one char
    private void ungetNextChar() throws IOException {
        inFile.reset();
    }

    private HashMap<String, Token> setReservedWords() {
        HashMap<String, Token> map = new HashMap<>();
        map.put("if", new Token(Token.TokenType.IF));
        map.put("then", new Token(Token.TokenType.THEN));
        map.put("else", new Token(Token.TokenType.ELSE));
        map.put("end", new Token(Token.TokenType.END));
        map.put("repeat", new Token(Token.TokenType.REPEAT));
        map.put("until", new Token(Token.TokenType.UNTIL));
        map.put("read", new Token(Token.TokenType.READ));
        map.put("write", new Token(Token.TokenType.WRITE));
        return map;
    }

    private boolean isReservedWord(String word) {
        return reservedWords.containsKey(word);
    }
}

