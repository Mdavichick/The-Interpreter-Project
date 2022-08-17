package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

// used to push literal values to the runtime stack
public class LitCode extends ByteCode{
    private String offset;
    private String value;
    private int oValue;


    @Override
    public void execute(VirtualMachine virtualMachine) {
        oValue = Integer.parseInt(offset);
        virtualMachine.push(oValue);
        }

    @Override
    public void init(ArrayList<String> args) {
        // can have more than 1 argument
        if(!args.isEmpty())
        {
            offset = args.get(0);       // set first argument
            if (args.size() > 1)
            {
                value = args.get(1);    // if more, set two
            }
            else{
                value = null;
            }
        }
    }

    @Override
    public String toString() {
        String base = "LIT " + oValue;
        if(value!= null) {
            base += (" int " + value);
        }
            return base;
        }
    }



