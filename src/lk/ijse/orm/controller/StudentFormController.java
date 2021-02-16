package lk.ijse.orm.controller;

import animatefx.animation.FadeInRight;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.orm.bo.BOFactory;
import lk.ijse.orm.bo.custom.RegistrationBO;
import lk.ijse.orm.dto.RegistrationDTO;
import lk.ijse.orm.tm.StudentTM;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class StudentFormController {
    public AnchorPane window;
    public AnchorPane paneSearch;
    public TextField txtSearchStudent;
    public JFXButton btnNew;
    public TableView<StudentTM> tblStudent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDob;
    public TableColumn colGender;
    public AnchorPane paneForm;
    public TextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXDatePicker dpDob;
    public JFXComboBox<String> cmbGender;
    public AnchorPane paneButtons;
    public JFXButton btnExit;
    public JFXButton btnNext;

    private RegistrationBO bo = BOFactory.getInstance().getBO(BOFactory.BOType.REGISTRATION);

    public void initialize() {
        setTableCols();
        setDefault();
        loadTable();
        txtSearchStudent.requestFocus();

        txtSearchStudent.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ArrayList<RegistrationDTO> allStudents = bo.getSearchStudentDetails(newValue);
                ObservableList<StudentTM> list = FXCollections.observableArrayList();

                for (RegistrationDTO dto : allStudents) {
                    list.add(new StudentTM(
                            dto.getStudentId(),
                            dto.getName(),
                            dto.getAddress(),
                            dto.getContact(),
                            String.valueOf(dto.getDob()),
                            dto.getGender()
                    ));
                }
                tblStudent.setItems(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDefault();
            if (null != newValue)
                loadStudentDataToForm(newValue.getStudentId());
            paneForm.setDisable(false);
            btnNext.setDisable(false);
            btnNext.setText("Update & Next");
        });
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        try {
            paneForm.setDisable(false);
            btnNext.setDisable(false);
            btnNext.setText("Save & Next");
            txtStudentId.setText(bo.getLastStudentId());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//            e.printStackTrace();
        }
    }

    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
        loadForm("MainForm");
    }

    public void btnNextOnAction(ActionEvent actionEvent) {
        try {
            boolean isValidated = isValidated();
            if (isValidated) {
                RegistrationDTO student = getStudent();
                if (btnNext.getText().equals("Save & Next")) {
//                    boolean isSaved = bo.saveStudent(student);
                    bo.setStudent(student);
                    bo.setStatus("Save");
                    loadNextForm();
                } else if (btnNext.getText().equals("Update & Next")) {
//                    boolean isUpdated = bo.updateStudent(student);
                    bo.setStudent(student);
                    bo.setStatus("Update");
                    loadNextForm();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Submission Failed...").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//            e.printStackTrace();
        }
    }

    private RegistrationDTO getStudent() {
        String date = dpDob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        java.sql.Date dbo = java.sql.Date.valueOf(date);
        return new RegistrationDTO(
                txtStudentId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                dbo,
                cmbGender.getValue()
        );
    }

    private boolean isValidated() {
        if (Pattern.compile("^[A-z ]{2,}$").matcher(txtName.getText()).matches()) {
            if (Pattern.compile("^[A-z0-9 ,.]{2,}$").matcher(txtAddress.getText()).matches()) {
                if (Pattern.compile("^0[0-9]{9}$").matcher(txtContact.getText()).matches()) {
                    if (dpDob.getValue() != null) {
                        if (cmbGender.getValue() != null) {
                            return true;
                        } else {
                            txtName.setFocusColor(Paint.valueOf("red"));
                            txtName.requestFocus();
                        }
                    } else {
                        txtName.setFocusColor(Paint.valueOf("red"));
                        txtName.requestFocus();
                    }
                } else {
                    txtName.setFocusColor(Paint.valueOf("red"));
                    txtName.requestFocus();
                }
            } else {
                txtName.setFocusColor(Paint.valueOf("red"));
                txtName.requestFocus();
            }
        } else {
            txtName.setFocusColor(Paint.valueOf("red"));
            txtName.requestFocus();
        }
        return false;
    }

    private void loadGendersToCmb() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Male");
        list.add("Female");
        cmbGender.setItems(list);
    }

    private void setTableCols() {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void loadStudentDataToForm(String id) {
        try {
            RegistrationDTO student = bo.searchStudent(id);
            txtStudentId.setText(student.getStudentId());
            txtName.setText(student.getName());
            txtAddress.setText(student.getAddress());
            txtContact.setText(student.getContact());
            String dob = String.valueOf(student.getDob());
            dpDob.setValue(LocalDate.parse(dob));
            cmbGender.setValue(student.getGender());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//            e.printStackTrace();
        }
    }

    private void loadTable() {
        try {
            ArrayList<RegistrationDTO> allStudents = bo.getAllStudents();
            ObservableList<StudentTM> list = FXCollections.observableArrayList();

            for (RegistrationDTO dto : allStudents) {
                list.add(new StudentTM(
                        dto.getStudentId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getContact(),
                        String.valueOf(dto.getDob()),
                        dto.getGender()
                ));
            }

            tblStudent.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//            e.printStackTrace();
        }
    }

    private void setDefault() {
        txtSearchStudent.clear();
        txtStudentId.setText("00");
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        dpDob.setValue(LocalDate.now());
        loadGendersToCmb();
        paneForm.setDisable(true);
        btnNext.setDisable(true);
        btnNext.setText("Next");

        txtName.setFocusColor(Paint.valueOf("black"));
        txtAddress.setFocusColor(Paint.valueOf("black"));
        txtContact.setFocusColor(Paint.valueOf("black"));
        dpDob.setDefaultColor(Paint.valueOf("#2f2fff"));
        cmbGender.setFocusColor(Paint.valueOf("black"));
    }

    private void loadForm(String formName) throws IOException {
        /*Parent root = FXMLLoader.load(this.getClass().getResource("../view/"+formName+".fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        new FadeIn(root).setSpeed(0.7).play();
        stage.show();

        Stage primaryStage = (Stage) btnExit.getScene().getWindow();
        primaryStage.close();*/

        Stage window = (Stage) this.window.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/" + formName + ".fxml"))));
    }

    private void loadNextForm() throws IOException {
            AnchorPane pane = FXMLLoader.load(this.getClass().getResource("../view/RegistrationForm.fxml"));
            window.getChildren().clear();
            window.getChildren().add(pane);
            new FadeInRight(pane).play();
    }
}
