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

    public Token.TokenType getToken() {

//        index for storing into tokenString
        int tokenStringIndex = 0;
//        holds current token to be returned
        Token.TokenType currentToken;
//        current state - always begins at start
        FAState state = FAState.START;
//        flag to indicate save to token string
        boolean save;

        while (state != FAState.DONE) {
            char c = getNextChar();
            save = true;
            switch(state) {
                case START:
                    if (isdigit(c))                                     {state = FAState.INNUM;}

                    else if (isalpha(c))                                {state = FAState.INID;}

                    else if (c == ':')                                  {state = FAState.INASSIGN;}

                    else if ((c == ' ') || (c == '\t') || (c == '\n'))  {save = false;}

                    else if (c == '{')                                  {save = false; state = FAState.INCOMMENT;}

                    else {
                        state = FAState.DONE;

                        switch (c) {
                            case EOF:
                                save = false;
                                currentToken = Token.TokenType.ENDFILE;
                                break;
                            case '=':
                                currentToken = Token.TokenType.EQ;
                                break;
                            case '<':
                                currentToken = Token.TokenType.LT;
                                break;
                            case '+':
                                currentToken = Token.TokenType.PLUS;
                                break;
                            case '-':
                                currentToken = Token.TokenType.MINUS;
                                break;
                            case '*':
                                currentToken = Token.TokenType.TIMES;
                                break;
                            case '/':
                                currentToken = Token.TokenType.OVER;
                                break;
                            case '(':
                                currentToken = Token.TokenType.LPAREN;
                                break;
                            case ')':
                                currentToken = Token.TokenType.RPAREN;
                                break;
                            case ';':
                                currentToken = Token.TokenType.SEMI;
                                break;
                            default:
                                currentToken = Token.TokenType.ERROR;
                                break;
                        }
                    }
                    break;

                case INCOMMENT:
                    save = false;
                    if (c == '}') {state = FAState.START;}
                    break;

                case INASSIGN:
                    state = FAState.DONE;
                    if (c == '=') {
                        currentToken = Token.TokenType.ASSIGN;
                    } else {
                        ungetNextChar();
                        save = false;
                        currentToken = Token.TokenType.ERROR;
                    }
                    break;

                case INNUM:
                    if (!isdigit(c)) {
                        ungetNextChar();
                        save = false;
                        state = FAState.DONE;
                        currentToken = Token.TokenType.NUM;
                    }
                    break;

                case INID:
                    if (!isalpha(c)) {
                        ungetNextChar();
                        save = false;
                        state = FAState.DONE;
                        currentToken = Token.TokenType.ID;
                    }
                    break;

                case DONE:
                default: //should never happen
                    System.out.println("Scanner Bug: state = " + state);
                    state = FAState.DONE;
                    currentToken = Token.TokenType.ERROR;
                    break;
            }
            if ((save) && (tokenStringIndex <= MAXTOKENLEN)) {
                tokenString[tokenStringIndex++] = c;
            }
            if (state == FAState.DONE) {
                tokenString[tokenStringIndex] = '\0';
                if (currentToken == ID){
                    currentToken = reserveLookup(tokenString);
                }
            }
        }
        if (TraceScan) {
            System.out.println(lineno);
            printToken(currentToken, tokenString);
        }
        return currentToken;
    }

}

