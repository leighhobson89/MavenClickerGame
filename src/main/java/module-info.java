module com.fsclicker {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires transitive java.desktop;

    opens com.fsclicker to javafx.fxml;
    exports com.fsclicker;
}
