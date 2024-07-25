package athletX;

import java.util.ArrayList;
import java.util.Iterator;

public class ShoppingCart implements Iterable<Product> {
    private final ArrayList<Product> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }


    /**
     * Add a product to the shopping cart
     * @param product Product to be added to shopping cart
     */
    public void addProduct(Product product) {
        cartItems.add(product);
    }

    /**
     * Remove product from shopping cart
     * @param id The product ID for given product
     * @return The removed product
     */
    public Product deleteProduct(int id) {
        Product removedItem = cartItems.remove(id);
        return removedItem;
    }

    /* Display shopping cart (Not used in implementation).
    public void viewCart() {
        if (cartItems.size() == 0) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Your cart contains: ");
        for (CartItem Item : cartItems) {
            System.out.println(Item.getProduct().getName() + " | Order Number: " + Item.getOrderNumber());
        }
    } */

    /**
     * Iterator for iterating through all the products in the shopping cart
     * @return The iterator for the item
     */
    public Iterator iterator() {
        return cartItems.iterator();
    }

    /**
     * Return the amount of items in the shopping cart
     * @return Amount of items in shopping cart
     */
    public int cartSize() {
        return cartItems.size();
    }

    /**
     * Get item at specific index in array
     * @param index Index of item to be returned
     * @return The product at given index
     */
    public Product getItemAt(int index) {
        return cartItems.get(index);
    }

    /**
     * Remove all items from cart
     */
    public void emptyCart() {
        cartItems.clear();
    }
}