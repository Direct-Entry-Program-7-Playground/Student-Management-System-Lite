package lk.ijse.dep7.sms_lite.controller;

import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class StudentManagementFormController {
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPhone;
    @FXML
    private ListView lstvwPhoneList;
    @FXML
    private Button btnAddPhone;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colPhone;
    @FXML
    private TableColumn colOption;
    @FXML
    private TableView tblStudent;

    public void initialize() {
        tableColumnAutoSize();

        Platform.runLater(()->{

        });
    }

    private void init(){
        btnClear.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        btnAddPhone.setDisable(true);

        txtID.requestFocus();

        // add some dummy data
    }

    @FXML
    private void btnAddPhone_onAction(ActionEvent actionEvent) {
    }

    @FXML
    private void btnClear_onAction(ActionEvent actionEvent) {
    }

    @FXML
    private void btnSave_onAction(ActionEvent actionEvent) {
    }

    @FXML
    private void btnDelete_onAction(ActionEvent actionEvent) {
    }

    private void tableColumnAutoSize() {
        double tableWidth = colID.widthProperty().get();
        tableWidth += colOption.widthProperty().get();
        tableWidth += colPhone.widthProperty().get();

        DoubleBinding autoWidth = tblStudent.widthProperty().subtract(tableWidth);
        colName.prefWidthProperty().bind(autoWidth);
        System.out.println(tableWidth);
    }
}
