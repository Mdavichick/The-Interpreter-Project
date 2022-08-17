package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class HaltCode extends ByteCode{

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.isRunning(false);    // Set isRunning to false to halt VM
    }

    @Override
    public void init(ArrayList<String> args) {  // No need to dump
    }

    @Override
    public String toString() {                  // represent object as string
        String halt = "HALT";
        return halt;
    }
}
