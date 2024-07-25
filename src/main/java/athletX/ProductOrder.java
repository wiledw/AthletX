package athletX;

import java.util.Map;
import java.util.HashMap;

/**
 * Represents an Order, and contains information of the Customer and Product ordered
 */
public class ProductOrder {
    private allOrders allOrders;
    private Customer customer;
    private final Product product;
    private int orderNumber;
    private static int orderIdIndex = 4300000;
    private static final Map<Integer, ProductOrder> activeOrders = new HashMap<>();

    /**
     * ProductOrder constructor which creates a new order object
     * @param customer customer object
     * @param product product object
     * @param allOrders allOrders object
     */
    private ProductOrder(Customer customer, Product product, allOrders allOrders) {
        this.customer = customer;
        this.product = product;
        this.allOrders = allOrders;
        this.orderNumber = orderIdIndex++;
    }

    /**
     * Static method that creates a new order object by calling new ProductOrder and put it to allOrders
     * @param customer customer object
     * @param product product object
     * @param allOrders allOrders object
     * @return new created product order
     */
    public static ProductOrder createProductOrder(Customer customer, Product product, allOrders allOrders) {

        ProductOrder p = new ProductOrder(customer, product, allOrders);
        activeOrders.put(p.getOrderNumber(), p);
        allOrders.addProduct(p);
        return p;
    }

    /**
     * This method return the customer
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * This method set the customer
     * @param customer customer object
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * This method get the order number
     * @return the order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * This method set the product order number
     * @param orderNumber the order number
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * The method return the product
     * @return product
     */
    public Product getProduct() {return this.product;}

}