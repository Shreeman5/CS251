/**
 * In this class, the price
 * of the candy is calculated.
 * This class is a subclass of
 * class Dessert.
 * @author Shreeman Gautam
 * Date: Sept 18, 2018
 */
public class Candy extends Dessert{

    /* double to represent the weight of candy*/
    private double weight;
    
    /* double to represent price of pound*/
    private double pricePerPound;
    
    /**
     * this constructs a new candy item.
     * @param name: Name of the candy
     * @param weight: variable created for this class
     * @param pricePerPound: variable created for this class
     */
    public Candy(String name, double weight, double pricePerPound){
        super(name);
        this.weight = weight;
        this.pricePerPound = pricePerPound;
    }

    @Override
    /**
     * @return price of the candy
     */
    public double getPrice() {
        return weight * pricePerPound;
    }
    
    /**
     * 
     * @return weight of the candy in pounds
     */
    public double getWeightInPounds() {
        return weight; 
    }
    
    /**
     * 
     * @return Price Per Pound of the candy
     */
    public double getPricePerPound() {
        return pricePerPound;
    }

}
