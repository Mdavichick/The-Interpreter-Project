package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;

// read user input from the keyboard

public class ReadCode extends ByteCode{
    private int input;
    @Override
    public void execute(VirtualMachine virtualMachine) {
        // prompt user for integer input
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter integer: ");
        input = obj.nextInt();

        //push input to VM
        virtualMachine.push(input);
    }

    @Override
    public void init(ArrayList<String> args) {
    }

    @Override
    public String toString() {
        String byteCode = "READ";
        return byteCode;
    }
}
