module athletX.Main {
    requires java.desktop;
    requires com.google.gson;
    requires jdk.unsupported;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;


    opens athletX.gui to javafx.fxml;
    opens athletX to com.google.gson;
    exports athletX.gui to javafx.graphics, javafx.fxml;




}