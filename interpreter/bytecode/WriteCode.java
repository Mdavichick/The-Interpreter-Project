package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

//display information to console
//only allowed to display the top value of RTS
public class WriteCode extends ByteCode{

    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println(virtualMachine.peek());
    }

    @Override
    public void init(ArrayList<String> args) {
    }

    @Override
    public String toString() {
        String byteCode = "WRITE ";
        return byteCode;
    }
}
