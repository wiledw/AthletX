package athletX.gui;

import athletX.Store;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * Functionality for the reset_pass.fxml page
 */
public class resetPass {
    @FXML
    private Scene scene;

    @FXML
    private TextField username;

    @FXML
    private PasswordField pass1;

    @FXML
    private PasswordField pass2;

    @FXML
    private Label passError;

    @FXML
    private Label userError;

    @FXML
    private void initialize()
    {
        username.requestFocus();
    }


    /**
     * Functionality for the "Reset" button
     * When it is clicked, will set the user and their corresponding password to the given input
     * If no input is given, an error message is returned
     * If the incorrect current password is given, will prompt user to re-enter
     */
    public void onPassReset(MouseEvent mouseEvent) throws IOException {
        userError.setText("");
        passError.setText("");

        if (!Store.userExists(username.getText()))
        {
            userError.setText("User does not exist");
            return;
        }

        if(!pass1.getText().equals(pass2.getText()))
        {
            passError.setText("Passwords don't match");
            return;
        }

        Store.setPassword(username.getText(), pass1.getText());


        Window.goBack();

    }

    /**
     * Functionality for the "Back" label
     * Will take user to login page
     * @param mouseEvent
     * @throws IOException
     */
    public void onGoBack(MouseEvent mouseEvent) throws IOException {
        Window.goBack();
    }
}
