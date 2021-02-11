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

        return new Token(Token.TokenType.ENDFILE);
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

