package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends ByteCode{
    private String label; // used for unconditional jumps
    private int destinationAddress;

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setProgramCounter(destinationAddress);
    }

    @Override
    public void init(ArrayList<String> args) {
        if(!args.isEmpty()){
            this.label = args.get(0);
        }
    }

    @Override
    public String toString() {
        String byteCode = "GOTO ";
        return byteCode + label;
    }

    public String getLabel(){return this.label;}
    // required for typecasting -> Int to String
    public void setLabel(int index){this.label = Integer.toString(index);}
    public void setDestination(int address){this.destinationAddress = address;}

}

