package athletX.gui;

import athletX.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class Header {
    @FXML
    private Pane pane;

    @FXML
    private VBox topBox;

    @FXML
    private Button addProduct;

    @FXML
    public Button cart;

    @FXML
    private Scene scene;


    @FXML
    private TextField searchField;

    @FXML
    private Button backButton, forwardButton;


    /**
     * Loads in the LogIn / SignUp file and displays it at 750, 10
     * Grays out the go back/forward buttons if there is nowhere to go
     * Sets the buttons that are just for manager (ex. Add Product) visible just for the manager
     * @throws IOException
     */
    @FXML
    private void initialize() throws IOException {

        AnchorPane p = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/logged_tab.fxml"));
        searchField.setText(Window.search);

        p.setLayoutX(750);
        p.setLayoutY(10);
        if(!Window.canGoBackwards())
        {
            backButton.setStyle("-fx-background-color: GRAY");
            backButton.setTextFill(Paint.valueOf("BLACK"));
        }

        if(!Window.canGoForward())
        {
            forwardButton.setStyle("-fx-background-color: GRAY");
            forwardButton.setTextFill(Paint.valueOf("BLACK"));
        }

        if(!(Store.loggedIn instanceof Manager)) {
            addProduct.setVisible(false);
        }
        if(!(Store.loggedIn instanceof Customer)){
            cart.setVisible(false);
        }

        scene = topBox.getScene();
        pane.getChildren().add(p);
    }

    /**
     * When you click on the logo, change pages to the home page
     * @param mouseEvent The logo is clicked with the mouse
     * @throws IOException
     */
    public void onLogoClicked(MouseEvent mouseEvent) throws IOException {
        Window.switchPage("homePage");
    }
    /**
     * When you click on the cart, change pages to the user's cart
     * @param mouseEvent The shopping cart is clicked with the mouse
     * @throws IOException
     */
    public void onCartClicked(MouseEvent mouseEvent) throws IOException {
        Window.switchPage("usercart");
    }
    /**
     * When you click on Clothing, search by the product category APPAREL and display those items
     * @param mouseEvent The Clothing button is clicked with the mouse
     * @throws IOException
     */
    public void onClothingClicked(MouseEvent mouseEvent) throws IOException {
        SearchPage.invoke(ProductManager.Filters.CATEGORY, Product.ProductCategory.APPAREL);
    }
    /**
     * When you click on Accessories, search by the product category ACCESSORIES and display those items
     * @param mouseEvent The Accessories button is clicked with the mouse
     * @throws IOException
     */
    public void onAccessoriesClicked(MouseEvent mouseEvent) throws IOException {
        SearchPage.invoke(ProductManager.Filters.CATEGORY, Product.ProductCategory.ACCESSORIES);
    }

    /**
     * When you click on Shoes, search by the product category SHOES and display those items
     * @param mouseEvent The Shoes button is clicked with the mouse
     * @throws IOException
     */
    public void onShoesClicked(MouseEvent mouseEvent) throws IOException {
        SearchPage.invoke(ProductManager.Filters.CATEGORY, Product.ProductCategory.SHOES);
    }

    /**
     * When you click on the search bar, you can search by the name of a product
     * @throws IOException
     */
    @FXML
    private void search() throws IOException {
        Window.search = searchField.getText();
        SearchPage.invoke(ProductManager.Filters.NAME, Window.search == null ? "" : Window.search);

    }
    /**
     * Goes to the page you were just on
     * @param mouseEvent The < (go back) button is clicked
     * @throws IOException
     */
    @FXML
    private void onGoBack(MouseEvent mouseEvent) throws IOException {
        Window.goBack();
    }
    /**
     * Goes forward to the page you went back from
     * @param mouseEvent The > (go forward) button is clicked
     * @throws IOException
     */
    @FXML
    private void onForward(MouseEvent mouseEvent) throws IOException {
        Window.goForward();
    }

    /**
     * Goes to add product, only visible if you are a manager
     * @param mouseEvent The Add Product button is clicked
     * @throws IOException
     */
    public void onAddProduct(MouseEvent mouseEvent) throws IOException {
        Window.switchPage("addProduct");
    }

}
