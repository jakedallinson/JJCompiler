package jjcompiler.scanner;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class utils {
    public static char EOF;

    public static void printToken(Token token) throws IOException {

        FileWriter fileWriter = new FileWriter("c:/temp/samplefile3.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        switch (token.getType()) {
            case IF:
            case ELSE:
            case INT:
            case RETURN:
            case VOID:
            case WHILE:
                printWriter.printf("reserved word ", token.getType());
                break;
            case PLUS:
                printWriter.printf("+");
                break;
            case MINUS:
                 printWriter.printf("-");
                break;
            case TIMES:
                 printWriter.printf("*");
                break;
            case DIVIDE:
                 printWriter.printf("/");
                break;
            case LT:
                 printWriter.printf("<");
                break;
            case GT:
                 printWriter.printf(">");
                break;
            case ASSIGN:
                 printWriter.printf("=");
                break;
            case NOTEQ:
                 printWriter.printf("!=");
                break;
            case SEMI:
                 printWriter.printf(";");
                break;
            case COMMA:
                 printWriter.printf(",");
                break;
            case LPAREN:
                 printWriter.printf("(");
                break;
            case RPAREN:
                 printWriter.printf(")");
                break;
            case LCURLY:
                 printWriter.printf("{");
                break;
            case RCURLY:
                 printWriter.printf("}");
                break;
            case LBRACKET:
                 printWriter.printf("[");
                break;
            case RBRACKET:
                 printWriter.printf("]");
                break;
            case LTEQ:
                 printWriter.printf("<=");
                break;
            case GTEQ:
                 printWriter.printf(">=");
                break;
            case EQ:
                 printWriter.printf("==");
                break;
            case NUM:
                 printWriter.printf("NUM, value = ", token.getType());
                break;
            case ID:
                 printWriter.printf("ID, name = ", token.getType());
                break;
            case ENDFILE:
                 printWriter.printf("** EOF **");
                break;
            case ERROR:
                 printWriter.printf("** ERROR **", token.getType());
                break;
            default: // Should Never Happen
                 printWriter.printf("**UNKNONW TOKEN**", token.getType(), token.getData());
        }
    }
}