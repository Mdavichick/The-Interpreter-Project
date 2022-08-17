package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode{
    private String byteCode;
    private int value;

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.pop();
    }

    @Override
    public void init(ArrayList<String> args) {
        byteCode = args.get(0); // get first index of array
        this.value = Integer.parseInt(byteCode); // store this string value as an int
    }

    @Override
    public String toString() {
        String pop = "POP ";
        return pop + byteCode;
    }
}
