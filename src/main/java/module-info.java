module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.project to javafx.fxml;
    exports com.example.project;

    opens com.example.project.view to javafx.fxml;
    exports com.example.project.view;
}