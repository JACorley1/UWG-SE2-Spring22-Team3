module edu.westga.edu {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
	requires transitive javafx.graphics;
	requires javafx.base;
	requires jeromq;
	requires json;
    opens edu.westga.edu.employee_management to javafx.fxml;
	opens edu.westga.edu.employee_management.controller to javafx.fxml;

    exports edu.westga.edu.employee_management;
}