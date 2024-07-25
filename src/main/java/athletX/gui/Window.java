package athletX.gui;

import athletX.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Stack;


public class Window extends Application
{

    private static final Stack<String> back = new Stack<>();
    private static final int SIZE = 200;

    private static final Stack<String> forward = new Stack<>();

    private static String currentPage;

    public static VBox topPane;

    private static Stage mainWindow;

    public static String search;

    /**
     * Loads the home page and the header on startup
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        topPane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/header.fxml"));

        Scene root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/homePage.fxml"));

        currentPage = "homePage";

        mainWindow = primaryStage;



        mainWindow.setFullScreen(false);
        mainWindow.setResizable(false);

        primaryStage.setScene(root);

        primaryStage.setTitle("athletX");
        primaryStage.getIcons().add(getImage("test.png"));

        primaryStage.show();


    }

    /**
     * This function switches the current page to a new page, ands and removes from the forward and back stacks
     * in order to manage navigation, and redirects the user to the login page if they are not logged in and attempting
     * to access the shopping cart or checkout pages
     * @param newS the name of the scene we want to switch to
     * @throws IOException
     */
    public static void switchPage(String newS) throws IOException {
        if(back.size() == SIZE)
        {
            back.removeElementAt(0);
        }



        if(forward.size() > 0 && forward.peek().equals(newS))
        {
            goForward();
            return;
        } else {
            forward.clear();
        }

        if(!productPage.buttonDeleted) {
            back.push(currentPage);
        } else {
            productPage.buttonDeleted = false;
        }

        currentPage = newS;


        switch (currentPage)
        {
            case "usercart", "checkout":
                if (Store.loggedIn == null)
                {
                    switchPage("login");
                    return;
                }
                break;
            case "addProduct":
                if(!(Store.loggedIn instanceof Manager))
                {
                    goBack();
                    return;
                }
        }




        Scene root = FXMLLoader.load(Window.class.getClassLoader().getResource("fxml/" + currentPage + ".fxml"));



        mainWindow.setScene(root);


    }

    /**
     * A function that simplifies the process of getting an image from out image source folder icons
     * @param file The name of the file
     * @return an Image
     * @throws IOException
     */
    public static Image getImage(String file) throws IOException {
        return new Image(Window.class.getClassLoader().getResource("icons/" + file).openStream());
    }

    /**
     * Checks if the user can go back, if they can, it checks what type of user they are (Customer, or Manger)
     * and reacts accordingly by loading the appropriate page
     * @throws IOException
     */
    public static void goBack() throws IOException {


        if(canGoBackwards())
        {


            String backPage = back.pop();


            switch (backPage) {
                case "login", "signup":
                    if (Store.loggedIn != null) {
                        goBack();
                        return;
                    }
                   break;
                case "checkout", "usercart":
                    if(Store.loggedIn instanceof Manager || currentPage.equals("login"))
                    {
                        goBack();
                        return;
                    }
                case "addProduct":
                    if(!(Store.loggedIn instanceof Manager))
                    {
                        goBack();
                        return;
                    }
                    break;
            }

            switch (currentPage) {
                case "login", "signup":
                    if (Store.loggedIn == null) {
                        forward.push(currentPage);
                    }
                    break;
                case "checkout", "usercart":
                    if(Store.loggedIn instanceof Customer)
                    {
                        forward.push(currentPage);
                    }
                    break;
                case "addProduct":
                    if(Store.loggedIn instanceof Manager)
                    {
                        forward.push(currentPage);
                    }
                    break;
                default:
                    forward.push(currentPage);
            }




            currentPage = backPage;


            mainWindow.setScene(FXMLLoader.load(Window.class.getClassLoader().getResource("fxml/" + currentPage + ".fxml")));
            return;
        }

        if(currentPage.equals("login") || currentPage.equals("signup") || currentPage.equals("checkout") || currentPage.equals("usercart"))
        {
            switchPage("homePage");
        }
    }

    /**
     * Returns the loaded fxml file of the header which is preset on every page ()
     * @return returns the header of the page
     * @throws IOException
     */
    public static VBox loadPane() throws IOException {
        return FXMLLoader.load(Window.class.getClassLoader().getResource("fxml/header.fxml"));
    }

    /**
     * Refreshes the page by redirecting to the page we are currently on
     * @throws IOException
     */
    public static void refreshPage() throws IOException {
       switchPage(currentPage);
    }

    /**
     * Checks if the user can go forwards, by checking if the forward stack is non-empty
     * @return If the user can go forward
     */
    public static boolean canGoForward()
    {
        return forward.size() > 0;
    }

    /**
     * Checks if the user can go backwards, by checking if the back stack is non-empty
     * @return If the user can go backward
     */
    public static boolean canGoBackwards()
    {
        return back.size() > 0;
    }

    /**
     * Pops from the forwards stack and sends the user there
     * @throws IOException
     */
    public static void goForward() throws IOException {
        if(canGoForward())
        {
            back.push(currentPage);

            currentPage = forward.pop();

            mainWindow.setScene(FXMLLoader.load(Window.class.getClassLoader().getResource("fxml/" + currentPage + ".fxml")));
        }
    }
}
