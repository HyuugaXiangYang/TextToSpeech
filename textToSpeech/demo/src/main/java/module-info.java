module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires freetts;
    requires javafx.base;

    opens com.example to javafx.fxml;
    exports com.example;
}
