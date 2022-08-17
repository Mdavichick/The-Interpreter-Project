package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

// what VM uses to jump to locations
public class CallCode extends ByteCode{
    private String label;
    int destinationAddress;

    @Override
    public void execute(VirtualMachine virtualMachine) {
        // save PC to ReturnAddress Stack
        virtualMachine.pushAddr();

        // Set new PC
        virtualMachine.setProgramCounter(destinationAddress);
    }

    @Override
    public void init(ArrayList<String> args) {
            this.label = args.get(0);
    }

    @Override
    public String toString() {
        String byteCode = "CALL";
        return byteCode + " " + label;
    }

    public void setLabel(int index)
    {
        this.label=Integer.toString(index);
    }
    public String getLabel(){
        return this.label;
    }
    public void setDestination(int address){this.destinationAddress = address;}


}
