package athletX.gui;


import athletX.Customer;
import athletX.Store;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for Profile editing page
 */
public class profile {

    @FXML
    private Scene scene;
    private Customer currentUser;
    @FXML
    private Label updateLastnameError, updateFirstnameError, updateUsernameError;
    @FXML
    private TextField firstName, lastName, userName;
    @FXML
    private Button accountDetailsLabel;
    @FXML
    private ImageView logo, shoppingCart;
    @FXML
    private Button infoPage;
    @FXML
    private AnchorPane storePage, customerServicePage, orderPage;
    @FXML
    private Label greeting;
    @FXML
    private Pane pane;

    public void onContShoppingClicked(MouseEvent mouseEvent) throws IOException {
        Window.switchPage("homePage");
    }


    public void onCustomerServiceClicked(MouseEvent mouseEvent) throws IOException {
        Window.switchPage("customerservice");
    }

    public void onOrdersClicked(MouseEvent mouseEvent) throws IOException {
        Window.switchPage("orders");
    }

    /**
     * Initialize what the page will look like when we enter the profile page
     * @throws IOException
     */
    @FXML
    private void initialize() throws IOException {
        VBox top = Window.loadPane();

        pane.getChildren().add(top);

        if (Store.loggedIn instanceof Customer) {
            currentUser = ((Customer) Store.loggedIn);
            greeting.setText("Welcome, " + Store.loggedIn.getFirstName());
        }
        userName.setText(currentUser.getUsername());
        firstName.setText(currentUser.getFirstName());
        lastName.setText(currentUser.getLastName());
    }

    public void editInfoClicked(MouseEvent mouseEvent) {
        updateUsernameError.setText("");
        updateFirstnameError.setText("");
        updateLastnameError.setText("");

        boolean updateInfo = true;

        if(userName.getText().equals(""))
        {
            updateUsernameError.setText("Please enter your new username");
        } else if(!userName.getText().equals(currentUser.getUsername()))
        {
            Store.editUsername(currentUser.getUsername(), userName.getText());
            updateUsernameError.setText("Username updated.");

        }

        if(firstName.getText().equals(""))
        {
            updateFirstnameError.setText("Please enter your new first name");
        } else if(!firstName.getText().equals(currentUser.getFirstName()))
        {
            currentUser.setFirstName(firstName.getText());
            updateFirstnameError.setText("First name updated.");

        }
        if(lastName.getText().equals(""))
        {
            updateLastnameError.setText("Please enter your new last name");
        } else if(!lastName.getText().equals(currentUser.getLastName()))
        {
            currentUser.setLastName(lastName.getText());
            updateLastnameError.setText("Last name updated.");

        }



    }

    public void onGoBack(MouseEvent mouseEvent) throws IOException {
        Window.switchPage("profile");
    }


}

