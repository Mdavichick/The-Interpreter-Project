package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.sql.SQLOutput;
import java.util.ArrayList;

// used to turn dumping ON and OFF
public class DumpCode extends ByteCode{
   private boolean dump;
   private String status;
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setDump(dump);
    }

    @Override
    public void init(ArrayList<String> args) {
            status = args.get(0);
            if (status == "ON"){
                dump = true;
            }
            else{
                dump = false;
            }
    }


    @Override
    public String toString() {
        return "DUMP ";
    }
}
