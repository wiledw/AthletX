package athletX.gui;

import athletX.Product;
import athletX.ProductManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class AccessoryPage {

    @FXML
    private Scene scene;

    @FXML
    private AnchorPane pane;

    @FXML
    private GridPane grid;
    @FXML
    private TextField search;

    @FXML
    private void initialize() throws IOException {
        load(new Product[]{new Product("test", 69, 69,42.0, Product.ProductCategory.ACCESSORIES, "ball.png"), new Product("test2", 69,69,42.0, Product.ProductCategory.SHOES, "bottle.png")});
    }

    /**
     * loads all the products related to accessories in a grid.
     * Adds new accessory to the grid if a new accessory is added to GenData.java
     */
    private void load(Product[] relProds) throws IOException {
        GridPane[] panes = new GridPane[relProds.length];

        for (int i = 0; i < relProds.length; i++)
        {
            Product prod = relProds[i];

            panes[i] = new GridPane();
            panes[i].getColumnConstraints().add(new ColumnConstraints(250));
            panes[i].getRowConstraints().add(new RowConstraints(120));
            panes[i].getRowConstraints().add(new RowConstraints(60));

            BorderPane p = new BorderPane();
            p.setMaxSize(250,120);

            Label productTitle = new Label(prod.getName());
            productTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
            productTitle.setAlignment(Pos.CENTER);
            productTitle.setContentDisplay(ContentDisplay.CENTER);
            productTitle.setPrefWidth(60);
            productTitle.setPrefHeight(250);

            panes[i].add(productTitle, 0, 1);

            ImageView img = new ImageView("icons/" + prod.getImageDir());
            img.setPreserveRatio(true);
            img.setFitWidth(250);
            img.setFitHeight(120);

            panes[i].add(img, 0, 0);
            panes[i].setVisible(true);

            grid.add(panes[i], i % 3, i / 3);

            panes[i].setOnMouseClicked((event) -> {
                try {
                    productPage.loadPage(prod);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }

    }




}
