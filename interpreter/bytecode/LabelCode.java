package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode{
    private String label;

    @Override
    public void execute(VirtualMachine virtualMachine) {
    }

    @Override
    public void init(ArrayList<String> args) {
            this.label = args.get(0);
    }

    @Override
    public String toString() {
        String byteCode = "LABEL ";
        return byteCode;
    }

    public String getLabel(){return label;}
}
