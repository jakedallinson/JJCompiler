package jjcompiler.scanner;

public class Token {

    public enum TokenType {
        // book-keeping tokens
        ENDFILE, ERROR,
        // keywords (reserved words)
        ELSE, IF, INT, RETURN, VOID, WHILE,
        // special symbols
        PLUS, MINUS, TIMES, DIVIDE, LT, GT, EQ, ASSIGN, SEMI, COMMA, LPAREN, RPAREN, LBRACKET, RBRACKET, LCURLY, RCURLY,
        // multicharacter tokens
        ID, NUM, LTEQ, NOTEQ, GTEQ,
    }

    private TokenType tokenType;
    private String tokenData;

    public Token () {this (null, "");}
    public Token (TokenType type) {
        this (type, "");
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

    public void munchTokenData () {
        if ((tokenData != null) && (tokenData.length() > 0)) {
            tokenData = tokenData.substring(0, tokenData.length() - 1);
        }
    }

    public void appendTokenData (char data) {
        tokenData += Character.toString(data);
    }
}
