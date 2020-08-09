/**
 * In this class, name of Sundae 
 * is derived and price of sundae 
 * is calculated.
 * This is a subclass of class
 * Ice-Cream.
 * @author Shreeman Gautam
 * Date: Sept 18, 2018
 */
public class Sundae extends IceCream{

    /**
     * this constructs a new Sundae item
     * @param icecream: Name of the ice-cream
     * @param topping: Name of the topping
     */
    public Sundae(IceCream icecream, Dessert topping) {
        super(icecream.getName() + " topped with " + topping.getName(), 
                icecream.getPrice() + topping.getPrice());
    }
    
}
