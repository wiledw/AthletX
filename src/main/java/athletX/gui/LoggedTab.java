package athletX.gui;

import athletX.Customer;
import athletX.Store;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.scene.input.MouseEvent;


public class LoggedTab {

    @FXML
    private Button loggedButton;
    @FXML
    private Button signButton;
    @FXML
    private AnchorPane pane;


    /**
     * Initializes the Tab before it is loaded onto the window
     */

    @FXML
    private void initialize()
    {
        if(Store.loggedIn != null)
        {
            if(Store.loggedIn instanceof Customer){
                signButton.setText("Account");
            }
            else {
                signButton.setVisible(false);
            }

            loggedButton.setText("Log Out");

        } else
        {
            signButton.setText("Sign Up");
            loggedButton.setText("Log In");
        }

    }

    /**
     * Clicked when the signup button is pressed, redirects the user to the signup page
     * @param event The signUp button is clicked
     * @throws IOException
     */
    @FXML
    private void onSignup(MouseEvent event) throws IOException {
        if(Store.loggedIn == null) {
            Window.switchPage( "signup");
        } else
        {
            Window.switchPage( "profile");
        }
    }


    /**
     * Called when the login button is called, redirects the user to the login page
     * @param event the LogIn button is clicked
     * @throws IOException
     */
    @FXML
    private void onLog(MouseEvent event) throws IOException {
        if(Store.loggedIn == null) {
            Window.switchPage("login");
        } else {
            Store.loggedIn = null;
            Window.refreshPage();
        }


    }
}
