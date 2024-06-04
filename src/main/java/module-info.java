module com.projects.hospitalmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;

    opens com.projects.hospitalmanagementsystem to javafx.fxml;
    exports com.projects.hospitalmanagementsystem;
}