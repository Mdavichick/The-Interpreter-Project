package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode {
    private String label; // marks a place in the program to jump to
    private int destinationAddress;

    @Override
    public void execute(VirtualMachine virtualMachine) {
        // FalseBranch jump requires 0 - check condition
        if(virtualMachine.pop() == 0){      // if so
            virtualMachine.setProgramCounter(destinationAddress);  // set Program Counter
        }
    }

    @Override
    public void init(ArrayList<String> args) {
        if(!args.isEmpty())
        this.label = args.get(0);   // set our label to first index
    }

    @Override
    public String toString() {
        String byteCode = "FALSEBRANCH ";
        return byteCode + label;
    }

    public String getLabel(){return this.label;}
    // required for typecasting -> Int to String
    public void setLabel(int index){this.label = Integer.toString(index);}
    public void setDestination(int address){destinationAddress = address;}
}
