package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

// return from functions, but also puts return values in correct position on RTS
public class ReturnCode extends ByteCode{
    private String label;

    @Override
    public void execute(VirtualMachine virtualMachine) {
        // pop activation frame and save return value to top of RTS
        virtualMachine.popFrame();

        // store our return value
        virtualMachine.returnAddress();
    }

    @Override
    public void init(ArrayList<String> args) {
    if(!args.isEmpty()){
        label = args.get(0);
    }
    else {
        label = "NULL";
    }
    }

    @Override
    public String toString() {
       String byteCode;
        if (!label.equals("NULL")) {
            byteCode = "RETURN factorial exit " + label;
        }
        else
        {
            byteCode = "RETURN";
        }
        return byteCode;
    }
}
