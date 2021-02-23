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

    // Constructors

    public Token () {this (null, "");}
    public Token (TokenType type) {
        this (type, "");
    }
    public Token (TokenType type, String data) {
        tokenType = type;
        tokenData = data;
    }

    // Getters and Setters

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

    public String printToken() {
        String output;
        switch (tokenType) {
            case ELSE, IF, INT, RETURN, VOID, WHILE:
                output = "reserved word: " + tokenType;
                break;
            case PLUS, MINUS, TIMES, DIVIDE, LT, GT, EQ, ASSIGN, SEMI, COMMA, LPAREN, RPAREN, LBRACKET, RBRACKET, LCURLY, RCURLY:
                output = tokenData;
                break;
            case NUM:
                output = "NUM: " + tokenData;
                break;
            case ID:
                output = "ID: " + tokenData;
                break;
            case ENDFILE:
                output = "*** EOF ***";
                break;
            case ERROR:
                output = "*** ERROR: Unknown Symbol '" + tokenData + "' ***";
                break;
            default: // Should Never Happen
                output = "*** ERROR: Unknown Token ***";
        }
        return output;
    }
}
