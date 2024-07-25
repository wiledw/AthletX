package athletX.gui;

import athletX.Store;
import athletX.exception.CredentialException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * Functionality for the login.fxml page
 */

public class login {
    @FXML
    private Scene scene;
    @FXML
    private Label clicked;
    @FXML
    private Button button;

    @FXML
    private TextField field;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorMessage;

    @FXML
    private void initialize() {
        field.requestFocus();
    }

    /**
     * Functionality for "Create a new account"
     * Redirects to signup.fxml when clicked
     * @param mouseEvent
     */
    public void createNewAccount(MouseEvent mouseEvent) throws IOException {
        Window.switchPage("signup");
    }

    /**
     * Functionality for "Login" button
     * Takes user input and verifies they are in the system before granting access
     * Will display "Invalid username, or password" error message if not
     * @param mouseEvent
     */
    public void onLoginClick(MouseEvent mouseEvent) throws IOException {
        String username = field.getText();
        String pass = password.getText();

        try {
            Store.login(username, pass);
        } catch (CredentialException e)
        {
            errorMessage.setText("Invalid username, or password");
            return;
        }

        Window.goBack();
    }

    /**
     * Functionality for "Forgot password?" label
     * Redirects user to reset_pass.fxml page
     * @param mouseEvent
     */
    public void onForgotPassword(MouseEvent mouseEvent) throws IOException {
        Window.switchPage("reset_pass");
    }

    /**
     * Functionality for "Back" label
     * Redirects user to athletX's home page as a guest
     * @param mouseEvent
     */
    public void onBackClicked(MouseEvent mouseEvent) throws IOException {
        Window.switchPage("homePage");
    }
}
