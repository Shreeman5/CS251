import java.util.LinkedList;

/**
 * Shreeman Gautam
 * Oct 5, 2018
 * This class implements
 * the Stack<> class provided 
 * and uses methods from 
 * the linkedList class to
 * add or remove doubles
 * to/from a stack
 */
public class StackOfDoubles implements Stack<Double>{

    /*linkedList to add or remove values*/
    private LinkedList<Double> linkedList = new LinkedList<Double>();
    
    /*returns true or false depending on the nature of the list*/
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    /*pushes the double onto a stack */
    public void push(Double val) {
        linkedList.push(val);
    }

    /*pops an element from the stack */
    public Double pop() {
        return linkedList.pop();
    }

    /*looks at the first element of the list */
    public Double peek() {
        return linkedList.peek();
    }

}
