/**
 * This class creates a 
 * bank account from which 
 * money can be withdrawn.
 * @author Shreeman Gautam
 * Date: Sept 25, 2018
 */
public class BankAccount {

    /* int to represent the ID of the account*/
    private int accountIDnumber;
    
    /* double to represent the money in the bank*/
    private double moneyInTheBank;
    
    /**
     * this constructs a new bank account
     * @param accountIDnumber: ID of the account
     * Also, the amount of money in the 
     * bank is specified.
     */
    public BankAccount(int accountIDnumber) {
        this.accountIDnumber = accountIDnumber;
        this.moneyInTheBank = 0; 
    }
    
    /**
     * 
     * @return ID of the account
     */
    public int getAccountID() {
        return accountIDnumber;
    }
    
    /**
     * 
     * @return money in the bank
     */
    public double getAccountBalance() {
        return moneyInTheBank;
    }
    
    /**
     * 
     * @param amount : money to be deposited
     */
    public void deposit(double amount) {
        moneyInTheBank = amount + moneyInTheBank;
    }
    
    /**
     * 
     * @param amount: money to be withdrawn
     * @throws InsufficientFundsException: If amount
     * to be withdrawn exceeds the money in the bank,
     * exception is thrown and the 
     * InsufficientFundsException class is called.
     * Else, money is withdrawn without
     * any exceptions.
     */
    public void withdraw(double amount) throws InsufficientFundsException{
        if (amount > moneyInTheBank) {
            throw new InsufficientFundsException(amount - moneyInTheBank);
        }
        else {
            moneyInTheBank = moneyInTheBank - amount;
        }
    }
} 
