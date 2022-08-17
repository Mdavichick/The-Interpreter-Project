package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

// Store ByteCode will be used to move values from the top of the run time stack
// to an offset in the current frame

public class StoreCode extends ByteCode{
    // arguments
    private String offset;  // offset value represented as String
    private String label;
    private int oValue; // offset value represented as int
    private int value;  // value to be moved

    @Override
    public void execute(VirtualMachine virtualMachine) {
        oValue = Integer.parseInt(offset);
        virtualMachine.store(oValue);
        // value will be found at top of stack
        this.value = virtualMachine.peek();
    }

    @Override
    public void init(ArrayList<String> args) {
        if(!args.isEmpty()){
            this.offset = args.get(0);
            this.label = args.get(1);
        }
    }

    @Override
    public String toString() {
        String byteCode = "STORE";
        return byteCode + " " + offset + " " + label + " " +  label + "= " + value;
    }
}
