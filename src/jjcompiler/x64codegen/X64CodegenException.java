package jjcompiler.x64codegen;

import jjcompiler.x86codegen.*;

public class X64CodegenException extends RuntimeException{

  public X64CodegenException(String msg) {
    super (msg);
  }
}