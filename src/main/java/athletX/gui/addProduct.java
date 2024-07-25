package athletX.gui;

import athletX.Manager;
import athletX.Product;
import athletX.ProductManager;
import athletX.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class addProduct {
    @FXML
    private Scene scene;

    @FXML
    private Pane pane;

    @FXML
    private ComboBox productType;

    @FXML
    private ToggleGroup categorySelect;
    private String category = "SHOES";
    @FXML
    private ToggleGroup genderSelect;
    @FXML
    private Label fileName, fileName1, fileName2, fileName3, labels[];
    private List<File> selectedFile = new ArrayList<>();
    @FXML
    private TextField priceDigits;
    @FXML
    private TextField priceDecimals;
    @FXML
    private TextField stockAmt;
    @FXML
    private TextField prodSize;
    @FXML
    private TextField prodCol;
    @FXML
    private TextField prodName;


    @FXML
    private Label errorMessage;

    /**
     * Initializes addProduct page,
     * creates labels for image file input
     * @throws IOException
     */
    @FXML
    private void initialize() throws IOException {
        labels = new Label[] {fileName, fileName1, fileName2, fileName3};
        VBox top = Window.loadPane();

        pane.getChildren().add(top);

        productType.getItems().clear();
    }


    /**
     * when the shoes category is selected,
     * enables size and colour options, disables subcategory option
     * @param actionEvent
     */
    public void selectShoes(ActionEvent actionEvent) {
        productType.getItems().clear();
        productType.setPromptText("");
        productType.setDisable(true);
        prodSize.setDisable(false);
        prodCol.setDisable(false);
        this.category = "SHOES";
    }

    /**
     * when the apparel category is selected,
     * enables size and colour options, enables subcategory option
     * @param actionEvent
     */
    public void selectApparel(ActionEvent actionEvent) {
        productType.getItems().clear();
        productType.setDisable(false);
        productType.setPromptText("Apparel Type");
        productType.getItems().add("SHIRTS");
        productType.getItems().add("PANTS");
        productType.getItems().add("SHORTS");
        productType.getItems().add("JACKET");
        this.category = "APPAREL";
        prodSize.setDisable(false);
        prodCol.setDisable(false);
    }

    /**
     * when the apparel category is selected,
     * disables size and colour options, enables subcategory option
     * @param actionEvent
     */
    public void selectAccessories(ActionEvent actionEvent) {
        productType.getItems().clear();
        productType.setDisable(false);
        productType.setPromptText("Accessory Type");
        productType.getItems().add("BAGS");
        productType.getItems().add("GLOVES");
        productType.getItems().add("SOCKS");
        productType.getItems().add("OTHER");
        prodSize.setDisable(true);
        prodCol.setDisable(true);
        this.category = "ACCESSORIES";
    }

    /**
     * when the "browse" button is pressed on the page,
     * prompts the user to select 1-4 image files from file explorer
     * populates labels and local File ArrayList object
     */
    public void selectImage() {

        FileChooser fChooser = new FileChooser();
        fChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", ".jpeg", "*.jfif"));
        selectedFile = fChooser.showOpenMultipleDialog(scene.getWindow());

        if(selectedFile == null || selectedFile.size() == 0)
        {
            return;
        }

        if(selectedFile.size() > 4)
        {
            giveError("Please select only 4 items.");
            selectedFile = null;
            return;
        }

        for (int i = 0; i < 4; i++) {
            if(i < selectedFile.size()) {
                labels[i].setText(selectedFile.get(i).getName());
            } else {
                labels[i].setText("");
            }
        }

    };

    /**
     * returns true if the integer portion of the price
     * is only numbers with a leading non-zero digit
     * or if it is zero
     * @return
     */
    public boolean sanitizePriceDigits() {
        return priceDigits.getText().matches("(([1-9][0-9]*)|0)");
    }

    /**
     * returns true if the decimal portion of the price
     * is exactly two digits
     * @return
     */
    public boolean sanitizePriceDecimals() {
        return priceDecimals.getText().matches("[0-9][0-9]");
    }

    /**
     * returns true if the stock is a non-negative integer
     * @return
     */
    public boolean sanitizeStock() {
        return stockAmt.getText().matches("([1-9][0-9]*|0)");
    }

    /**
     * submits all fields to be turned into a product,
     * displays relevant errors at bottom of page if inputs
     * are improperly formatted
     * @param actionEvent
     * @throws IOException
     */
    public void submitFields(ActionEvent actionEvent) throws IOException {
        if(!category.equals("SHOES") && (productType.getValue() == null)) {
            giveError("Please select an " + category.toLowerCase() + " subcategory.");
            return;
        }
        if(selectedFile == null  || selectedFile.size() == 0) {
            giveError("Please select a file");
            return;
        }

        if(!sanitizePriceDecimals() || !sanitizePriceDigits()) {
            giveError("Error in input of price.");
            return;
        }
        if(!sanitizeStock()) {
            giveError("Error in input of stock amount.");
            return;
        }
        if(prodSize.getText().equals("") && !(category.equals("ACCESSORIES"))) {
            giveError("Error in input of product size.");
            return;
        }
        if(prodCol.getText().equals("") && !(category.equals("ACCESSORIES"))) {
            giveError("Error in input of product colour.");
            return;
        }
        if(prodName.getText().equals("")) {
            giveError("Error in input of product name.");
            return;
        }

        for (File s: selectedFile) {
            File destination = new File("AppFiles\\images\\"+s.getName());
            Files.copy(s.toPath(), destination.toPath(), REPLACE_EXISTING);
        }

        Product.ProductCategory prodCat = Product.ProductCategory.valueOf(category);
        String productName = prodName.getText();
        int stockAmount = Integer.parseInt(stockAmt.getText());
        double price = Double.parseDouble(priceDigits.getText()) + Double.parseDouble(priceDecimals.getText())/100;
        String productSize = prodSize.getText();
        String productColour = prodSize.getText();
        String gender = ((ToggleButton)genderSelect.getSelectedToggle()).getText();
        String subCategory;
        if(!(productType.getValue() == null)){
            subCategory = productType.getValue().toString();
        } else {
            subCategory = "";
        }
        Product prod = ProductManager.createProduct(prodCat, productName, stockAmount, price, productSize, productColour, gender, subCategory, selectedFile.get(0).getName());

        for (int i = 0; i < Math.min(4, selectedFile.size()); i++) {
            prod.getSupImageDir()[i] = "AppFiles/images/" + selectedFile.get(i).getName();

        }

        ProductManager.serialize(prod);

        Window.switchPage("homePage");
    }

    public void giveError(String message) {
        errorMessage.setText(message);
        errorMessage.setVisible(true);
    }
}
