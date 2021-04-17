package jjcompiler.compiler;

public class CMinusCompilerException extends Exception {
    public CMinusCompilerException(String functLocation, Object expectedObj, Object gotObj) {
        super("COMPILE ERROR: " + functLocation + "() expected " + expectedObj + ", got " + gotObj);
    }
}
