package athletX;

import athletX.gui.Window;
import javafx.application.Application;

import java.io.IOException;

/**
 * Main entry point for application
 * For Debugging purposes
 * Customer account username: customer
 * Customer account password: 1234
 *
 * Manager account username: admin
 * Manager account password: 1234
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Store.addManager("admin", "cus", "admin");
        Store.setPassword("admin", "1234");

        ProductManager p = new ProductManager();

        Store.addCustomer("customer", "cus", "tomer", "sdfdsfs");
        Store.setPassword("customer", "1234");
        Application.launch(Window.class, args);

    }
}
