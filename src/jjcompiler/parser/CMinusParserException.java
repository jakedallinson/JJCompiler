package jjcompiler.parser;

import jjcompiler.scanner.Token.*;

public class CMinusParserException extends Exception {
    public CMinusParserException (TokenType expectedTokenType, TokenType gotTokenType) {
        super("PARSE ERROR: expected Token " + expectedTokenType + ", got Token: " + gotTokenType);
    }
}
