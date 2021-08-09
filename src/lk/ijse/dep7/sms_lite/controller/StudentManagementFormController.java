/*
 *
 *  * Copyright (c) 2021 Manoj Randeni. All rights reserved.
 *  * Licensed under the Apache license. See License.txt in the project root for license information
 *
 */

package lk.ijse.dep7.sms_lite.controller;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import lk.ijse.dep7.sms_lite.model.tm.StudentTM;
import lk.ijse.dep7.sms_lite.util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentManagementFormController {
    BooleanProperty idAndNameValid = new SimpleBooleanProperty(false);
    BooleanProperty hasPhoneNumber = new SimpleBooleanProperty(false);
    Connection connection;
    PreparedStatement FIND_STUDENT_QUERY;

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
    private TableView<StudentTM> tblStudent;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colPhone;
    @FXML
    private TableColumn<StudentTM, Button> colAction;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnDeletePhoneNumber;
    @FXML
    private Button btnClearPhoneList;
    @FXML
    private Button btnHome;

    public void initialize() {
        tableColumnAutoSize();

        // Allow multiple row selection
        tblStudent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstvwPhoneList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        colID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumbers"));
        colAction.setCellValueFactory(param -> {
            ImageView img = new ImageView("/lk/ijse/dep7/sms_lite/asset/image/delete.png");
            img.setFitHeight(20);
            img.setFitWidth(20);
            Button btnRowDelete = new Button();
            btnRowDelete.setId("rowDelete");
            btnRowDelete.setPrefWidth(30);
            btnRowDelete.setGraphic(img);

            btnRowDelete.setOnAction(
                    (event) -> {
                        deleteStudent(event, param.getValue());
                        tblStudent.getItems().remove(param.getValue());
                    }
            );
            return new ReadOnlyObjectWrapper<>(btnRowDelete);

        });

        // Define prepareStatements
        try {
            connection = DBConnection.getInstance().getConnection();
            FIND_STUDENT_QUERY = connection.prepareStatement("SELECT * FROM student INNER JOIN contact ON student.id = contact.student_id WHERE student_id = ?;");
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        init();

        Platform.runLater(() -> {

        });


    }

    private void init() {

    }

    private void addStudentsToTableView(ResultSet rst) throws SQLException {

    }

    private void deleteStudent(ActionEvent actionEvent, StudentTM studentTM) {

    }

    private void tableColumnAutoSize() {

    }

    @FXML
    private void btnAddPhone_onAction(ActionEvent actionEvent) {

    }


    @FXML
    private void btnDeletePhoneNumber_onAction(ActionEvent event) {

    }

    @FXML
    private void btnClearPhoneList_onAction(ActionEvent event) {


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


    @FXML
    private void btnHome_onAction(ActionEvent event) throws IOException {
        MainFormController.navigation(MainFormController.NavigationMenu.MAINFORM);
    }
}
