module com.pokedex {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.desktop;
    requires javafx.swing;


    opens com.pokedex to javafx.fxml;
    exports com.pokedex;
}