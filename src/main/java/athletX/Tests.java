package athletX;

import athletX.*;
import athletX.exception.CredentialException;

import java.io.IOException;

public class Tests {
    private static Product defaultProduct = ProductManager.createProduct(Product.ProductCategory.APPAREL, "test", 69, 420.69, "8", "BLACK", "Male", Apparel.ApparelCategory.SHIRTS.toString(), "appa.jpg");

    public static void main(String[] args) throws IOException {
        System.out.println(testStoreCustomer());
        System.out.println(testStoreManager());
        System.out.println(testUser());
        System.out.println(testUserCustomer());

        Store.addCustomer("customer", "cus", "tomer", "srgtert");
        Store.setPassword("customer", "1234");

        Store.addManager("admin", "cus", "admin");
        Store.setPassword("admin", "1234");


        System.out.println(testProds());
        System.out.println(testAllOrders());
        System.out.println(testProduct());
        System.out.println(testCreateProductOrder());
        System.out.println(testCart());



    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Tests the ProductManager class, more specifically the createProduct and deleteProduct methods
     * @return A string describing the results of 6 tests testing 3 products 1 of each category
     * @throws IOException
     */
    public static String testProds() throws IOException {
        String output = "ProductManager: \n";
        StringBuilder errors = new StringBuilder("");
        int testCounter = 0;

        // Test accessory
        Product accessory = new Accessories("Muscle supplement", -1, 69, 420.69, Accessories.AccessoryCategory.OTHER, "appa.jpg");
        Product testAccessory = ProductManager.createProduct(Product.ProductCategory.ACCESSORIES, "Muscle supplement", 69, 420.69, null, null, null, Accessories.AccessoryCategory.OTHER.toString(), "appa.jpg");

        // Checking if the accessory created by the createProduct method is the same as the one created manually
        if(testAccessory.equals(accessory))
        {
            testCounter++;
        } else {
            errors.append("Subtest 1 Failed: Accessory are not the same\n");
        }

        // Test apparel
        Product apparel = new Apparel("Santa's sock", -1, 70, 421.69, "7", "RED", "Male", Apparel.ApparelCategory.SHIRTS, "appa.jpg");
        Product testApparel = ProductManager.createProduct(Product.ProductCategory.APPAREL, "Santa's sock", 70, 421.69, "7", "RED", "Male", Apparel.ApparelCategory.SHIRTS.toString(), "appa.jpg");

        // Checking if the apparel created by the createProduct method is the same as the one created manually
        if(apparel.equals(testApparel))
        {
            testCounter++;
        } else {
            errors.append("Subtest 2 Failed: Apparel are not the same\n");
        }


        // Test shoes
        Product shoe = new Shoes("Elf shoes", -1, 71, 422.69, "Male", "8", "BLACK", "appa.jpg");

        Product testShoe = ProductManager.createProduct(Product.ProductCategory.SHOES, "Elf shoes", 71, 422.69, "8", "BLACK", "Male", null, "appa.jpg");



        // Checking if the shoe created by the createProduct method is the same as the one created manually
        if(shoe.equals(testShoe))
        {
            testCounter++;
        } else {
            errors.append("Subtest 3 Failed: Shoes are not the same\n");
        }

        // Serializing the products
        ProductManager.serialize(testShoe);
        ProductManager.serialize(testApparel);
        ProductManager.serialize(testAccessory);


        // Attempts to delete the products and tests if the accessory was deleted
        if(ProductManager.deleteProduct(testAccessory.getprodId()))
        {
            testCounter++;
        } else {
            errors.append("Subtest 4 Failed: Could not delete accessory\n");
        }

        // Attempts to delete the products and tests if the apparel was deleted
        if(ProductManager.deleteProduct(testApparel.getprodId()))
        {
            testCounter++;
        } else {
            errors.append("Subtest 5 Failed: Could not delete apparel\n");
        }

        // Attempts to delete the products and tests if the shoe was deleted
        if(ProductManager.deleteProduct(testShoe.getprodId()))
        {
            testCounter++;
        } else {
            errors.append("Subtest 6 Failed: Could not delete shoe\n");
        }

        // Returns the results of the tests
        if(testCounter == 6)
        {
            return output + "All test cases passed";
        } else
        {
            return output + testCounter + "/6 test cases passed \n" + errors;
        }



    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Tests the ProductOrder class
     * @return A string describing the results of 2 tests
     */
    public static String testAllOrders() {
        // Final output string containing test case information
        String output = "allOrders Tests: \n";
        // Counts number of tests ran and number successful
        int passed = 0;
        int numTests = 0;
        // Logs in to default user catches and returns if user does not exist
        try {
            Store.login("customer", "1234");
        } catch (CredentialException e) {
            output += "ERROR: Could not log in, customer does not exist.\n";
            output += "Test suite aborted on error.\n";
            return output;
        }
        if (!(Store.loggedIn instanceof Customer)) {
            output += "ERROR: User not an instance of customer.\n";
            output += "Test suite aborted on error.\n";
            return output;
        }
        // Creates a product order
        Product prod = ProductManager.getProduct(defaultProduct.getprodId());
        allOrders orders = ((Customer) Store.loggedIn).getOrders();
        // createProductOrder adds the product order to orderes
        ProductOrder prodOrder = ProductOrder.createProductOrder((Customer) Store.loggedIn, prod, orders);

        numTests++;
        // Checks if the user's cart size properly increased to one
        if (orders.cartSize() == 1) {
            output += "PASSED\n";
            passed++;
        } else {
            output += "FAILED: cartSize() did not report size of 1 after adding product.\n";
        }

        numTests++;
        // Checks if the user's cart contains said product at index 0
        try {
            if (orders.getItemAt(0) == prodOrder) {
                output += "PASSED\n";
                passed++;
            } else {
                output += "FAILED: Cart item not the same as added product.\n";
            }
        } catch (Exception e) {
            output += "ERROR: Cart item could not be accessed.\n";
        }

        // Resets all side effects
        Store.loggedIn = null;
        // Return formatted output with number of tests passed out of total tests ran.
        return output + "Passed " + passed + " of " + numTests + " tests.\n";
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Tests the ProductOrder class
     * @return A string describing the results of 11 tests
     */
    public static String testProduct(){
        int prodId = ProductManager.genProductID();
        Product product = new Apparel("Test Product", prodId, 10, 99.99, "6", "BLACK", "MALE", Apparel.ApparelCategory.SHIRTS, "appa.jpg");

        String output = "Product Tests: \n";
        StringBuilder errors = new StringBuilder("");

        int testCounter = 0;

        /* TESTING GETTERS */
        if ("Test Product".equals(product.getName())) { testCounter++; }
        else { errors.append("Subtest 1 Failed: Product names do not match\n"); }

        if ("AppFiles\\images\\appa.jpg".equals(product.getImageDir())){ testCounter++; }
        else { errors.append("Subtest 2 Failed: Image directory names do not match\n"); }

        if (prodId == product.getprodId()) { testCounter++; }
        else { errors.append("Subtest 3 Failed: Product IDs do not match\n"); }

        if (10 == product.getStock()) { testCounter++; }
        else { errors.append("Subtest 4 Failed: Product stock counts do not match\n"); }

        product.reduceStock();

        if (product.getStock() == 9) { testCounter++; }
        else { errors.append("Subtest 5 Failed: Product reduced stock counts do not match\n"); }

        if (product.getPrice() == 99.99) { testCounter++; }
        else { errors.append("Subtest 6 Failed: Product prices do not match\n"); }

        if (product.getProductCategory() == Product.ProductCategory.APPAREL) { testCounter++; }
        else { errors.append("Subtest 7 Failed: Product categories do not match\n"); }

        /* TESTING SETTERS */
        product.setName("New Name");
        product.setPrice((int) 50.00);
        product.setprodId(50000);
        product.setStock(0);

        if ("New Name".equals(product.getName())) { testCounter++; }
        else { errors.append("Subtest 8 Failed: Changed product names do not match\n"); }

        if (50.00 == product.getPrice()) { testCounter++; }
        else { errors.append("Subtest 9 Failed: Changed product names do not match\n"); }

        if (50000 == product.getprodId()) { testCounter++; }
        else { errors.append("Subtest 10 Failed: Changed product names do not match\n"); }

        if (0 == product.getStock()) { testCounter++; }
        else { errors.append("Subtest 11 Failed: Changed product names do not match\n"); }

        /* TEST OUTPUT */
        if (testCounter == 11){
            return output + "All test cases passed\n";
        }
        else{
            return output + testCounter + "/ 11 test cases passed \n" + errors;
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Tests the ProductOrder class
     * @return A string describing the results of 2 tests
     */
    public static String testCreateProductOrder() {
        /* counter for tests passed */

        int totalTests = 2;
        int totalPassed = 0;

        String output = "ProductOrder.createProductOrder: ";


        Customer customer = new Customer("customer", "customer", "one", "address", 1234);
        int shoeProdID = ProductManager.genProductID();
        Product shoe = new Shoes("Athletic shoes", shoeProdID, 50, 89.99, "male", "7", "white", "shoe12.5.jpg");

        Product testShoe = ProductManager.createProduct(Product.ProductCategory.SHOES, "Athletic Shoes", shoeProdID, 50, "89.99", "male", "7", "white", "shoe12.5.jpg");
        allOrders allOrders = new allOrders();

        /* Test create product order */
        try {
            ProductOrder productOrder = ProductOrder.createProductOrder(customer, shoe, allOrders);
            totalPassed++;

            /* Testing the returned product from product order */
            try{
                Product p = productOrder.getProduct();
                if(p.equals(shoe)){
                    totalPassed++;
                }
                else {
                    System.out.println("ERROR: Product ORDER product mismatch ");
                }
            }
            catch (Exception E){
                System.out.println("ERROR: Could not Add Product to Order");
            }

        }
        catch (Exception E){
            System.out.println("ERROR: Could not create Product Order ");
        }


        return totalPassed + " tests passed out of " + totalTests + " tests.\n";

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Tests the Shopping Cart class
     * @return A string describing the results of 3 tests
     */
    public static String testCart() {

        // Final output string containing test case information
        String output = "ShoppingCart Tests: \n";

        // Counts number of tests ran and number successful
        int passed = 0;
        int numTests = 0;

        // Logs in to default user catches and returns if user does not exist
        try {
            Store.login("customer", "1234");
        } catch (CredentialException e) {
            output += "ERROR: Could not log in, customer does not exist.\n";
            output += "Test suite aborted on error.\n";
            return output;
        }

        // Gets a product by its prodID
        Product prod = ProductManager.getProduct(defaultProduct.getprodId());

        // Checks if the logged-in user is a Customer, returns if not
        if(! (Store.loggedIn instanceof Customer)) {
            output += "ERROR: User not an instance of customer.\n";
            output += "Test suite aborted on error.\n";
            return output;
        }

        // Gets the logged-in user's cart
        ShoppingCart cart = ((Customer)Store.loggedIn).getCart();


        // Adds the previous product to user's cart
        cart.addProduct(prod);

        // Checks if the user's cart size properly increased to one
        numTests++;
        if(cart.cartSize() == 1) {
            output += "PASSED\n";
            passed++;
        } else {
            output += "FAILED: cartSize() did not report size of 1 after adding product.\n";
        }

        // Checks if the user's cart contains said product at index 0
        numTests++;
        try {
            if (cart.getItemAt(0) == prod) {
                output+= "PASSED\n";
                passed++;
            } else {
                output+= "FAILED: Cart item not the same as added product.\n";
            }
        } catch (Exception e) {
            output+= "ERROR: Cart item could not be accessed.\n";
        }


        // Checks if the cart item successfully deletes an item
        numTests++;
        try {
            if (cart.deleteProduct(0) == prod) {
                output+= "PASSED\n";
                passed++;
            } else {
                output+= "FAILED: Cart item deleted is not the same as originally added product.\n";
            }
        } catch (Exception e) {
            output+= "ERROR: Cart item could not be accessed.\n";
        }


        // Resets all side effects
        cart.emptyCart();
        Store.loggedIn = null;

        // Return formatted output with number of tests passed out of total tests ran.
        return output + "Passed " + passed + " of " + numTests + " tests.\n";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Tests the customer side of the Store class
     * @return A string describing the results of 4 tests
     */
    public static String testStoreCustomer() {
        // Add new customer
        Store.addCustomer("customer1", "Jeff", "Bezos", "TMU");
        // Set customer password
        Store.setPassword("customer1", "1234");

        // Final output string containing test case information
        String output = "Store Tests (Customer Side): \n";
        // Counts number of tests ran and number successful
        int passed = 0;
        int numTests = 0;
        // Check if userExists after adding
        if (Store.userExists("customer1")) {
            passed++;
        } else {
            output += "ERROR: Could not add a customer, customer does not exist.\n";
            output += "Test suite aborted on error.\n";
        }
        numTests++;
        // Logs in to user catches and returns if user does not exist
        try {
            Store.login("customer1", "1234");
            passed++;
        } catch (CredentialException e) {
            output += "ERROR: Could not log in, customer does not exist.\n";
            output += "Test suite aborted on error.\n";
            return output;
        }
        numTests++;
        // Checks if the logged-in user is a Customer, returns if not
        if (!(Store.loggedIn instanceof Customer)) {
            output += "ERROR: User not an instance of customer.\n";
            output += "Test suite aborted on error.\n";
            return output;
        } else {
            passed++;
        }
        numTests++;

        // Check editUsername method
        Store.editUsername("customer1", "newCustomer1");
        if (!Store.userExists("customer1") && Store.userExists("newCustomer1")) {
            passed++;
        } else {
            output += "ERROR: Could not edit username of customer1\n";
        }
        numTests++;
        // Resets all side effects
        Store.loggedIn = null;
        // Return formatted output with number of tests passed out of total tests ran.
        return output + "Passed " + passed + " of " + numTests + " tests.\n";
    }

    /**
     * Tests the manager side of the Store class
     * @return A string describing the results of 4 tests
     */
    public static String testStoreManager() {
        // Add new manager
        Store.addManager("manager1", "Jeffry", "Bezos");
        // Set manager password
        Store.setPassword("manager1", "1234");
        // Final output string containing test case information
        String output = "Store Tests (Manager Side): \n";
        // Counts number of tests ran and number successful
        int passed = 0;
        int numTests = 0;
        // Check if userExists after adding
        if (Store.userExists("manager1")) {
            passed++;
        } else {
            output += "ERROR: Could not add a manager, manager does not exist.\n";
            output += "Test suite aborted on error.\n";
        }
        numTests++;
        // Logs in to user catches and returns if user does not exist
        try {
            Store.login("manager1", "1234");
            passed++;
        } catch (CredentialException e) {
            output += "ERROR: Could not log in, manager1 password is incorrect/not set.\n";
            output += "Test suite aborted on error.\n";
            return output;
        }
        numTests++;
        // Checks if the logged-in user is a Manager, returns if not
        if (!(Store.loggedIn instanceof Manager)) {
            output += "ERROR: User not an instance of Manager.\n";
            output += "Test suite aborted on error.\n";
            return output;
        } else {
            passed++;
        }
        numTests++;
        // Check editUsername method
        Store.editUsername("manager1", "newManager1");
        if (!Store.userExists("manager1") && Store.userExists("newManager1")) {
            passed++;
        } else {
            output += "ERROR: Could not edit username of manager1\n";
        }
        numTests++;
        // Resets all side effects
        Store.loggedIn = null;
        // Return formatted output with number of tests passed out of total tests ran.
        return output + "Passed " + passed + " of " + numTests + " tests.\n";
    }

    /**
     * Tests general user functions in the Store class
     * @return A string describing the results of 7 tests
     */
    public static String testUser() {
        // Add new customer
        Store.addCustomer("customer2", "Tom", "Jerry", "TMU");
        // Set customer password
        Store.setPassword("customer2", "1234");
        Customer currentUser;
        // Final output string containing test case information
        String output = "User Tests: \n";
        // Counts number of tests ran and number successful
        int passed = 0;
        int numTests = 0;
        // Check if userExists after adding
        if (Store.userExists("customer2")) {
            passed++;
        } else {
            output += "ERROR: Could not add a customer, customer does not exist.\n";
            output += "Test suite aborted on error.\n";
        }
        numTests++;
        // Logs in to user catches and returns if user does not exist
        try {
            Store.login("customer2", "1234");
        } catch (CredentialException e) {
            output += "ERROR: Could not log in, customer does not exist.\n";
            output += "Test suite aborted on error.\n";
            return output;
        }
        // Checks if the logged-in user is a Customer, returns if not
        if(!(Store.loggedIn instanceof Customer)) {
            output += "ERROR: User not an instance of customer.\n";
            output += "Test suite aborted on error.\n";
            return output;
        }
        // Set currentUser as the current customer that logged in
        currentUser = ((Customer) Store.loggedIn);
        // Check getUsername working
        if (currentUser.getUsername().equals("customer2")){
            passed++;
        } else {
            output += "getUsername() not working\n";
        }
        numTests++;
        // Check getFirstName working
        if (currentUser.getFirstName().equals("Tom")){
            passed++;
        } else {
            output += "getFirstName() not working\n";
        }
        numTests++;
        // Check getLastName working
        if (currentUser.getLastName().equals("Jerry")){
            passed++;
        } else {
            output += "getLastName() not working\n";
        }
        numTests++;

        // Use all set method from User.java
        currentUser.setUsername("newCustomer2");
        currentUser.setFirstName("NewTom");
        currentUser.setLastName("NewJerry");

        // Check setUsername working
        if (currentUser.getUsername().equals("newCustomer2")){
            passed++;
        } else {
            output += "setUsername() not working\n";
        }
        numTests++;
        // Check setFirstName working
        if (currentUser.getFirstName().equals("NewTom")){
            passed++;
        } else {
            output += "setFirstName() not working\n";
        }
        numTests++;
        // Check setLastName working
        if (currentUser.getLastName().equals("NewJerry")){
            passed++;
        } else {
            output += "setLastName() not working\n";
        }
        numTests++;
        // Resets all side effects
        Store.loggedIn = null;
        // Return formatted output with number of tests passed out of total tests ran.
        return output + "Passed " + passed + " of " + numTests + " tests.\n";
    }

    /**
     * Tests general user customer functions in the Store class
     * @return A string describing the results of 4 tests
     */
    public static String testUserCustomer(){
        // Add new customer
        Store.addCustomer("customer3", "Tim", "Hortons", "TMU");
        // Set customer password
        Store.setPassword("customer3", "1234");
        Customer currentUser;
        // Final output string containing test case information
        String output = "User Tests (Customer): \n";
        // Counts number of tests ran and number successful
        int passed = 0;
        int numTests = 0;
        // Check if userExists after adding
        if (Store.userExists("customer3")) {
            passed++;
        } else {
            output += "ERROR: Could not add a customer, customer does not exist.\n";
            output += "Test suite aborted on error.\n";
        }
        numTests++;
        // Logs in to user catches and returns if user does not exist
        try {
            Store.login("customer3", "1234");
        } catch (CredentialException e) {
            output += "ERROR: Could not log in, customer does not exist.\n";
            output += "Test suite aborted on error.\n";
            return output;
        }
        // Checks if the logged-in user is a Customer, returns if not
        if(!(Store.loggedIn instanceof Customer)) {
            output += "ERROR: User not an instance of customer.\n";
            output += "Test suite aborted on error.\n";
            return output;
        }

        // Set currentUser as the current customer that logged in
        currentUser = ((Customer) Store.loggedIn);
        Shoes p = new Shoes("newShoes",  ProductManager.genProductID(), 69, 42.0, "male", "6", "black", "shoe.png");

        // Check if addToCart able to add product to user cart
        currentUser.addToCart(p);
        if (currentUser.getCart().getItemAt(0) == p){
            passed++;
        } else {
            output += "Failed to add item to cart, addToCart failed!\n";
        }
        numTests++;

        // Check if set and get CustomerId is working
        currentUser.setCustomerId(67000100);
        if (currentUser.getCustomerId() == 67000100){
            passed++;
        } else {
            output += "Failed to setCustomerId\n";
        }
        numTests++;

        // Check if set and get ShippingAddress is working
        currentUser.setShippingAddress("NewTMU");
        if (currentUser.getShippingAddress().equals("NewTMU")){
            passed++;
        } else {
            output += "Failed to setShippingAddress\n";
        }
        numTests++;

        // Resets all side effects
        currentUser.getCart().emptyCart();
        Store.loggedIn = null;
        // Return formatted output with number of tests passed out of total tests ran.
        return output + "Passed " + passed + " of " + numTests + " tests.\n";
    }

}
