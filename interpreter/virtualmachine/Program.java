package interpreter.virtualmachine;

import interpreter.bytecode.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {

    private ArrayList<ByteCode> program;
    private static HashMap<String, Integer> mapHash;

    public Program() {
        program = new ArrayList<>();
    }

    protected ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }

    // write an add byteCodes function for this class
    public void addByte(ByteCode byteCode){program.add(byteCode);}

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter
     * HINT: make note what type of data-structure ByteCodes are stored in.
     */


    // Resolves address of Call, Goto and FalseBranch codes
    public void resolveAddress() {
        HashMap<String, LabelCode> hashTable = new HashMap<>();

        // 1st pass through the arraylist keeping track of label codes and their labels
            for (int i = 0; i < program.size(); i++)
            {
                ByteCode x = program.get(i);

                if (x instanceof LabelCode)
                {   // map . put (x.label), index i
                    hashTable.put(((LabelCode)x).getLabel(),(LabelCode)x);
                }
            }

        // 2nd pass through arraylist, look for call, goto and falsebranch codes and do the following:
        // look at stored label codes and find the 1 that has the matching label value.
            for (int i = 0; i < program.size(); i++)
            {
                ByteCode y = program.get(i);

                if(y instanceof CallCode)
                {
                    if(hashTable.containsKey(((CallCode)y).getLabel())){
                        ((CallCode)y).setDestination(program.indexOf(hashTable.get(((CallCode)y).getLabel())));
                    }
                }

                if(y instanceof GotoCode)
                {
                    if(hashTable.containsKey(((GotoCode)y).getLabel())){
                        ((GotoCode)y).setDestination(program.indexOf(hashTable.get(((GotoCode)y).getLabel())));
                    }
                }

                else if(y instanceof FalseBranchCode)
                {
                    if(hashTable.containsKey(((FalseBranchCode)y).getLabel())){
                        ((FalseBranchCode)y).setDestination(program.indexOf(hashTable.get(((FalseBranchCode)y).getLabel())));
                    }
                }
            }
}}
