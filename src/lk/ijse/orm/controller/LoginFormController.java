package lk.ijse.orm.controller;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane root;
    public TextField txtUserName;
    public PasswordField txtPasswordMask;
    public JFXButton btnLogin;
    public JFXButton btnCancel;
    public ImageView imgEye;
    public ImageView imgEyeCross;
    public TextField txtPassword;

    private static String userName = "admin";
    private static String password = "admin";

    public void initialize(){
        imgEye.setVisible(true);
        imgEyeCross.setVisible(false);
        txtPassword.setVisible(false);
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String pass = getPass();
        if (isValidated(pass)){
            loadMainForm();
        } else {
            new Alert(Alert.AlertType.WARNING, "User Name or Password incorrect!!!").show();
        }
    }

    private void loadMainForm() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        new FadeIn(root).setSpeed(0.7).play();
        stage.show();

        Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
        primaryStage.close();
    }

    private String getPass() {
        if (txtPasswordMask.isVisible())
            return txtPasswordMask.getText();
        else if (txtPassword.isVisible())
            return txtPassword.getText();
        else
            return null;
    }

    private boolean isValidated(String pass) {
        if (txtUserName.getText().equals(userName) && pass.equals(password)){
            return true;
        } else {
            return false;
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void imgEyeOnMouseClicked(MouseEvent mouseEvent) {
        imgEyeCross.setVisible(true);
        imgEye.setVisible(false);
        txtPassword.setText(txtPasswordMask.getText());
        txtPasswordMask.setVisible(false);
        txtPassword.setVisible(true);
    }

    public void imgEyeCrossOnMouseClicked(MouseEvent mouseEvent) {
        imgEye.setVisible(true);
        imgEyeCross.setVisible(false);
        txtPasswordMask.setText(txtPassword.getText());
        txtPasswordMask.setVisible(true);
        txtPassword.setVisible(false);
    }
}
