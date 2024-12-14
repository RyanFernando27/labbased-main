module org.example.labbased {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens org.example.labbased to javafx.fxml;
    exports org.example.labbased;
}