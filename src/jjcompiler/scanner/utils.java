package jjcompiler.scanner;
import java.io.IOException;


public class utils {

    public static final char EOF = '\0';

    public static void printToken(Token token) throws IOException {

        switch (token.getType()) {
            case IF:
            case ELSE:
            case INT:
            case RETURN:
            case VOID:
            case WHILE:
                CMinusScanner.printWriter.printf("reserved word ", token.getType());
                break;
            case PLUS:
                CMinusScanner.printWriter.printf("+");
                break;
            case MINUS:
                CMinusScanner.printWriter.printf("-");
                break;
            case TIMES:
                CMinusScanner.printWriter.printf("*");
                break;
            case DIVIDE:
                CMinusScanner.printWriter.printf("/");
                break;
            case LT:
                CMinusScanner.printWriter.printf("<");
                break;
            case GT:
                CMinusScanner.printWriter.printf(">");
                break;
            case ASSIGN:
                CMinusScanner.printWriter.printf("=");
                break;
            case NOTEQ:
                CMinusScanner.printWriter.printf("!=");
                break;
            case SEMI:
                CMinusScanner.printWriter.printf(";");
                break;
            case COMMA:
                CMinusScanner.printWriter.printf(",");
                break;
            case LPAREN:
                CMinusScanner.printWriter.printf("(");
                break;
            case RPAREN:
                CMinusScanner.printWriter.printf(")");
                break;
            case LCURLY:
                CMinusScanner.printWriter.printf("{");
                break;
            case RCURLY:
                CMinusScanner.printWriter.printf("}");
                break;
            case LBRACKET:
                CMinusScanner.printWriter.printf("[");
                break;
            case RBRACKET:
                CMinusScanner.printWriter.printf("]");
                break;
            case LTEQ:
                CMinusScanner.printWriter.printf("<=");
                break;
            case GTEQ:
                CMinusScanner.printWriter.printf(">=");
                break;
            case EQ:
                CMinusScanner.printWriter.printf("==");
                break;
            case NUM:
                CMinusScanner.printWriter.printf("NUM, value = ", token.getType());
                break;
            case ID:
                CMinusScanner.printWriter.printf("ID, name = ", token.getType());
                break;
            case ENDFILE:
                CMinusScanner.printWriter.printf("** EOF **");
                break;
            case ERROR:
                CMinusScanner.printWriter.printf("** ERROR **", token.getType());
                break;
            default: // Should Never Happen
                CMinusScanner.printWriter.printf("**UNKNONW TOKEN**", token.getType(), token.getData());
        }
    }
}