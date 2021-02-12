package jjcompiler.scanner;

public class Token {

    public enum TokenType {
        // book-keeping tokens
        ENDFILE, ERROR,

        // reserved words
        IF, THEN, ELSE, END, REPEAT, UNTIL, READ, WRITE,

        // multicharacter tokens
        ID, NUM,

        // special symbols
        ASSIGN, LT, EQ, LPAREN, RPAREN, SEMI, PLUS,
        MINUS, TIMES, OVER
    }

    private TokenType tokenType;
    private Object tokenData;

    public Token () {this (null, null);}
    public Token (TokenType type) {
        this (type, null);
    }
    public Token (TokenType type, Object data) {
        tokenType = type;
        tokenData = data;
    }
    public TokenType getType () {
        return tokenType;
    }
    public Object getData () {
        return tokenData;
    }
    public void setTokenType (TokenType type) {
        tokenType = type;
    }
    public void setTokenData (Object data) {
        tokenData = data;
    }
}
