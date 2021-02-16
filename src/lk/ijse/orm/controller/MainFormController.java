package lk.ijse.orm.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInRight;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
    public JFXButton btnLogout;
    public JFXButton btnChangePassword;

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

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to logout ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("LOGOUT ???");
        Optional<ButtonType> buttonType = alert.showAndWait();
        ButtonType btnYes = new ButtonType("Yes");
        ButtonType btnNo = new ButtonType("No");
        if (buttonType.get() == ButtonType.YES) {
            loadLogin();
        } else if (buttonType.get() == ButtonType.NO) {
            alert.close();
        }
    }

    private void loadLogin() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        new FadeIn(root).setSpeed(0.7).play();
        stage.show();

        Stage primaryStage = (Stage) btnLogout.getScene().getWindow();
        primaryStage.close();
    }

    public void btnChangePasswordOnAction(ActionEvent actionEvent) throws IOException {
        lblTitle.setText("Change Password Form | Register Management System");
        loadUI("ChangePasswordForm");
    }
}
