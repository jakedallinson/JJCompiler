package jjcompiler.parser;

import jjcompiler.parser.AST.*;
import jjcompiler.scanner.*;
import java.io.BufferedReader;
import java.io.IOException;

public class CMinusParser implements Parser {

    private CMinusScanner scanner;

    public CMinusParser (BufferedReader file) throws IOException {
        scanner = new CMinusScanner(file);
    }

    public Program parse() throws IOException {
        Program program = new Program();

        while (scanner.viewNextToken().getType() != Token.TokenType.ENDFILE) {
            // TODO
            advanceToken();
        }
        return program;
    }

    public void advanceToken() throws IOException {
        scanner.getNextToken();
    }

    public void matchToken(Token token) throws CMinusParserException {
        if (token.getType() != scanner.viewNextToken().getType()) {
            throw new CMinusParserException("PARSE ERROR: Expected Token " + scanner.viewNextToken().getType() + ", got " + token.getType());
        }
    }
}
