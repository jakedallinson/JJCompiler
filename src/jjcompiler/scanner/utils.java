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
                Main.printWriter.printf("reserved word ", token.getType());
                break;
            case PLUS:
                Main.printWriter.printf("+");
                break;
            case MINUS:
                Main.printWriter.printf("-");
                break;
            case TIMES:
                Main.printWriter.printf("*");
                break;
            case DIVIDE:
                Main.printWriter.printf("/");
                break;
            case LT:
                Main.printWriter.printf("<");
                break;
            case GT:
                Main.printWriter.printf(">");
                break;
            case ASSIGN:
                Main.printWriter.printf("=");
                break;
            case NOTEQ:
                Main.printWriter.printf("!=");
                break;
            case SEMI:
                Main.printWriter.printf(";");
                break;
            case COMMA:
                Main.printWriter.printf(",");
                break;
            case LPAREN:
                Main.printWriter.printf("(");
                break;
            case RPAREN:
                Main.printWriter.printf(")");
                break;
            case LCURLY:
                Main.printWriter.printf("{");
                break;
            case RCURLY:
                Main.printWriter.printf("}");
                break;
            case LBRACKET:
                Main.printWriter.printf("[");
                break;
            case RBRACKET:
                Main.printWriter.printf("]");
                break;
            case LTEQ:
                Main.printWriter.printf("<=");
                break;
            case GTEQ:
                Main.printWriter.printf(">=");
                break;
            case EQ:
                Main.printWriter.printf("==");
                break;
            case NUM:
                Main.printWriter.printf("NUM, value = ", token.getType());
                break;
            case ID:
                Main.printWriter.printf("ID, name = ", token.getType());
                break;
            case ENDFILE:
                Main.printWriter.printf("** EOF **");
                break;
            case ERROR:
                Main.printWriter.printf("** ERROR **", token.getType());
                break;
            default: // Should Never Happen
                Main.printWriter.printf("**UNKNONW TOKEN**", token.getType(), token.getData());
        }
    }
}