package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

// will be used to move values from an offset in the current frame to the
// top of the stack
public class LoadCode extends ByteCode{
    private int offset;
    private String value;
    private int arguments; // for printing purposes


    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.load(offset);
    }

    @Override
    public void init(ArrayList<String> args) {
        // can have 1 to 2 arguments
        arguments = args.size();    // identify how many arguments are to be printed
        offset = Integer.parseInt(args.get(0));       // set first argument
        if (args.size() > 1)
        {
            value = args.get(1);    // if more, set two
        }
    }

    @Override
    public String toString() {
        String byteCode = "LOAD ";
        if (arguments > 1)
        {
            return byteCode + offset + " " + value + "   " + "<load " + value + ">";
        }
        else
        {
            return byteCode + offset;
        }


    }
}
