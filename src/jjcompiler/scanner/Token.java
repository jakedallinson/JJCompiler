package jjcompiler.scanner;

public class Token {

    public enum TokenType {
        // book-keeping tokens
        ENDFILE, ERROR,
        // keywords (reserved words)
        ELSE, IF, INT, RETURN, VOID, WHILE,
        // special symbols
        PLUS, MINUS, TIMES, DIVIDE, LT, LTEQ, GT, GTEQ, EQ, NOTEQ, ASSIGN, SEMI, COMMA, LPAREN, RPAREN, LBRACKET, RBRACKET, LCURLY, RCURLY, LCOMMA, RCOMMA,
        // multicharacter tokens
        ID, NUM
    }

    private TokenType tokenType;
    private String tokenData;

    public Token () {this (null, null);}
    public Token (TokenType type) {
        this (type, null);
    }
    public Token (TokenType type, String data) {
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
    public void setTokenData (String data) {
        tokenData = data;
    }

    public void appendTokenData (char data) {
        tokenData += Character.toString(data);
    }
}
