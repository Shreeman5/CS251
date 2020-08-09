/**
 * In this class, the price of the
 * cookies is calculated.
 * This class is a subclass of class
 * Dessert.
 * @author Shreeman Gautam
 * Date: Sept 18, 2018
 */
public class Cookie extends Dessert{

    /*int to calculate number of cookies*/
    private int numberOfCookies;
    
    /*double to calculate price of dozen cookies*/
    private double pricePerDozen;
    
    /**
     * this constructs a new cookie item
     * @param name: Name of the cookie.
     * @param numberOfCookies: variable created for this class
     * @param pricePerDozen: variable created for this class
     */
    public Cookie(String name, int numberOfCookies, double pricePerDozen){
        super(name);
        this.numberOfCookies = numberOfCookies;
        this.pricePerDozen = pricePerDozen;
    }

    @Override
    /**
     * @return price of the cookies
     */
    public double getPrice() {
        return (numberOfCookies * pricePerDozen) / 12;
    }
    
    /**
     * 
     * @return number of cookies
     */
    public int getItemCount(){
        return numberOfCookies;
    }
    
    /**
     * 
     * @return price of a dozen cookies
     */
    public double getPricePerDozen(){
        return pricePerDozen;
    }

}
