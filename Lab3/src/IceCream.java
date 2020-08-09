/**
 * In this class, price of the
 * Ice cream is calculated.
 * This class is a subclass of class
 * Dessert.
 * @author Shreeman Gautam
 * Date: Sept 18, 2018
 */
public class IceCream extends Dessert{

    /* double to calculate price of Ice Cream*/
    private double price;
    
    /**
     * this constructs a new Ice-cream item
     * @param name: Name of the ice-cream
     * @param price: variable created for this class
     */
    public IceCream(String name, double price) {
        super(name);
        this.price = price;
    }

    @Override
    /**
     * @return price of ice-cream
     */
    public double getPrice() {
        return price;
    }

}
