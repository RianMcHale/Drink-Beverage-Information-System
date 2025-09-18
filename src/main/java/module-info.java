module com.example.drinkbeverageinfosystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.drinkbeverageinfosystem to javafx.fxml;
    exports com.example.drinkbeverageinfosystem;
    exports com.example.drinkbeverageinfosystem.controller;
    opens com.example.drinkbeverageinfosystem.controller to javafx.fxml;
    exports com.example.drinkbeverageinfosystem.models;
    opens com.example.drinkbeverageinfosystem.models to javafx.fxml;
}
