package jjcompiler.parser;

import jjcompiler.scanner.Token.*;

public class CMinusParserException extends Exception {
    public CMinusParserException(String functLocation, TokenType expectedTokenType, TokenType gotTokenType) {
        super("PARSE ERROR: " + functLocation + "() expected " + expectedTokenType + ", got " + gotTokenType);
    }
}
