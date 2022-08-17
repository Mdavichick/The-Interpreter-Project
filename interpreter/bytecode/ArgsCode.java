package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

// used to set up how many arguments a function has
public class ArgsCode extends ByteCode{
    private String arguments;
    private int n; // number of values that are arguments for the next function call

    @Override
    public void execute(VirtualMachine virtualMachine) {
        n = Integer.parseInt(arguments);
        virtualMachine.newFrameAt(n);
    }

    @Override
    public void init(ArrayList<String> args) {
        if(!args.isEmpty())
        arguments = args.get(0);
    }

    @Override
    public String toString() {
        String byteCode = "ARGS";
        return byteCode + " " + n;
    }
}
