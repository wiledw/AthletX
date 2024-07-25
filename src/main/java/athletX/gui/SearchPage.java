package athletX.gui;

import athletX.Product;
import athletX.ProductManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/** This code defines a JavaFX controller class for a home page GUI. The code makes use of
 the Product and ProductManager classes from the AthletX package.
 */

public class SearchPage {
    @FXML
    private Scene scene;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label resultNum;

    @FXML
    private GridPane grid;

    private static List<Product> relProds;

    private static String searchText;
    private static ProductManager.Filters currentFilter;
    private static Object[] currentParams;

    private int page;

    /**
     * This method is called when the associated FXML file is loaded.
     * @throws IOException If error occurs while loading data.
     */
    @FXML
    private void initialize() throws IOException {
        loadProds();
        VBox b = Window.loadPane();
        pane.getChildren().add(b);


        load();
    }


    /**
     * 'load()' is used to load and display the products
     */

    private void load() throws IOException {
        grid.getChildren().clear();
        GridPane[] panes = new GridPane[Math.min(relProds.size(), (page + 1) * 6)];



        for (int i = page * 6; i < Math.min(relProds.size(), (page + 1) * 6); i++)
        {
            int index =  i % 6;
            Product prod = relProds.get(i);
            Border noHover = new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(2)));
            Border hover = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(4)));

            panes[index] = new GridPane();
            panes[index].setMaxSize(250, 180);
            panes[index].setBorder(noHover);
            panes[index].setCursor(Cursor.HAND);

            panes[index].getColumnConstraints().add(new ColumnConstraints(250));
            panes[index].getRowConstraints().add(new RowConstraints(120));
            panes[index].getRowConstraints().add(new RowConstraints(60));



            panes[index].setOnMouseClicked(event -> {
                try {
                    productPage.loadPage(prod);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            panes[index].setOnMouseEntered(event -> panes[index].setBorder(hover));
            panes[index].setOnMouseExited(event -> panes[index].setBorder(noHover));


            BorderPane p = new BorderPane();
            p.setMaxSize(250,120);

            Text productTitle = new Text(prod.getName());
            productTitle.setFont(Font.font("System", FontWeight.BOLD, 20));

            productTitle.setWrappingWidth(250);



            panes[index].add(productTitle, 0, 1);

            ImageView img = new ImageView(new Image(new FileInputStream(prod.getImageDir())));
            img.setPreserveRatio(true);
            img.setFitWidth(225);
            img.setFitHeight(108);

            p.setCenter(img);
            Label price = new Label(String.format("$%.2f",prod.getPrice()));
            price.setFont(Font.font("System", FontWeight.BOLD, 18));
            p.setTop(price);
            p.setMaxSize(250, 120);

            panes[index].add(p, 0, 0);
            panes[index].setVisible(true);

            grid.add(panes[index], (i % 6) % 3, (i % 6) / 3);

            panes[index].setOnMouseClicked((event) -> {
                try {
                    productPage.loadPage(prod);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }

        resultNum.setText(relProds.size() == 0 ? "No results" : String.format("%d-%d of %d", page*6 + 1, Math.min(page * 6 + 6, relProds.size()), relProds.size()));

    }

    /**
     * takes user to the next page
     */
    private void nextPage() throws IOException {
        if((page + 1) * 6 < relProds.size())
        {
            page++;
            load();
        }
    }

    /**
     * takes user to the previous page
     */
    private void previousPage() throws IOException {
        if(page > 0)
        {
            page--;
            load();
        }
    }

    /**
     * sets the current filter and parameters,
     * loads products, and switches to the search page.
     */

    public static void invoke(ProductManager.Filters filter, Object... params) throws IOException {
        currentFilter = filter;
        currentParams = params;

        loadProds();

        Window.switchPage("searchPage");

    }

    private static void loadProds()
    {
        relProds = ProductManager.getFilteredProduct(currentFilter, currentParams);
        if(currentFilter == ProductManager.Filters.NAME) {
            searchText = ((String) currentParams[0]);
        } else
        {
            searchText = "";
        }
    }

    /**
     * detects whether the user has scrolled up or down on the screen
     * and then calls either nextPage() or previousPage() based on the direction.
     */
    @FXML
    private void onScroll(ScrollEvent scrollEvent) throws IOException {
        if(scrollEvent.getDeltaY() < 0)
        {
            nextPage();
        } else
        {
            previousPage();
        }
    }
}
