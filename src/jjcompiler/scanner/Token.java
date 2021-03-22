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
            case IF:
                output = "reserved word: " + tokenType;
                break;
            case RETURN:
                output = "reserved word: " + tokenType;
                break;
            case VOID:
                output = "reserved word: " + tokenType;
                break;
            case ELSE:
                output = "reserved word: " + tokenType;
                break;
            case INT:
                output = "reserved word: " + tokenType;
                break;
            case WHILE:
                output = "reserved word: " + tokenType;
                break;
            case PLUS:
                output = tokenData;
                break;
            case MINUS:
                output = tokenData;
                break;
            case TIMES:
                output = tokenData;
                break;
            case DIVIDE:
                output = tokenData;
                break;
            case LT:
                output = tokenData;
                break;
            case GT:
                output = tokenData;
                break;
            case EQ:
                output = tokenData;
                break;
            case ASSIGN:
                output = tokenData;
                break;
            case SEMI:
                output = tokenData;
                break;
            case COMMA:
                output = tokenData;
                break;
            case LPAREN:
                output = tokenData;
                break;
            case RPAREN:
                output = tokenData;
                break;
            case LBRACKET:
                output = tokenData;
                break;
            case RBRACKET:
                output = tokenData;
                break;
            case LCURLY:
                output = tokenData;
                break;
            case RCURLY:
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

    public String printTokenData() {
        String output;
        switch (tokenType) {
            case IF:
                output = tokenType.toString();
                break;
            case RETURN:
                output = tokenType.toString();
                break;
            case VOID:
                output = tokenType.toString();
                break;
            case ELSE:
                output = tokenType.toString();
                break;
            case INT:
                output = tokenType.toString();
                break;
            case WHILE:
                output = tokenType.toString();
                break;
            default:
                output = tokenData;
        }
        return output;
    }
}
