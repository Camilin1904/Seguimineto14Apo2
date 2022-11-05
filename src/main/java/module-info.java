module bank.payment_record {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens bank.payment_record to javafx.fxml;
    exports bank.payment_record;
}
