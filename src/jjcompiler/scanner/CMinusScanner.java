package jjcompiler.scanner;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Character;

import static jjcompiler.scanner.util.*;

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
        DONE
    }

    public CMinusScanner (BufferedReader file) {
        inFile = file;
        nextToken = scanToken();
        reservedWords = setReservedWords();
    }

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("resources/ex1.cm"));
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

    // fetches the next non-blank char
    private char getNextChar() throws IOException {
        inFile.mark(0); // mark the file here before chomping, to be used in unget
        int value;
        if ((value = inFile.read()) != -1) {
            return (char) value;
        } else {
            return EOF;
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
        map.put("while", new Token(Token.TokenType.WHILE));
        map.put("int", new Token(Token.TokenType.INT));
        map.put("void", new Token(Token.TokenType.VOID));
        map.put("return", new Token(Token.TokenType.RETURN));
        return map;
    }
    
    private boolean isReservedWord(String word) {
        return reservedWords.containsKey(word);
    }

    private Token getReservedWordToken(String word) {
        return reservedWords.get(word);
    }

    private Token scanToken() {
        try {

    //        index for storing into tokenString
            int tokenStringIndex = 0;
    //        holds current token to be returned
            Token currentToken = new Token();
    //        current state - always begins at star);
            FAState state = FAState.START;
    //        flag to indicate save to token string
            boolean save;

            while (state != FAState.DONE) {
                char c = getNextChar();
                save = true;
                switch(state) {
                    case START:
                        if (Character.isDigit(c))
                            {state = FAState.INNUM;}

                        else if (Character.isAlphabetic(c))
                            {state = FAState.INID;}

                        else if (c == ':')
                            {state = FAState.INASSIGN;}

                        else if ((c == ' ') || (c == '\t') || (c == '\n'))
                            {save = false;}

                        else if (c == '{')
                            {save = false; state = FAState.INCOMMENT;}

                        else {
                            state = FAState.DONE;
                            switch (c) {
                                case '\0':
                                    save = false;
                                    currentToken.setTokenType(Token.TokenType.ENDFILE);
                                    break;
                                case '+':
                                    currentToken.setTokenType(Token.TokenType.PLUS);
                                    break;
                                case '-':
                                    currentToken.setTokenType(Token.TokenType.MINUS);
                                    break;
                                case '*':
                                    currentToken.setTokenType(Token.TokenType.TIMES);
                                    break;
                                case '/':
                                    currentToken.setTokenType(Token.TokenType.OVER);
                                    break;
                                case '<':
                                    currentToken.setTokenType(Token.TokenType.LT);
                                    break;
                                case '>':
                                    currentToken.setTokenType(Token.TokenType.GT);
                                    break;
                                case ',':
                                    currentToken.setTokenType(Token.TokenType.COMMA);
                                    break;
                                case '(':
                                    currentToken.setTokenType(Token.TokenType.LPAREN);
                                    break;
                                case ')':
                                    currentToken.setTokenType(Token.TokenType.RPAREN);
                                    break;
                                case '[':
                                    currentToken.setTokenType(Token.TokenType.LBRACK);
                                    break;
                                case ']':
                                    currentToken.setTokenType(Token.TokenType.RBRACK);
                                    break;
                                case ';':
                                    currentToken.setTokenType(Token.TokenType.SEMI);
                                    break;
                                case '{':
                                    currentToken.setTokenType(Token.TokenType.LCURLY);
                                    break;
                                case '}':
                                    currentToken.setTokenType(Token.TokenType.RCURLY);
                                    break;
                                default:
                                    currentToken.setTokenType(Token.TokenType.ERROR);
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
                            currentToken.setTokenType(Token.TokenType.ASSIGN);
                        } else {
                            ungetNextChar();
                            save = false;
                            currentToken.setTokenType(Token.TokenType.ERROR);
                        }
                        break;

                    case INNUM:
                        if (!Character.isDigit(c)) {
                            ungetNextChar();
                            save = false;
                            state = FAState.DONE;
                            currentToken.setTokenType(Token.TokenType.NUM);
                        }
                        break;

                    case INID:
                        if (!Character.isAlphabetic(c)) {
                            ungetNextChar();
                            save = false;
                            state = FAState.DONE;
                            currentToken.setTokenType(Token.TokenType.ID);
                        }
                        break;

                    case DONE:
                    default: //should never happen
                        System.out.println("Scanner Bug: state = " + state);
                        state = FAState.DONE;
                        currentToken.setTokenType(Token.TokenType.ERROR);
                        break;
                }
    //            if ((save) && (tokenStringIndex <= MAXTOKENLEN)) {
    //                tokenString[tokenStringIndex++] = c;
    //            }
    //            if (state == FAState.DONE) {
    //                tokenString[tokenStringIndex] = '\0';
    //                if (currentToken == Token.TokenType.ID){
    //                    currentToken.setTokenType(reserveLookup(tokenString));
    //                }
    //            }
            }
    //            TRACE FLAG p503
    //        if (TraceScan) {
    //            System.out.println(lineno);
    //            printToken(currentToken, tokenString);
    //        }

        } catch (IOException e) {



        }
        // return currentToken;
        return new Token(Token.TokenType.ENDFILE);
    }
}

