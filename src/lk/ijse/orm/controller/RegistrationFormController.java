package lk.ijse.orm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.orm.bo.BOFactory;
import lk.ijse.orm.bo.custom.RegistrationBO;
import lk.ijse.orm.dto.RegistrationDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

public class RegistrationFormController {
    public AnchorPane window;
    public AnchorPane paneStudentForm;
    public TextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXDatePicker dpDob;
    public JFXComboBox<String> cmbGender;
    public JFXButton btnRegistrationId;
    public AnchorPane paneCourseForm;
    public JFXTextField txtCourseName;
    public JFXTextField txtCourseType;
    public JFXTextField txtCourseDuration;
    public JFXTextField txtRegistrationDate;
    public JFXTextField txtRegistrationFee;
    public JFXComboBox<String> cmbCourseCode;
    public AnchorPane paneButtons;
    public JFXButton btnBack;
    public JFXButton btnRegister;
    public JFXButton btnExit;

    private RegistrationBO bo = BOFactory.getInstance().getBO(BOFactory.BOType.REGISTRATION);

    public void initialize() {
        setDefault();
        txtRegistrationFee.requestFocus();
    }

    public void cmbCourseCodeOnAction(ActionEvent actionEvent) {
        try {
            RegistrationDTO course = bo.searchCourse(cmbCourseCode.getValue());
            txtCourseName.setText(course.getCourseName());
            txtCourseType.setText(course.getCourseName());
            txtCourseDuration.setText(course.getDuration());
            bo.setCourse(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        loadForm("MainForm");
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        try {
            boolean isValidated = isValidated();
            if (isValidated) {
                boolean isSaved = bo.saveRegistration(new RegistrationDTO(
                        btnRegistrationId.getText(),
                        txtRegistrationDate.getText(),
                        Double.valueOf(txtRegistrationFee.getText()),
                        txtStudentId.getText(),
                        cmbCourseCode.getValue()
                ));
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                    loadForm("MainForm");
                } else {
                    new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Submission Failed...").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValidated() {
        if (Pattern.compile("^[0-9]{3,}$").matcher(txtRegistrationFee.getText()).matches()) {
            return true;
        } else {
            txtRegistrationFee.setFocusColor(Paint.valueOf("red"));
            txtRegistrationFee.requestFocus();
        }
        return false;
    }

    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit from \bRegistration form\b?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("EXIT ???");
        Optional<ButtonType> buttonType = alert.showAndWait();
        ButtonType btnYes = new ButtonType("Yes");
        ButtonType btnNo = new ButtonType("No");
        if (buttonType.get() == ButtonType.YES) {
            loadForm("MainForm");
        } else if (buttonType.get() == ButtonType.NO) {
            alert.close();
        }
    }

    private void setDefault() {
        try {
            loadGendersToCmb();
            loadCourseCodesToCmb();
            btnRegistrationId.setText(bo.getLastRegistrationId());
            txtRegistrationDate.setText(String.valueOf(LocalDate.now()));
            loadStudentDetails();
            txtRegistrationFee.setFocusColor(Paint.valueOf("black"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStudentDetails() {
        RegistrationDTO student = bo.getStudent();
        txtStudentId.setText(student.getStudentId());
        txtName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtContact.setText(student.getContact());
        String dob = String.valueOf(student.getDob());
        dpDob.setValue(LocalDate.parse(dob));
        cmbGender.setValue(student.getGender());
    }

    private void loadForm(String formName) throws IOException {
        Stage window = (Stage) this.window.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/" + formName + ".fxml"))));
    }

    private void loadGendersToCmb() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Male");
        list.add("Female");
        cmbGender.setItems(list);
    }

    private void loadCourseCodesToCmb() {
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            ArrayList<String> codes = bo.getAllCourseCodes();
            for (String s : codes) {
                list.add(s);
            }
            cmbCourseCode.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
