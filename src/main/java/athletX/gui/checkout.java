package athletX.gui;

import athletX.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.Calendar;
import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;



public class checkout {

    @FXML
    private Label outOfStockError;
    @FXML
    private Scene scene;

    public Label mmError;
    public Label yyError;
    public Label cvvError;
    @FXML
    private Label firstnameError;
    @FXML
    private Label lastnameError;
    @FXML
    private Label streetaddressError;
    @FXML
    private Label cityError;
    @FXML
    private Label countryError;
    @FXML
    private Label cardnumberError;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField streetAddress;
    @FXML
    private TextField optionalAddress;
    @FXML
    private TextField city;
    @FXML
    private TextField country;
    @FXML
    private TextField cardNumber;
    @FXML
    private Label subtotal;

    @FXML
    private Label shipping;

    @FXML
    private Label total;
    @FXML
    private TextField mm;
    @FXML
    private TextField yy;
    @FXML
    private TextField cvv;

    @FXML
    private VBox deliveryMenu;

    @FXML
    private ToggleGroup deliveryGroup;

    private double deliveryP = 0.0;
    private double subtotalP = 0.0;

    private Customer currentCustomer;

    private int year;

    @FXML
    private void initialize() throws IOException {

        ShoppingCart cart = ((Customer)Store.loggedIn).getCart();
        subtotalP = 0;
        for (Product c: cart) {
            subtotalP += c.getPrice();
        }

        RadioButton r = (RadioButton) deliveryGroup.getSelectedToggle();
        deliveryP = r.getText().equals("Standard") ? 10 : 15;

        String formattedSubtotalP = String.format("$%.2f", subtotalP);
        subtotal.setText(formattedSubtotalP);
        String formattedDeliveryP = String.format("$%.2f", deliveryP);
        shipping.setText(formattedDeliveryP);
        String formattedTotal = String.format("$%.2f", deliveryP + subtotalP);
        total.setText(formattedTotal);

        deliveryGroup.selectedToggleProperty().addListener( (observable,  oldValue,  newValue) -> {
            deliveryP = ((RadioButton)newValue).getText().equals("Standard") ? 10 : 15;

            String formattedDeliveryP1 = String.format("$%.2f", deliveryP);
            shipping.setText(formattedDeliveryP1);
            String formattedTotal1 = String.format("$%.2f", deliveryP + subtotalP);
            total.setText(formattedTotal1);
        });

    }


    /**
     * Called when the user clicks the checkout button. Makes all the proper checks for a transaction
     * and then places an order
     * @param mouseEvent
     * @throws IOException
     */
    public void onCheckout(MouseEvent mouseEvent) throws IOException {
        firstnameError.setText("");
        lastnameError.setText("");
        streetaddressError.setText("");
        cityError.setText("");
        countryError.setText("");
        cardnumberError.setText("");
        mmError.setText("");
        yyError.setText("");
        cvvError.setText("");
        outOfStockError.setText("");

        currentCustomer = ((Customer) Store.loggedIn);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        boolean checkOut = true;

        if (firstName.getText().equals("")) {
            firstnameError.setText("Please enter your first name");
            checkOut = false;
        }

        if (lastName.getText().equals("")) {
            lastnameError.setText("Please enter your last name");
            checkOut = false;
        }

        if (streetAddress.getText().equals("")) {
            streetaddressError.setText("Please enter your street address");
            checkOut = false;
        }

        if (city.getText().equals("")) {
            cityError.setText("Please enter your city");
            checkOut = false;
        } else if (!isValidCity(city.getText())) {
            cityError.setText("Please enter a correct city");
            checkOut = false;
        }

        if (country.getText().equals("")) {
            countryError.setText("Please enter your country");
            checkOut = false;
        } else if (!isValidCountry(country.getText())) {
            countryError.setText("Please enter a correct country");
            checkOut = false;
        }

        if (cardNumber.getText().equals("")){
            cardnumberError.setText("Please enter your card");
            checkOut = false;
        } else if (!cardNumber.getText().matches("\\d{16}")) {
            cardnumberError.setText("Please enter a valid card (16 digits)");
            checkOut = false;
        }

        if (mm.getText().equals("")) {
            mmError.setText("Please enter your card MM");
            checkOut = false;
        } else if (!mm.getText().matches("\\d{2}")) {
            mmError.setText("Please enter a valid MM (2 digits)");
            checkOut = false;
        } else if (Integer.parseInt(mm.getText()) > 12) {
            mmError.setText("Please enter a valid month");
        }

        if (yy.getText().equals("")) {
            yyError.setText("Please enter your card YY");
            checkOut = false;
        } else if (!yy.getText().matches("\\d{2}")) {
            yyError.setText("Please enter a valid YY (2 digits)");
            checkOut = false;
        } else if (Integer.parseInt(yy.getText()) < year-2000) {
            yyError.setText("Card expired");
            checkOut = false;
        }

        if (cvv.getText().equals("")) {
            cvvError.setText("Please enter your card CVV");
            checkOut = false;
        } else if (!cvv.getText().matches("\\d{3}")) {
            cvvError.setText("Please enter a valid CVV (3 digits)");
            checkOut = false;
        }

        if (!checkOut)
            return;


        ShoppingCart cart = currentCustomer.getCart();
        allOrders orders = currentCustomer.getOrders();
        for (Product c : cart){
            if (c.getStock() == 0){
                outOfStockError.setText(c.getName() + " is out of stock");
                return;
            }
        }


        for(Product c : cart){
            ProductOrder.createProductOrder(currentCustomer, c, orders);
            c.reduceStock();
        }

        cart.emptyCart();
        Window.switchPage("orders");

    }


    /**
     * Checks if the user entered a valid country name
     * @param country The name of the country entered
     * @return If the user entered a proper country name
     */
    public boolean isValidCountry(String country){
        String[] countryList = Locale.getISOCountries();
        for (String i : countryList){
            Locale locale = new Locale("", i);
            String name = locale.getDisplayCountry();
            if (name.equalsIgnoreCase(country)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the user entered a valid city name
     * @param city The name of the city entered
     * @return If the user entered a proper city name
     */
    public boolean isValidCity(String city){
        String[] cityList = TimeZone.getAvailableIDs();
        for (String i : cityList) {
            String[] parts = i.split("/");
            String cityName = parts[parts.length - 1].replace("_", " ");
            if (city.equalsIgnoreCase(cityName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calls when the user presses the back button. Goes back to the previous page.
     * @param mouseEvent
     * @throws IOException
     */
    public void onGoBack(MouseEvent mouseEvent) throws IOException {
        Window.goBack();
    }

}
