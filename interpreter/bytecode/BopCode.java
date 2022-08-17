package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

// used to implement binary operations
// need to remove 2 values from the RTS and operate on them according to operation
// result to be pushed back to the top of stack
public class BopCode extends ByteCode{
    private String operator;
    private int operand1;
    private int operand2;

    @Override
    public void execute(VirtualMachine virtualMachine) {
        int result = 0;
        operand2 = virtualMachine.pop();
        operand1 = virtualMachine.pop();

        switch(operator)
        {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "==":
                if (operand1 == operand2)
                {
                    result = 1;                 // result is an int, boolean T/F rep in Binary
                }
                else
                {
                    result = 0;
                }
                break;
            case "!=":
                if(operand1 != operand2)
                {
                    result = 1;
                }
                else{
                    result = 0;
                }
                break;
            case "<=":
                if (operand1 <= operand2)
                {
                    result = 1;
                }
                else
                {
                    result = 0;
                }
                break;
            case ">":
                if (operand1 > operand2)
                {
                    result = 1;
                }
                else{
                    result = 0;
                }
                break;
            case ">=":
                if (operand1 >= operand2)
                {
                    result = 1;
                }
                else
                {
                    result = 0;
                }
                break;
            case "<":
                if (operand1 < operand2){
                    result = 1;
                }
                else{
                    result = 0;
                }
                break;
            case "|":
                if (operand1 == 1 || operand2 == 1)
                {
                    result = 1;
                }
                else
                {
                    result = 0;
                }
                break;
            case "&":
                if (operand1 == 1 && operand2 == 1)
                {
                    result = 1;
                }
                else
                {
                    result = 0;
                }
                break;
        }
            virtualMachine.push(result);
    }

    @Override
    public void init(ArrayList<String> args) {
        if(!args.isEmpty())
        {
            this.operator = args.get(0);
        }
    }

    @Override
    public String toString() {

        return "BOP " + this.operator;
    }
}
