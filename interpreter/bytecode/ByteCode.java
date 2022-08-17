package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public abstract class ByteCode {

    // ByteCode execution
    public abstract void execute(VirtualMachine virtualMachine);

    // Get arguments for ByteCode
    public abstract void init(ArrayList<String> args);

    // For dumping purposes
    public abstract String toString();
}



