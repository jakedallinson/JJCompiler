package jjcompiler.parser;

import jjcompiler.parser.AST.*;
import jjcompiler.scanner.*;
import java.io.IOException;

public interface Parser {
    public Program parse() throws IOException;
    public void advanceToken() throws IOException;
    public void matchToken(Token token) throws CMinusParserException;
}
