/**
 * This class extends the exception class
 * and if the class BankAccount
 * throws an exception, this class
 * is accessed by the BankingTest class
 * through the printStackTrace method
 * @author Shreeman Gautam
 *
 */
public class InsufficientFundsException extends Exception{

    /*in case the program doesn't run/compile, please
     * comment out the code on line 11 
     */
    //private static final long serialVersionUID = 1L;
    
    /* double to represent the amount of shortfall */
    private double shortfall;
    
    /**
     * 
     * @param shortfall: the amount of money the 
     * account is short off
     * An error message is printed out if class
     * BankAccount throws an exception
     */
    public InsufficientFundsException (double shortfall) {
        super("Need more money");
        this.shortfall = shortfall;
    }
    
    /**
     * 
     * @return amount of shortfall
     */
    public double getShortfall() {
        return shortfall;
    }
    
    
}
