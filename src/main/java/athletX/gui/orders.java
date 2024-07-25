package athletX.gui;

import athletX.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class orders {

    @FXML
    private Scene scene;

    private allOrders orders;
    private int itemCount;
    private int pageNum;

    private int maxIndex;


    @FXML
    private Button prevPage;
    @FXML
    private Button nextPage;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label pageLabel;

    public HBox orderRow1;
    private Product prod1;

    public HBox orderRow2;
    private Product prod2;

    @FXML
    private HBox orderRow3;
    private Product prod3;

    @FXML
    private HBox orderRow4;
    private Product prod4;

    /**
     * manages displaying the current page and
     * updating/hiding the cart rows
     * @throws FileNotFoundException
     * may throw error if a product does not have an
     * image properly linked in the AppFiles/images directory
     */
    private void displayCart() throws FileNotFoundException {
        if(pageNum == maxIndex) {
            nextPage.setDisable(true);
            int lastPageCount = ((itemCount - 1) % 4) + 1; // If user is on the last page, checks successively to see if a certain row should be rendered
            System.out.println(lastPageCount);
            if(lastPageCount < 1) {
                orderRow1.setVisible(false);
            } else {
                prod1 = orders.getItemAt(pageNum*4).getProduct();
                populateRow(orderRow1, prod1);
            }

            if(lastPageCount < 2) {
                orderRow2.setVisible(false);
            } else {
                prod2 = orders.getItemAt(pageNum*4+1).getProduct();
                populateRow(orderRow2, prod2);
            }

            if(lastPageCount < 3) {
                orderRow3.setVisible(false);
            } else {
                prod3 = orders.getItemAt(pageNum*4+2).getProduct();
                populateRow(orderRow3, prod3);
            }

            if(lastPageCount < 4) {
                orderRow4.setVisible(false);
            } else {
                prod4 = orders.getItemAt(pageNum*4+3).getProduct();
                populateRow(orderRow4, prod4);
            }

        } else {
            nextPage.setDisable(false);
            populateRow(orderRow1, orders.getItemAt(pageNum*4).getProduct());
            populateRow(orderRow2, orders.getItemAt(pageNum*4+1).getProduct());
            populateRow(orderRow3, orders.getItemAt(pageNum*4+2).getProduct());
            populateRow(orderRow4, orders.getItemAt(pageNum*4+3).getProduct());
        }

        if(pageNum == 0)
            prevPage.setDisable(true);
        else
            prevPage.setDisable(false);

        if(pageNum == maxIndex) {
            if(itemCount == 0)
                pageLabel.setText("0 of 0");
            else
                pageLabel.setText((pageNum*4+1) + "-" + itemCount + " of " + itemCount);
        } else {
            pageLabel.setText((pageNum*4+1) + "-" + (pageNum*4+4) + " of " + itemCount);
        }
    }

    /**
     * takes an HBox and a Product,
     * populates said HBox with information from Product
     * @param row
     * row to be populated
     * @param prod
     * product indexed by that row
     * @throws FileNotFoundException
     */
    private void populateRow(HBox row, Product prod) throws FileNotFoundException {
        row.setVisible(true);
        ((ImageView)row.getChildren().get(0)).setImage(new Image(new FileInputStream(prod.getImageDir())));
        ((Label)(((Pane)row.getChildren().get(1)).getChildren().get(0))).setText(prod.getName());
        ((Label)(((Pane)row.getChildren().get(2)).getChildren().get(0))).setText(String.format("$%.2f",prod.getPrice()));
    }
    /**
     * used on initialization and item deletion
     * makes sure that the user cannot index items
     * out of bounds of cart
     */
    private void updateMaxIndex() {
        if(itemCount <= 4) {
            maxIndex = 0;
        } else {
            maxIndex = (itemCount - 1) / 4;
        }
    }
    /**
     * if the user is logged in as a customer,
     * initializes local allOrders object,
     * else prompts user to log in
     * @throws IOException
     * thrown from displayCart()
     */
    @FXML
    public void initialize() throws IOException {
        VBox top = Window.loadPane();

        pane.getChildren().add(top);

        if(! (Store.loggedIn instanceof Customer)) {
            Window.switchPage("login");
        }
        pageNum  = 0;
        itemCount = ((Customer)Store.loggedIn).getOrders().cartSize();
        orders = ((Customer)Store.loggedIn).getOrders();
        updateMaxIndex();
        displayCart();
    }
    /**
     * when the go left button is pressed, decrease page by 1,
     * display the new page
     * @throws FileNotFoundException
     * thrown from displayCart
     */
    public void cycleLeft() throws FileNotFoundException {
        pageNum = (pageNum - 1);
        displayCart();
    }
    /**
     * when the go righ button is pressed, increase page by 1,
     * display the new page
     * @throws FileNotFoundException
     * thrown from displayCart
     */
    public void cycleRight() throws FileNotFoundException {
        pageNum = (pageNum + 1);
        displayCart();
    }
}