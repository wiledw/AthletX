package athletX;

import java.util.ArrayList;
import java.util.Iterator;


public class allOrders {
    private final ArrayList<ProductOrder> orders;

    /**
     * Initializes the Arraylist that stores each customer's ProductOrder
     */
    public allOrders() {
        orders = new ArrayList<>();
    }



    /**
     * Adds a product to the list of all the orders a customer has made
     * @param product The product to be added
     */
    public void addProduct(ProductOrder product) {
        orders.add(product);
    }


    /**
     * Returns the number of elements in the ArrayList orders
     * @return The number of orders in the orders Array List
     */
    public int cartSize() {
        return orders.size();
    }


    /**
     * This method will get item at a specific index from orders
     * @param index The index at which we want to get a ProductOrder from orders
     * @return item
     */
    public ProductOrder getItemAt(int index) {
        return orders.get(index);
    }

}
