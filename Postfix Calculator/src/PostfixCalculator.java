import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shreeman Gautam
 * Oct 5, 2018
 * 
 * This class takes in postfix
 * expressions and using basic
 * arithmetic, converts the operands 
 * and operators into a double type
 * number.
 */
public class PostfixCalculator{

    /*
     * variable that holds the stack of operands
     * using the StackOfDoubles class 
     */
    private Stack<Double> operands = new StackOfDoubles();
    
    /*
     * Variable that maps strings to 
     * operator objects
     */
    private Map<String, Operator> operatorMap = 
            new HashMap<String, Operator>();
    
    /**
     * this constructs a post fix
     * calculator where using the 
     * put method, strings are mapped
     * to operator objects
     */
    public PostfixCalculator() {
        operatorMap.put("+", new addition());
        operatorMap.put("add", new addition());
        operatorMap.put("-", new subtraction());
        operatorMap.put("sub", new subtraction());
        operatorMap.put("*", new multiplication());
        operatorMap.put("mult", new multiplication());
        operatorMap.put("/", new division());
        operatorMap.put("div", new division());
        operatorMap.put("=", new print());
        operatorMap.put("print", new print());
    }
    
    /**
     * Non-static inner class which
     * implements the Operator class.
     * Performs addition.
     */
    public class addition implements Operator{

        /**
         * method within this class 
         * which returns the number
         * of arguments (int)
         */
        public int numArgs() {
            return 2;
        }

        /**
         * method within this class
         * which adds the first two variables
         * from the incoming list and returns 
         * the double value
         */
        public double eval(List<Double> args) {
            return args.get(0) + args.get(1);
        }

    }
    
    /**
     * Non-static inner class which
     * implements the Operator class.
     * Performs subtraction.
     */
    public class subtraction implements Operator{

        /**
         * method within this class 
         * which returns the number
         * of arguments (int)
         */
        public int numArgs() {
            return 2;
        }

        /**
         * method within this class
         * which subtracts the first two variables
         * from the incoming list and returns 
         * the double value
         */
        public double eval(List<Double> args) {
            return args.get(0) - args.get(1);
        }

    }
    
    /**
     * Non-static inner class which
     * implements the Operator class.
     * Performs multiplication.
     */
    public class multiplication implements Operator{

        /**
         * method within this class 
         * which returns the number
         * of arguments (int)
         */
        public int numArgs() {
            return 2;
        }

        /**
         * method within this class
         * which multiplies the first two variables
         * from the incoming list and returns 
         * the double value
         */
        public double eval(List<Double> args) {
            return args.get(0) * args.get(1);
        }

    }
    
    /**
     * Non-static inner class which
     * implements the Operator class.
     * Performs division.
     */
    public class division implements Operator{

        /**
         * method within this class 
         * which returns the number
         * of arguments (int)
         */
        public int numArgs() {
            return 2;
        }

        /**
         * method within this class
         * which divides the first two variables
         * from the incoming list and returns 
         * the double value
         */
        public double eval(List<Double> args) {
            return (args.get(0) / args.get(1));
        }
        
    }
    
    /**
     * Non-static inner class which
     * implements the Operator class.
     * Prints final value.
     */
    public class print implements Operator{

        /**
         * method within this class 
         * which returns the number
         * of arguments (int)
         */
        public int numArgs() {
            return 1;
        }

        /**
         * method within this class
         * which prints the first variable
         * from the incoming list, using
         * the get method and returns 
         * the first variable
         */
        public double eval(List<Double> args) {
            System.out.println(args.get(0));
            return args.get(0);
        }
    
    }
    
    /**
     * If CalcTest determines that it is an 
     * operand, it is pushed into the operands 
     * stack.
     */
    public void storeOperand(double value) {
        operands.push(value);
    }
    
    /**
     * If CalcTest determines that it is an
     * operator, the operator string is sent 
     * here and this method is invoked.
     */
    public void evaluateOperator(String Operator) {
        /*Determining the type of operator using the get
         * method provided by the Hashmap class */
        Operator oper = operatorMap.get(Operator);
        
        /*After determining the operator, invoke
         * the numArgs method of that operator */
        int i = oper.numArgs();
     
        LinkedList<Double> argList = new LinkedList <Double>();
        
        /*using the while loop, add a certain
         * number of variables into arglist
         * using the addFirst method from
         * linkedlist and the pop method */
        while(i > 0) {
            argList.addFirst(operands.pop());
            i = i - 1;
        }
        
        /*send the list to the desired operator
         * where it is evaluated and now,
         * is stored in the result variable */
        double result = oper.eval(argList);
        
        /*push the result into the operand stack */
        operands.push(result);
    }
}
