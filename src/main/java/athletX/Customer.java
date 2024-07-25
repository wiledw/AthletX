package athletX;

/**
 * A customer is a user with a customer id, shipping address,
 * shopping cart, and orders
 */

public class Customer extends User {

    private int customerId;
    private String shippingAddress;

    private final ShoppingCart cart = new ShoppingCart();
    private final allOrders orders = new allOrders();


    public Customer(String username, String firstName, String lastName, String shippingAddress, int customerId) {
        super(firstName, lastName, username, Actors.CUSTOMER);
        this.shippingAddress = shippingAddress;
        this.customerId = customerId;

    }

    /**
     * Adds product to cart
     * @param product
     */

    public void addToCart(Product product) {
        cart.addProduct(product);
    }

    /**
     * Returns the customer ID
     * @return
     */
    public int getCustomerId() {
        return customerId;
    }


    /**
     * Set the customer ID
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public allOrders getOrders() { return orders; }

}
