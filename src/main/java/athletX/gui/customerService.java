package athletX.gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;

/**
 * Replaces variables q1, q2, q3, a1, a2, a3 with text when User
 * clicks on the various buttons displayed on the page
 *
 * recClicked() --> displays recommended page's fqa
 * shipClicked() --> displays shipping page's fqa
 * returnClicked() --> displays return page's fqa
 * accountClicked() --> displays account page's fqa
 * paymentClicked() --> displays payment page's fqa
 * miscClicked() --> displays miscellaneous page's fqa
 */

public class customerService {

    @FXML
    private Label q1, q2, q3;

    @FXML
    private TextArea a1, a2, a3;

    @FXML
    private Button reco, shipping, refund, accountManaging, payment, miscellaneous;

    @FXML
    private Scene scene;

    @FXML
    public Pane pane;


    /**
     * Adds header.fxml at the top of the page
     * @throws IOException
     */
    @FXML
    private void initialize() throws IOException {
        VBox top = Window.loadPane();
        pane.getChildren().add(top);

    }

    /**
     * functionality for Recommended button
     * When user clicks it, sets the TextFields (q1...a3) to specified text
     */
    @FXML
    public void recClicked(){
        q1.setText("Q: Is my personal and payment information secure?");
        a1.setText("A: Yes, we take the security of our customers' information seriously. Our website uses SSL encryption to protect your personal and payment information. We also comply with industry standards for data privacy and security.");
        q2.setText("Q: How can I place an order?");
        a2.setText("A: To place an order, simply browse the website for the items you want to purchase, add them to your shopping cart, and proceed to checkout. You will be prompted to provide your shipping and payment information before confirming your order.");
        q3.setText("Q: How long will it take to receive my order?");
        a3.setText("A: The delivery time depends on your location and the shipping option you choose. Typically, standard shipping takes 3-5 business days, while expedited shipping takes 1-2 business days. You can track your order status using the tracking number provided in the shipping confirmation email.");

    }

    /**
     * functionality for Shipping button
     * When user clicks it, sets the TextFields (q1...a3) to specified text
     */
    @FXML
    public void shipClicked(){
        q1.setText("Q: Can I change the shipping address for my order?");
        a1.setText("A: You can change the shipping address for your order before it has shipped by going to your order history and selecting \"Change Address.\" If your order has already shipped, please contact our customer service team");
        q2.setText("Q: What should I do if my package hasn't arrived yet?");
        a2.setText("A: If your package has not arrived by the estimated delivery date, please check the tracking information to see if there are any updates. If there are no updates or if you have any concerns about your delivery, please contact our customer service team for assistance.");
        q3.setText("Q: How long will it take to receive my order?");
        a3.setText("A: The delivery time depends on your location and the shipping option you choose. Typically, standard shipping takes 3-5 business days, while expedited shipping takes 1-2 business days. You can track your order status using the tracking number provided in the shipping confirmation email.");
    }


    /**
     * functionality for Returns + Refunds button
     * When user clicks it, sets the TextFields (q1...a3) to specified text
     */
    @FXML
    public void returnClicked(){
        q1.setText("Q: What is your return policy?");
        a1.setText("A: We offer a 30-day return policy on most items. If you are not satisfied with your purchase, simply contact us within 30 days of delivery to initiate the return process.");
        q2.setText("Q: Can I return an item that has been opened or used?");
        a2.setText("A: Yes, you can return most items even if they have been opened or used. However, certain items such as software, digital downloads, and personal care products may not be eligible for return if they have been opened or used due to health and safety concerns.");
        q3.setText("Q: How do I initiate a return?");
        a3.setText("A: The delivery time depends on your location and the shipping option you choose. Typically, standard shipping takes 3-5 business days, while expedited shipping takes 1-2 business days. You can track your order status using the tracking number provided in the shipping confirmation email.");
    }



    /**
     * functionality for Managing your Account button
     * When user clicks it, sets the TextFields (q1...a3) to specified text
     */
    @FXML
    public void accountClicked(){
        q1.setText("Q: How do I update my account information?");
        a1.setText("A: If you click on 'Account and Details' at the top of every page, you will be directed to 'Your Profile' page. Click on 'Edit Information' and from there, you can update your personal information, shipping and billing addresses, and payment methods. ");
        q2.setText("Q: Can I use the same email address for multiple athletX accounts?");
        a2.setText("A: No, each Amazon account requires a unique email address.");
        q3.setText("Q: What personal information do I need to provide?");
        a3.setText("A: When creating an Amazon account, you will need to provide your name, email address, phone number, and a password.");
    }


    /**
     * functionality for Payment + Pricing button
     * When user clicks it, sets the TextFields (q1...a3) to specified text
     */
    @FXML
    public void paymentClicked(){
        q1.setText("Q: Is my personal and payment information secure?");
        a1.setText("A: Yes, we take the security of our customers' information seriously. Our website uses SSL encryption to protect your personal and payment information. We also comply with industry standards for data privacy and security.");
        q2.setText("Q: What payment methods does athletX accept?");
        a2.setText("A: athletX accepts various payment methods, including credit and debit cards, athletX gift cards, PayPal, and bank transfers.");
        q3.setText("Q: Why was my payment declined on athletX?");
        a3.setText("A: Your payment may have been declined on athletX for several reasons, such as insufficient funds, an expired card, or a billing address mismatch. You may need to update your payment information or contact your bank for more information.");
    }


    /**
     * functionality for Miscellaneous button
     * When user clicks it, sets the TextFields (q1...a3) to specified text
     */
    @FXML
    public void miscClicked(){
        q1.setText("Q: How can I track my order on athletX?");
        a1.setText("A: You can track your order on athletX by logging into your account and checking the order status, or by clicking on the tracking number in the shipping confirmation email.");
        q2.setText("Q: Does athletX offer international shipping?");
        a2.setText("A: As of right now, athletX only offers  shipping in North America, but our team is working behind the scenes to make athletX be available in a myriad of countries in the near future!");
        q3.setText("Q: How can I leave feedback for a seller on athletX?");
        a3.setText("A: To leave feedback for a seller on athletX, log into your account, go to Your Orders, select the order, and click on Leave Seller Feedback.");
    }




}

