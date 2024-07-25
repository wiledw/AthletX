package athletX;

/**
 * Holds information about the Manager, who have more power over the platform,
 * such as adding and deleting products
 */
public class Manager extends User {
    int managerID;

    /**
     * Manager constructor to make a new manager object
     * @param username manager username
     * @param firstName manager firstname
     * @param lastName manager lastname
     * @param managerID manager id
     */
    public Manager(String username, String firstName, String lastName, int managerID) {
        super(firstName, lastName, username, Actors.MANAGER);
        System.out.println("sgs");
    }

    /**
     * This method add a product to a customer cart
     * @param c customer
     * @param product product
     */
    public void addToCustomerCart(Customer c, Product product) {
        c.addToCart(product);
        System.out.println("sgsdgsg");
    }


}
