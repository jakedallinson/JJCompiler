package jjcompiler.scanner;

import java.io.*;
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
        INCONTIUNECOMMENT,
        INNUM,
        INID,
        INASSIGN,
        INDIVIDE,
        INNOTEQ,
        INLT,
        INGT,
        //INEQ,
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
        map.put("else", new Token(Token.TokenType.ELSE));
        map.put("if", new Token(Token.TokenType.IF));
        map.put("int", new Token(Token.TokenType.INT));
        map.put("return", new Token(Token.TokenType.RETURN));
        map.put("void", new Token(Token.TokenType.VOID));
        map.put("while", new Token(Token.TokenType.WHILE));
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
                            state = FAState.INNUM;

                        else if (Character.isAlphabetic(c))
                            state = FAState.INID;

                        else if (c == '/')
                            state = FAState.INDIVIDE;

                        else if (c == '!')
                            state = FAState.INNOTEQ;

                        else if (c == '<')
                            state = FAState.INLT;

                        else if (c == '>')
                            state = FAState.INGT;

                        else if (c == '=')
                            state = FAState.INASSIGN;

                        else if ((c == '\n') || (c == '\t') || (c == ' '))
                            save = false;

                        else {

                            state = FAState.DONE;

                            switch (c) {
                                case EOF:
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
                                case ';':
                                    currentToken.setTokenType(Token.TokenType.SEMI);
                                    break;
                                case ',':
                                    currentToken.setTokenType(Token.TokenType.COMMA);
                                    break;
                                case '[':
                                    currentToken.setTokenType(Token.TokenType.LBRACKET);
                                    break;
                                case ']':
                                    currentToken.setTokenType(Token.TokenType.RBRACKET);
                                    break;
                                case '(':
                                    currentToken.setTokenType(Token.TokenType.LPAREN);
                                    break;
                                case ')':
                                    currentToken.setTokenType(Token.TokenType.RPAREN);
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

                    case INASSIGN:
                        state = FAState.DONE;
                        if (c == '=') {
                            currentToken.setTokenType(Token.TokenType.EQ);
                        } else {
                            // check for GT/ LT so unmunch.
                            ungetNextChar();
                            save = false;
                            currentToken.setTokenType(Token.TokenType.ASSIGN);
                        }
                        break;

                    case INCOMMENT:
                        save = false;

                        if (c == '*')
                            state = FAState.INCONTIUNECOMMENT;
                        break;

                    case INCONTIUNECOMMENT:
                        save = false;

                        if (c == '/')
                            state = FAState.START;

                        else if (c == '*')
                            state = FAState.INCONTIUNECOMMENT;

                        else
                            state = FAState.INCOMMENT;
                        break;

                    case INNOTEQ:
                        state = FAState.DONE;

                        if (c == '=')
                            currentToken.setTokenType(Token.TokenType.NOTEQ);
                        else {
                            ungetNextChar();
                            save = false;
                            currentToken.setTokenType(Token.TokenType.ERROR);
                        }
                        break;

                    case INLT:
                        state = FAState.DONE;

                        if (c == '=')
                            currentToken.setTokenType(Token.TokenType.LTEQ);
                        else {
                            ungetNextChar();
                            save = false;
                            currentToken.setTokenType(Token.TokenType.LT);
                        }
                        break;

                    case INGT:
                        state = FAState.DONE;

                        if (c == '=')
                            currentToken.setTokenType(Token.TokenType.GTEQ);
                        else {
                            ungetNextChar();
                            save = false;
                            currentToken.setTokenType(Token.TokenType.GT);
                        }
                        break;

                    case INDIVIDE:
                        if (c == '*') {
                            save = false;
                            state = FAState.INCOMMENT;
                            currentToken.munchTokenData();
                        } else {
                            ungetNextChar();
                            save = false;
                            state = FAState.DONE;
                            currentToken.setTokenType(Token.TokenType.DIVIDE);
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

                if ((save)) {
                    currentToken.appendTokenData(c);
                }

                if (state == FAState.DONE) {
                    currentToken.appendTokenData('\0');
                    if (currentToken.getType() == Token.TokenType.ID && isReservedWord((String) currentToken.getData())) {
                        currentToken = getReservedWordToken((String) currentToken.getData());
                    }
                }
            }
    //            TRACE FLAG p503
    //        if (TraceScan) {
    //            System.out.println(lineno);
    //            printToken(currentToken);
    //        }

        } catch (IOException e) {

            //catch
        }
        // return currentToken;
        return new Token(Token.TokenType.ENDFILE);
    }
}

