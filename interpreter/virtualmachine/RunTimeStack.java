package interpreter.virtualmachine;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.Stack;

class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer>     framePointer;    // never empty

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    private int lastIndex(){
        return this.runTimeStack.size()-1;
    }
    public int peek(){
        return this.runTimeStack.get(lastIndex());
    }
    public int push(int valueToPush){
        this.runTimeStack.add(valueToPush);
        return this.peek();
    }
    public int pop(){
        return this.runTimeStack.remove(lastIndex());
    }

    /*
        Used for dumping the current state of the runTimeStack.
        It will print portions of the stack based on respective
        frame markers.
        Example [1,2,3] [4,5,6] [7,8]
        Frame pointers would be 0,3,6
     */
    public void dump() {

        for (int i = 0; i < framePointer.size(); i ++)
        {
            System.out.print("[");

                        // Printing all frames in FP until last frame
            if (i < framePointer.size()-1){
                for(int j = framePointer.get(i); j < framePointer.get(i+1); j++){
                    if(j < framePointer.get(i+1)-1){                                         // if there's move values to come in frame
                        System.out.print(runTimeStack.get(j) + ",");                       // print a comma
                    }
                    else{
                        System.out.print(runTimeStack.get(j));                              // else, last value in frame
                    }
                }
            }
                        // Printing last frame in FP to end of RTS
            else{
                for (int j = framePointer.get(framePointer.size()-1); j < runTimeStack.size(); j++) {
                    if (j < runTimeStack.size() - 1) {                                      // if there's more values to come in frame
                        System.out.print(runTimeStack.get(j) + ",");                       // print a coma
                    }
                    else{
                        System.out.print(runTimeStack.get(j));                              // else, last value in frame
                    }
                }
            }
            System.out.print("] ");
        }
        System.out.println();
    }

    /*
        Takes the top item of the run time stack, and stores
        it into a offset starting from the current frame.
        @param offset number of slots above current frame marker
        @return the item just stored
     */
    public int store (int offset){
        int popValue = this.pop();  // Remove top item of runTimeStack and stores it

        // ArrayList.set(int index, Object element)
        // index = current frame + offset | element = popValue
        this.runTimeStack.set(framePointer.peek() + offset, popValue);
        return popValue;
    }

    /*
        Takes a value from the run time stack that is at the offset
        from the current frame marker and pushes it onto the top of
        the stack.
        @param offset number of slots above current frame marker
        @return item just loaded into the offset
     */
    public int load (int offset) {
        int oValue = this.runTimeStack.get(framePointer.peek() + offset);  // takes value from offset position
        runTimeStack.add(oValue);
        return oValue;                                                              // returns value
    }

    /*
        Create a new frame pointer at the index offset slots down
        from the top of the runtime stack.
        @param offset slots down from the top of the runtime stack
     */
    public void newFrameAt(int offset){
        framePointer.push(runTimeStack.size() - offset);
    }
    /*
        Pop the current frame off the run time stack. Also removes
        the frame pointer value from the FramePointer Stack
     */
    public void popFrame(){
        // peek frame pointer bounds
        int storeValue = framePointer.peek();

        //while(lastIndex() >= framePointer.peek()) // while within frame
        for (int i = lastIndex(); i >= storeValue; i--)
        {
            runTimeStack.remove(i);               // remove related index
        }

        // pop off the frame
        framePointer.pop();
    }
}
