package jjcompiler.compiler;

public class CMinusCompilerException extends Exception {
    public CMinusCompilerException(String functLocation, Object expectedObj, Object gotObj) {
        super("COMPILE ERROR: " + functLocation + "() expected " + expectedObj + ", got " + gotObj);
    }

    public CMinusCompilerException(String functLocation, String varName) {
        super("COMPILE ERROR: " + functLocation + "() " + varName + "Var not Declared");
    }
}
