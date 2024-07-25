package athletX.gui;

import athletX.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class productPage {

    private static Product currentProduct;

    @FXML
    private Scene scene;

    @FXML
    private AnchorPane pane;

    @FXML
    private VBox border1, border2, border3, border4;

    private int selectedImage = -1;

    @FXML
    private Button delProd;
    public static boolean buttonDeleted = false;

    private int selectedSize = -1;


    @FXML
    private ImageView main, sub1, sub2, sub3, sub4;

    @FXML
    private Label prodTitle;

    @FXML
    private Label priceLabel, sizeLabel, stockLabel, genderLabel, colourLabel, typeLabel, errorMsg;

    private int selectedColour = -1;

    /**
     * Initializes the product page
     * @author Tahamid
     * @throws IOException
     */
    @FXML
    private void initialize() throws IOException {
        pane.getChildren().add(Window.loadPane());
        VBox borders[] = new VBox[]{border1, border2, border3, border4};
        main.setImage(new Image(new FileInputStream(currentProduct.getImageDir())));
        prodTitle.setText(currentProduct.getName());
        priceLabel.setText(String.format("$%.2f", currentProduct.getPrice()));
        stockLabel.setText(currentProduct.getStock()+"");

        switch(currentProduct.getProductCategory()) {
            case SHOES:
                sizeLabel.setText(((Shoes)currentProduct).getSize());
                colourLabel.setText(((Shoes)currentProduct).getColour());
                genderLabel.setText(((Shoes)currentProduct).getGender());
                typeLabel.setText("N/A");
                break;
            case ACCESSORIES:
                sizeLabel.setText("N/A");
                colourLabel.setText("N/A");
                genderLabel.setText("N/A");
                typeLabel.setText(((Accessories)currentProduct).getType().toString());
                break;
            case APPAREL:
                sizeLabel.setText(((Apparel)currentProduct).getSize());
                colourLabel.setText(((Apparel)currentProduct).getColour());
                genderLabel.setText(((Apparel)currentProduct).getGender());
                typeLabel.setText(((Apparel)currentProduct).getType().toString());
                break;
            default:
                break;
        }

        if(!(Store.loggedIn instanceof Manager)) {
            delProd.setVisible(false);
        }



        ImageView[] images = new ImageView[]{sub1, sub2, sub3, sub4};

        for (int i = 0; i < images.length; i++) {
            if(currentProduct.getSupImageDir()[i] == null) {
                images[i].setImage(null);
                continue;
            }

            images[i].setImage(new Image(new FileInputStream(currentProduct.getSupImageDir()[i])));
        }


        for (int i = 0; i < borders.length; i++) {
            int index = i;

            borders[i].setOnMouseEntered(event -> {
                        if (!(index == selectedImage)) {
                            borders[index].setStyle("-fx-border-color: BLACK; -fx-border-width: 2px 2px 2px 2px");
                        }
                    }
            );
            borders[i].setOnMouseExited(event -> {
                if (selectedImage == index) {
                    borders[index].setStyle("-fx-border-color: BLUE; -fx-border-width: 2px 2px 2px 2px");
                } else {
                    borders[index].setStyle("");
                }
            });

            borders[i].setOnMouseClicked(event -> {
                if (selectedImage > -1)
                    borders[selectedImage].setStyle("");
                selectedImage = index;
                borders[selectedImage].setStyle("-fx-border-color: BLUE; -fx-border-width: 2px 2px 2px 2px");
                main.setImage(images[selectedImage].getImage());

            });

        }

    }

    /**
     * Adds a product to the logged in Customer's cart
     * @throws IOException
     */
    public void addToCart() throws IOException {
        if(currentProduct.getStock() > 0)
        {
            if(Store.loggedIn instanceof Manager)
            {
                errorMsg.setText("Please log in as a customer");
            } else {
                if(Store.loggedIn instanceof Customer)
                    ((Customer)Store.loggedIn).addToCart(currentProduct);
                Window.switchPage("usercart");
            }
        }
    }

    /**
     * Adds the current product to the customer's cart and proceeds to checkout page.
     * @throws IOException
     */
    public void purchaseNow() throws IOException {
        if(currentProduct.getStock() > 0)
        {
            if(Store.loggedIn instanceof Manager)
            {
                errorMsg.setText("Please log in as a customer");
            } else {
                if(Store.loggedIn instanceof Customer)
                    ((Customer)Store.loggedIn).addToCart(currentProduct);
                Window.switchPage("checkout");
            }
        }
    }

    /**
     * Loads page based on the product
     * @param product
     * @throws IOException
     */
    public static void loadPage(Product product) throws IOException {
        currentProduct = product;
        Window.switchPage("productPage");
    }


    /**
     * Called when the manager clicks the button. Deletes the product from the website
     * @param mouseEvent
     * @throws IOException
     */
    public void onDelClicked(MouseEvent mouseEvent) throws IOException {

        if(currentProduct.getName().equals("appa")) {
            errorMsg.setText("leave appa alone...");
            return;
        }

        System.out.println(ProductManager.deleteProduct(currentProduct.getprodId()));

        buttonDeleted = true;

        Window.switchPage("homePage");
    }
}
