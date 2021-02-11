package jjcompiler.scanner;

public class Token {

    public enum TokenType {
        IF,
        THEN,
        ELSE,
        END,
        REPEAT,
        UNTIL,
        READ,
        WRITE,
        ASSIGN,
        LT,
        EQ,
        LPAREN,
        RPAREN,
        SEMI,
        PLUS,
        MINUS,
        TIMES,
        OVER,
        ENDFILE,
        NUM,
        ID,
        ERROR
    }

    private TokenType tokenType;
    private Object tokenData;

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
}
