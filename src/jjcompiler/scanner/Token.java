package jjcompiler.scanner;

public class Token {

    public enum TokenType {
        // book-keeping tokens
        ENDFILE, ERROR,
        // keywords (reserved words)
        ELSE, IF, INT, RETURN, VOID, WHILE,
        // special symbols
        PLUS, MINUS, TIMES, DIVIDE, LT, LTEQ, GT, GTEQ, EQ, NOTEQ, ASSIGN, SEMI, COMMA, LPAREN, RPAREN, LBRACK, RBRACK, LCURLY, RCURLY, LCOMM, RCOMM,
        // multicharacter tokens
        ID, NUM
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
