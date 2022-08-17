package interpreter.virtualmachine;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.DumpCode;

import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;
    private boolean        toBeDumped;  // PopByteCode requires dump tracking

    public VirtualMachine(Program program) {
        this.program = program;
    }

    public void executeProgram() {
        programCounter = 0;
        runTimeStack = new RunTimeStack();
        returnAddress = new Stack<Integer>();
        isRunning = true;

        while(isRunning) {
            ByteCode code = program.getCode(programCounter);
            code.execute(this);

            if (toBeDumped)                 // if dumping is enabled
            {
                printDump(code);            // print
            }
            programCounter ++;
        }
    }

    public void isRunning(boolean power){isRunning = power;}
    public int peek(){ return this.runTimeStack.peek(); }
    public void push(int valueToPush){this.runTimeStack.push(valueToPush);}
    public int pop(){return this.runTimeStack.pop();}
    public void store(int offset){this.runTimeStack.store(offset);}
    public void load(int offset){this.runTimeStack.load(offset);}
    public void newFrameAt(int offset){this.runTimeStack.newFrameAt(offset);}
    public void setDump(boolean active){this.toBeDumped=active;}
    public void setProgramCounter(int pc){this.programCounter = pc;}
    public void pushAddr(){this.returnAddress.push(programCounter);}
    public void popFrame(){
        // pop off and save the return value from RTS
        int storeValue = this.runTimeStack.pop();

        // pop off all values until frame is empty
        // remove frame boundary and pop off the frame
        this.runTimeStack.popFrame();

        // push return value back to the RTS
        this.runTimeStack.push(storeValue);
    }
    public void returnAddress(){
        // pop return address stack
        int value = this.returnAddress.pop();

        // set program counter to (pop value)
        setProgramCounter(value);
    }
    private void printDump(ByteCode code) {
        if (!(code instanceof DumpCode)){
            System.out.println(code);
            this.runTimeStack.dump();
        }
    }
}


