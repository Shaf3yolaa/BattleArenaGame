module com.battlearena.battlearenagame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.battlearena.battlearenagame to javafx.fxml;
    exports com.battlearena.battlearenagame;
}