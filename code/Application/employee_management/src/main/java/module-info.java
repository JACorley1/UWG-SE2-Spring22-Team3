module edu.westga.edu {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens edu.westga.edu.employee_management to javafx.fxml;
    exports edu.westga.edu.employee_management;
}