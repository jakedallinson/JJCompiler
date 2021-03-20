/**
 * CMinusScanner implements the scanner interface for scanning C- (*.cm) files
 */

package jjcompiler.scanner;

import java.io.*;
import java.util.HashMap;
import java.lang.Character;

import static jjcompiler.scanner.Utils.*;

public class CMinusScanner implements Scanner {

    public static Console printWriter;
    private Token nextToken;
    private final BufferedReader inFile;
    private final HashMap<String, Token> reservedWords = setReservedWords();

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
        DONE
    }


    /**
     * constructor
     */
    public CMinusScanner(BufferedReader file) throws IOException {
        inFile = file;
        nextToken = scanToken();
    }

    /**
     * function munches next token
     */
    public Token getNextToken() throws IOException {
        Token returnToken = nextToken;
        if (nextToken.getType() != Token.TokenType.ENDFILE)
            nextToken = scanToken();
        return returnToken;
    }

    /**
     * function peeks at next token without munching
     */
    public Token viewNextToken() {
        return nextToken;
    }

    /**
     * function uses filereader to scan the next token
     */
    public Token scanToken() throws IOException {
        Token currentToken = new Token();
        // current state in DFA, begins at START
        FAState state = FAState.START;
        // flag to indicate whether to save char to token string
        boolean save;

        while (state != FAState.DONE) {
            char c = getNextChar();
            save = true;

            // main switch statement
            switch(state) {
                // we are on a blank-character
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
                        // these cases are all single char special symbols
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

                // we found an =, check for ==
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

                // we found an */, check for chars to stay in comment
                case INCOMMENT:
                    save = false;
                    if (c == '*')
                        state = FAState.INCONTIUNECOMMENT;
                    break;

                // we found an */.../, check what to do
                case INCONTIUNECOMMENT:
                    save = false;
                    if (c == '/')
                        state = FAState.START;
                    else if (c == '*')
                        state = FAState.INCONTIUNECOMMENT;
                    else
                        state = FAState.INCOMMENT;
                    break;

                // we found a !, check for =
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

                // we found a <, check for =
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

                // we found a >, check for =
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

                // we found a /, check for *
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

                // we're working on a number, check for more digits
                case INNUM:
                    if (Character.isAlphabetic(c)) {
                        state = FAState.DONE;
                        currentToken.setTokenType(Token.TokenType.ERROR);
                    } else if (!Character.isDigit(c)) {
                        ungetNextChar();
                        save = false;
                        state = FAState.DONE;
                        currentToken.setTokenType(Token.TokenType.NUM);
                    }
                    break;

                // we're working on an identifier, check for more letters
                case INID:
                    if (Character.isDigit(c)) {
                        state = FAState.DONE;
                        currentToken.setTokenType(Token.TokenType.ERROR);
                    } else if (!Character.isAlphabetic(c)) {
                        ungetNextChar();
                        save = false;
                        state = FAState.DONE;
                        currentToken.setTokenType(Token.TokenType.ID);
                    }
                    break;

                case DONE:
                default: //should never happen
                    System.out.println("** ERROR Scanner: state = " + state + " **");
                    state = FAState.DONE;
                    currentToken.setTokenType(Token.TokenType.ERROR);
                    break;
            }

            // if save flag is true, add the char to the token data
            if ((save)) {
                currentToken.appendTokenData(c);
            }

            // when done, check for reserved word then exit out of DFA
            if (state == FAState.DONE) {
                if (currentToken.getType() == Token.TokenType.ID && isReservedWord((String) currentToken.getData())) {
                    currentToken = getReservedWordToken((String) currentToken.getData());
                }
            }

        }

        return currentToken;
    }

    /**
     * function gets the next non-blank char
     */
    private char getNextChar() throws IOException {
        inFile.mark(0); // mark the file here before chomping, to be used in unget
        int value;
        if ((value = inFile.read()) != -1) {
            return (char) value;
        } else {
            return EOF;
        }
    }

    /**
     * function backtracks one char in filereader
     */
    private void ungetNextChar() throws IOException {
        inFile.reset();
    }

    /**
     * function sets reserved words attribute as hash map of tokens with
     * the key as their lexical name
     */
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

    /**
     * function checks if word is a reserved word
     */
    private boolean isReservedWord(String word) {
        return reservedWords.containsKey(word);
    }

    /**
     * function returns token from the reserved words that matches a word
     */
    private Token getReservedWordToken(String word) {
        return reservedWords.get(word);
    }
}