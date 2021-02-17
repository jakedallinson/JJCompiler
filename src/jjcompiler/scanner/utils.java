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
                CMinusScanner.printWriter.printf("reserved word: " + token.getType());
                CMinusScanner.printWriter.printf("\n");
                break;
            case PLUS:
                CMinusScanner.printWriter.printf("+");
                CMinusScanner.printWriter.printf("\n");
                break;
            case MINUS:
                CMinusScanner.printWriter.printf("-");
                CMinusScanner.printWriter.printf("\n");
                break;
            case TIMES:
                CMinusScanner.printWriter.printf("*");
                CMinusScanner.printWriter.printf("\n");
                break;
            case DIVIDE:
                CMinusScanner.printWriter.printf("/");
                CMinusScanner.printWriter.printf("\n");
                break;
            case LT:
                CMinusScanner.printWriter.printf("<");
                CMinusScanner.printWriter.printf("\n");
                break;
            case GT:
                CMinusScanner.printWriter.printf(">");
                CMinusScanner.printWriter.printf("\n");
                break;
            case ASSIGN:
                CMinusScanner.printWriter.printf("=");
                CMinusScanner.printWriter.printf("\n");
                break;
            case NOTEQ:
                CMinusScanner.printWriter.printf("!=");
                CMinusScanner.printWriter.printf("\n");
                break;
            case SEMI:
                CMinusScanner.printWriter.printf(";");
                CMinusScanner.printWriter.printf("\n");
                break;
            case COMMA:
                CMinusScanner.printWriter.printf(",");
                CMinusScanner.printWriter.printf("\n");
                break;
            case LPAREN:
                CMinusScanner.printWriter.printf("(");
                CMinusScanner.printWriter.printf("\n");
                break;
            case RPAREN:
                CMinusScanner.printWriter.printf(")");
                CMinusScanner.printWriter.printf("\n");
                break;
            case LCURLY:
                CMinusScanner.printWriter.printf("{");
                CMinusScanner.printWriter.printf("\n");
                break;
            case RCURLY:
                CMinusScanner.printWriter.printf("}");
                CMinusScanner.printWriter.printf("\n");
                break;
            case LBRACKET:
                CMinusScanner.printWriter.printf("[");
                CMinusScanner.printWriter.printf("\n");
                break;
            case RBRACKET:
                CMinusScanner.printWriter.printf("]");
                CMinusScanner.printWriter.printf("\n");
                break;
            case LTEQ:
                CMinusScanner.printWriter.printf("<=");
                CMinusScanner.printWriter.printf("\n");
                break;
            case GTEQ:
                CMinusScanner.printWriter.printf(">=");
                CMinusScanner.printWriter.printf("\n");
                break;
            case EQ:
                CMinusScanner.printWriter.printf("==");
                CMinusScanner.printWriter.printf("\n");
                break;
            case NUM:
                CMinusScanner.printWriter.printf("NUM, value = " + token.getData());
                CMinusScanner.printWriter.printf("\n");
                break;
            case ID:
                CMinusScanner.printWriter.printf("ID, name = " + token.getData());
                CMinusScanner.printWriter.printf("\n");
                break;
            case ENDFILE:
                CMinusScanner.printWriter.printf("** EOF **");
                CMinusScanner.printWriter.printf("\n");
                break;
            case ERROR:
                CMinusScanner.printWriter.printf("** ERROR Unknown Symbol '" + token.getData() + "' **");
                CMinusScanner.printWriter.printf("\n");
                break;
            default: // Should Never Happen
                CMinusScanner.printWriter.printf("**UNKNONW TOKEN**" + token.getType() + token.getData());
                CMinusScanner.printWriter.printf("\n");
        }
    }
}