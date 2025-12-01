module com.battlearena.battlearenagame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.battlearena.battlearenagame to javafx.fxml;
    exports com.battlearena.battlearenagame;
}