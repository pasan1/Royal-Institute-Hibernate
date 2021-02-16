package lk.ijse.orm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.orm.bo.BOFactory;
import lk.ijse.orm.bo.custom.LoginBO;

import java.io.IOException;

public class ChangePasswordFormController {
    public AnchorPane window;
    public TextField txtOldPassword;
    public TextField txtNewPassword;
    public TextField txtReNewPassword;
    public AnchorPane paneButtons;
    public JFXButton btnExit;
    public JFXButton btnUpdate;

    private LoginBO bo = BOFactory.getInstance().getBO(BOFactory.BOType.LOGIN);

    public void initialize() {
        setDefaults();
    }

    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
        loadMainForm();
    }

    private void loadMainForm() throws IOException {
        Stage window = (Stage) this.window.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        if (null != validateOldPass()) {
            if (null != getNewPass()) {
                changePassword(getNewPass());
                new Alert(Alert.AlertType.INFORMATION, "Password changed!!!").show();
                loadMainForm();
            }
        }
    }

    private void changePassword(String newPass) throws Exception {
        bo.setPassToFile(newPass);
    }

    private String validateOldPass() throws Exception {
        if (txtOldPassword.getText().equals(bo.getPassFromFile())) {
            return txtOldPassword.getText();
        } else {
            new Alert(Alert.AlertType.WARNING, "Old Password is NOT matched!!!").show();
            txtOldPassword.setStyle("-fx-border-color: red");
            txtOldPassword.requestFocus();
            return null;
        }
    }

    private String getNewPass() {
        if (txtNewPassword.getText().equals(txtReNewPassword.getText())) {
            return txtReNewPassword.getText();
        } else {
            new Alert(Alert.AlertType.WARNING, "New Passwords are NOT matched!!!").show();
            txtNewPassword.setStyle("-fx-border-color: red");
            txtReNewPassword.setStyle("-fx-border-color: red");
            txtNewPassword.requestFocus();
            return null;
        }
    }

    private void setDefaults() {
        txtOldPassword.setStyle("-fx-border-color: black");
        txtNewPassword.setStyle("-fx-border-color: black");
        txtReNewPassword.setStyle("-fx-border-color: black");
    }
}
