
package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.virtualmachine.Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;  // read buffered data from a string

    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN loadCodes.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }
    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts. Can also use the split function in the String class.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {
       int counter = 1;
        String line;
       String[] items;      // used to tokenize the string
       String byteCodeName; // ByteCode name from .x.cod file
       String className;    // class name after its mapped from name in source code to class name
                            // class name we're going to retrieve from codetable
        ArrayList<String> args = new ArrayList<>();
        Class classBlueprint;

       // Program<ByteCode> program = newProgram();
        Program program = new Program();
        ByteCode bc;
    try {
        while (this.byteSource.ready()) {    /*while we have more bytecodes to read*/

            // tokenize read line
            line = this.byteSource.readLine();  // read one line from our code
            items = line.split("\\s+"); // split line at one or more spaces

            // grab first token of line (guaranteed first token read is bytecode)
            byteCodeName = items[0];

            // grab class name from token
            className = CodeTable.getClassName(byteCodeName);

            // load class blueprint from classname
            classBlueprint = Class.forName("interpreter.bytecode." + className);

            // get declared constructor (should be no-arg constructor)
            // create a new instance of bytecode using constructor
            bc = (ByteCode) classBlueprint.getDeclaredConstructor().newInstance();

            // grab remaining arguments. (from list) [line]
            // line = this.byteSource.readLine
//
//            for (int i = 1; i < items.length; i++)
//            {
//                args.add(items[i]);
//            }

            args = new ArrayList<>(Arrays.asList(items).subList(1, items.length));


            // pass args to bytecode init function
                bc.init(args);

                //args.clear();
            // add bytecode to program
               program.addByte(bc);


        }
    } catch (IOException | ClassNotFoundException ex) {
        System.out.println(ex);   // print exception
        System.exit(255);   // if fails, we exit

    } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
        e.printStackTrace();
    }

        // put a breakpoint here, run our code before doing resolve address
        // make sure all our bytecodes loaded correctly and initialized correctly


//         resolve address
        program.resolveAddress();

        return program;
    }
}
