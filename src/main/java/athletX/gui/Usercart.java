package athletX.gui;

import athletX.Customer;
import athletX.Product;
import athletX.ShoppingCart;
import athletX.Store;
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

public class Usercart {
    @FXML
    public Button trash1;
    public Button trash2;
    public Button trash3;
    public Button trash4;
    @FXML
    private Scene scene;

    private ShoppingCart cart;
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

    public HBox cartRow1;
    private Product prod1;

    public HBox cartRow2;
    private Product prod2;

    @FXML
    private HBox cartRow3;
    private Product prod3;

    @FXML
    private HBox cartRow4;
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
            cartRow1.setVisible(false);
            } else {
                prod1 = cart.getItemAt(pageNum*4);
                populateRow(cartRow1, prod1);
            }

            if(lastPageCount < 2) {
                cartRow2.setVisible(false);
            } else {
                prod2 = cart.getItemAt(pageNum*4+1);
                populateRow(cartRow2, prod2);
            }

            if(lastPageCount < 3) {
                cartRow3.setVisible(false);
            } else {
                prod3 = cart.getItemAt(pageNum*4+2);
                populateRow(cartRow3, prod3);
            }

            if(lastPageCount < 4) {
                cartRow4.setVisible(false);
            } else {
                prod4 = cart.getItemAt(pageNum*4+3);
                populateRow(cartRow4, prod4);
            }

        } else {
            nextPage.setDisable(false);
            populateRow(cartRow1, cart.getItemAt(pageNum*4));
            populateRow(cartRow2, cart.getItemAt(pageNum*4+1));
            populateRow(cartRow3, cart.getItemAt(pageNum*4+2));
            populateRow(cartRow4, cart.getItemAt(pageNum*4+3));
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

    // Handlers for package buttons


    public void shippingHandler() throws IOException {
        Window.switchPage("checkout");
    }

    /**
     * selects which item to delete
     * @throws FileNotFoundException
     */
    public void trash1Handler() throws FileNotFoundException {
        trashItem(0);
    }
    public void trash2Handler() throws FileNotFoundException {
        trashItem(1);
    }
    public void trash3Handler() throws FileNotFoundException {
        trashItem(2);
    }
    public void trash4Handler() throws FileNotFoundException {
        trashItem(3);
    }

    /**
     * accessed from trashHandlers,
     * deletes the indexed item from user's cart
     * @param i
     * index offset of item to be deleted
     * @throws FileNotFoundException
     * from displayCart()
     */
    private void trashItem(int i) throws FileNotFoundException {
        cart.deleteProduct(pageNum*4+i);
        itemCount--;
        updateMaxIndex();
        displayCart();
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
     * initializes local ShoppingCart object,
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
        itemCount = ((Customer)Store.loggedIn).getCart().cartSize();
        cart = ((Customer)Store.loggedIn).getCart();
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

    /**
     * sends user to checkout
     *
     * @throws IOException
     * thrown if page is not found which
     * should never happen
     */
    public void shippingHandler(ActionEvent actionEvent) throws IOException {
        Window.switchPage("checkout");
    }
}