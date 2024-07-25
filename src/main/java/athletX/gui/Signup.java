package athletX.gui;

import athletX.Store;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Signup {

    @FXML
    private Scene scene;
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField shippingAddress;

    @FXML
    private TextField username;

    @FXML
    private PasswordField pass1;

    @FXML
    private PasswordField pass2;

    @FXML
    private javafx.scene.control.Label passError;

    @FXML
    private Label userError;

    @FXML
    private Label firstNameError;

    @FXML
    private Label lastNameError;

    @FXML
    private Label shippingAddressError;

    /**
     * This is method makes the cursor placed in firstName field when we enter the signup page and ready for user input
     */
    @FXML
    private void initialize()
    {
        firstName.requestFocus();

    }

    public void onSignup(MouseEvent mouseEvent) throws IOException {
        userError.setText("");
        passError.setText("");
        firstNameError.setText("");
        lastNameError.setText("");
        shippingAddressError.setText("");

        boolean signUp = true;

        if(firstName.getText().equals(""))
        {
            firstNameError.setText("Please enter your first name");
            signUp = false;
        }

        if(lastName.getText().equals("")) {
            lastNameError.setText("Please enter your last name");
            signUp = false;
        }

        if(shippingAddress.getText().equals(""))
        {
            shippingAddressError.setText("Please enter your shipping address");
            signUp = false;
        }

        if (Store.userExists(username.getText()))
        {
            userError.setText("Username already taken");
            signUp = false;
        }

        if(pass1.getText().equals(""))
        {
            passError.setText("Please enter a password");
            signUp = false;
        }

        if(!pass1.getText().equals(pass2.getText()))
        {
            passError.setText("Passwords don't match");
            signUp = false;
        }

        if(!signUp)
            return;

        Store.addCustomer(username.getText(), firstName.getText(), lastName.getText(), shippingAddress.getText());
        Store.setPassword(username.getText(), pass1.getText());


        Window.switchPage("login");

    }

    public void onGoBack(MouseEvent mouseEvent) throws IOException {
        Window.goBack();
    }

}
