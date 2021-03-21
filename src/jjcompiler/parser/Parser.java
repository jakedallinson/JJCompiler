package jjcompiler.parser;

import jjcompiler.parser.AST.Program;
import jjcompiler.scanner.Token;

import java.io.IOException;

public interface Parser {
    public Program parse() throws CMinusParserException, IOException;
    public Token advanceToken() throws IOException;
    public void matchToken(Token.TokenType expected) throws CMinusParserException, IOException;
}
