module com.example.indicator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.indicator to javafx.fxml;
    exports com.example.indicator;
    exports com.example.indicator.ui;
    opens com.example.indicator.ui to javafx.fxml;
}