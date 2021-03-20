package jjcompiler.parser;

import jjcompiler.scanner.CMinusScanner;
import jjcompiler.scanner.Token;
import jjcompiler.scanner.Token.TokenType;



import java.util.List;

public class CMinusParser implements Parser{

    private final List<Token> tokens;
    private int current = 0;

    /**
     * constructor
     */
    public CMinusParser(List<Token> tList) {
        tokens = tList;
    }


    /**
     * Token Handlers
     */
    private Token currentToken () {
        return tokens.get(current);
    }

    private Token nextToken() {

        if (tokens.size() > current) {
            current++;
        }

        return tokens.get(current);
    }

    private Token viewNextToken() {
        return tokens.get(current++);
    }



    public void parse(){

        while (currentToken().getType() != TokenType.ENDFILE) {

            System.out.println(currentToken().printToken());
            nextToken();
        }
        System.out.println(currentToken().printToken());
    }

    public void printTree() {



    }
}
