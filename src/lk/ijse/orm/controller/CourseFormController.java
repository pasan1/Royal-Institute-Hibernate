package lk.ijse.orm.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.orm.bo.custom.CourseBO;
import lk.ijse.orm.dto.CourseDTO;
import lk.ijse.orm.dto.RegistrationDTO;
import lk.ijse.orm.tm.CourseTM;
import lk.ijse.orm.tm.StudentTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CourseFormController {
    public AnchorPane window;
    public AnchorPane paneSearch;
    public TextField txtSearchCourse;
    public JFXButton btnNew;
    public TableView<CourseTM> tblCourse;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colType;
    public TableColumn colDuration;
    public AnchorPane paneForm;
    public TextField txtCourseCode;
    public JFXTextField txtName;
    public JFXTextField txtType;
    public JFXTextField txtDuration;
    public AnchorPane paneButtons;
    public JFXButton btnExit;
    public JFXButton btnSave;

    private CourseBO bo = BOFactory.getInstance().getBO(BOFactory.BOType.COURSE);

    public void initialize() {
        setTableCols();
        setDefault();
        loadTable();

        txtSearchCourse.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ArrayList<CourseDTO> allStudents = bo.getSearchCourseDetails(newValue);
                ObservableList<CourseTM> list = FXCollections.observableArrayList();

                for (CourseDTO dto : allStudents) {
                    list.add(new CourseTM(
                            dto.getCode(),
                            dto.getCourseName(),
                            dto.getCourseType(),
                            dto.getDuration()
                    ));
                }
                tblCourse.setItems(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tblCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDefault();
            if (null != newValue)
                loadStudentDataToForm(newValue.getCode());
            paneForm.setDisable(false);
            btnSave.setDisable(false);
            btnSave.setText("Update");
        });
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        try {
            paneForm.setDisable(false);
            btnSave.setDisable(false);
            btnSave.setText("Save");
            txtCourseCode.setText(bo.getLastCourseId());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//            e.printStackTrace();
        }
    }

    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
        loadForm("MainForm");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
            if (isValidated()) {
                if (btnSave.getText().equals("Save")) {
                    if (bo.saveCourse(getCourse())) {
                        new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                        setDefault();
                        loadTable();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                    }
                } else if (btnSave.getText().equals("Update")) {
                    if (bo.updateCourse(getCourse())) {
                        new Alert(Alert.AlertType.INFORMATION, "SuccessFully Updated").show();
                        setDefault();
                        loadTable();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Updating Failed").show();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "Submission Failed...").show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CourseDTO getCourse() {
        return new CourseDTO(
                txtCourseCode.getText(),
                txtName.getText(),
                txtType.getText(),
                txtDuration.getText()
        );
    }

    private boolean isValidated() {
        if (Pattern.compile("^[A-z ]{2,}$").matcher(txtName.getText()).matches()) {
            if (Pattern.compile("^[A-z ]{2,}$").matcher(txtType.getText()).matches()) {
                if (Pattern.compile("^[A-z0-9 ]+$").matcher(txtDuration.getText()).matches()) {
                    return true;
                } else {
                    txtDuration.setFocusColor(Paint.valueOf("red"));
                    txtDuration.requestFocus();
                }
            } else {
                txtType.setFocusColor(Paint.valueOf("red"));
                txtType.requestFocus();
            }
        } else {
            txtName.setFocusColor(Paint.valueOf("red"));
            txtName.requestFocus();
        }
        return false;
    }

    private void setTableCols() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }

    private void loadForm(String formName) throws IOException {
        Stage window = (Stage) this.window.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/" + formName + ".fxml"))));
    }

    private void loadStudentDataToForm(String code) {
        try {
            CourseDTO course = bo.searchCourse(code);
            txtCourseCode.setText(course.getCode());
            txtName.setText(course.getCourseName());
            txtType.setText(course.getCourseType());
            txtDuration.setText(course.getDuration());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//            e.printStackTrace();
        }
    }

    private void loadTable() {
        try {
            ArrayList<CourseDTO> allCourse = bo.getAllCourse();
            ObservableList<CourseTM> list = FXCollections.observableArrayList();

            for (CourseDTO dto : allCourse) {
                list.add(new CourseTM(
                        dto.getCode(),
                        dto.getCourseName(),
                        dto.getCourseType(),
                        dto.getDuration()
                ));
            }
            tblCourse.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//            e.printStackTrace();
        }
    }

    private void setDefault() {
        txtSearchCourse.clear();
        txtCourseCode.setText("00");
        txtName.clear();
        txtType.clear();
        txtDuration.clear();
        btnSave.setText("Save");
        btnSave.setDisable(true);
        paneForm.setDisable(true);

        txtName.setFocusColor(Paint.valueOf("black"));
        txtType.setFocusColor(Paint.valueOf("black"));
        txtDuration.setFocusColor(Paint.valueOf("black"));
    }
}
