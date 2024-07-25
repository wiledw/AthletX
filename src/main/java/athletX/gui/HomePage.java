package athletX.gui;

import athletX.Product;
import athletX.ProductManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Functionality for homePage.fxml
 */

public class HomePage {

    @FXML
    private Pane pane;
    @FXML
    private Scene scene;
    @FXML
    private TextField searchBar;


    /**
     * Displays header.fxml at the top of the page
     */
    @FXML
    private void initialize() throws IOException {
        VBox top = Window.loadPane();
        pane.getChildren().add(top);
    }

    /**
     * Functionality for the large "Search" button for the "Search for a product" search bar
     * Gets user input when clicked
     * Checks if the product is in the system and returns a page of results if found
     */
    @FXML
    private void onSearch(MouseEvent mouseEvent) throws IOException {
        Window.search = searchBar.getText();
        SearchPage.invoke(ProductManager.Filters.NAME, Window.search == null ? "" : Window.search);
    }

    /**
     * Directs user to Apparel Page when "Shop Apparel" label is clicked
     */
    @FXML
    private void onApparellClicked(MouseEvent mouseEvent) throws IOException {
        SearchPage.invoke(ProductManager.Filters.CATEGORY, Product.ProductCategory.APPAREL);
    }

    /**
     * Directs user to Shoe Page when "Shop Shoes" label is clicked
     */
    @FXML
    private void onShoesClicked(MouseEvent mouseEvent) throws IOException {
        SearchPage.invoke(ProductManager.Filters.CATEGORY, Product.ProductCategory.SHOES);
    }

    /**
     * Directs user to Accessories Page when "Shop Accessories" label is clicked
     */
    @FXML
    private void onAccessoriesClicked(MouseEvent mouseEvent) throws IOException {
        SearchPage.invoke(ProductManager.Filters.CATEGORY, Product.ProductCategory.ACCESSORIES);
    }
}
