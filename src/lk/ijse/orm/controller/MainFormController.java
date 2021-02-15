package lk.ijse.orm.controller;

import animatefx.animation.FadeInRight;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainFormController {
    public AnchorPane window;
    public AnchorPane paneUser;
    public AnchorPane paneMenu;
    public AnchorPane paneDisplay;
    public AnchorPane paneTitle;
    public JFXButton btnHome;
    public JFXButton btnRegistrations;
    public JFXButton btnCourses;
    public JFXButton btnReports;
    public Text lblTitle;

    static String title;

    public void initialize() throws IOException {
        title = "Welcome to Royal Institute | Register Management System";
        btnHome.requestFocus();
        loadDefaultForm();
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        loadDefaultForm();
    }

    public void btnRegistrationsOnAction(ActionEvent actionEvent) throws IOException {
        lblTitle.setText("Registration Form | Register Management System");
        loadUI("StudentForm");
    }

    public void btnCoursesOnAction(ActionEvent actionEvent) throws IOException {
        lblTitle.setText("Course Form | Register Management System");
        loadUI("CourseForm");
    }

    public void btnReportsOnAction(ActionEvent actionEvent) {
    }

    public void loadDefaultForm() throws IOException {
        lblTitle.setText(title);
        loadUI("DefaultForm");
    }

    private void loadUI(String formName) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("../view/" + formName + ".fxml"));
        paneDisplay.getChildren().clear();
        paneDisplay.getChildren().add(pane);
        new FadeInRight(pane).play();
    }
}
