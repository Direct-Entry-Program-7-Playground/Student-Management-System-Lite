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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import lk.ijse.dep7.sms_lite.model.tm.ProviderTM;
import lk.ijse.dep7.sms_lite.model.tm.StudentTM;
import lk.ijse.dep7.sms_lite.util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
    private TableColumn<StudentTM, ListView<String>> colPhone;
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
    @FXML
    private ComboBox cmbProvider;

    public void initialize() {
        tableColumnAutoSize();

        // Allow multiple row selection
        /*tblStudent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstvwPhoneList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);*/

        colID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        colPhone.setCellValueFactory(param -> {
            ListView<String> list = new ListView<>();
            StudentTM student = param.getValue();
            list.setItems(FXCollections.observableArrayList(student.getContacts()));

            list.setPrefHeight(student.getContacts().size() * 44);
            return new ReadOnlyObjectWrapper<>(list);
        });

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
                        StudentTM student = param.getValue();
                        deleteStudent(event, student);
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

        Platform.runLater(() -> {
            init();

        });


    }

    private void init() {
        loadAllStudents();
        loadAllProviders();
    }

    private void loadAllStudents() {
        tblStudent.getItems().clear();

        try {
            PreparedStatement PSTM_GET_ALL_STUDENTS_DATA_QUERY = connection.prepareStatement("SELECT s.id AS student_id, s.name AS student_name,c.contact AS contact_no, p.name AS provider_name FROM SMSLite.student s LEFT JOIN contact c ON s.id = c.student_id LEFT JOIN provider p ON p.id = c.provider_id");
            ResultSet rst = PSTM_GET_ALL_STUDENTS_DATA_QUERY.executeQuery();


            while (rst.next()) {
                int studentId = rst.getInt("student_id");
                String studentName = rst.getString("student_name");
                String contactNo = rst.getString("contact_no");

                List<String> contacts;
                if ((contacts = getStudentContacts(studentId)) == null) {
                    contacts = new ArrayList<>();

                    if (contactNo != null) {
                        contacts.add(contactNo);
                    }

                    tblStudent.getItems().add(new StudentTM(studentId, studentName, contacts));
                } else {
                    contacts.add(contactNo);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private List<String> getStudentContacts(int studentID) {
        for (StudentTM student : tblStudent.getItems()) {
            if (student.getStudentID() == studentID) return student.getContacts();
        }

        return null;
    }

    private void loadAllProviders() {
        cmbProvider.getItems().clear();

        try {
            PreparedStatement PSTM_GET_ALL_PROVIDERS_DATA_QUERY = connection.prepareStatement("SELECT * FROM provider;");
            ResultSet rst = PSTM_GET_ALL_PROVIDERS_DATA_QUERY.executeQuery();

            while (rst.next()) {
                int id = rst.getInt("id");
                String name = rst.getString("name");
                String operatorCode = rst.getString("operatorCode");

                List<String> operatorCodes;
                if ((operatorCodes = getProviderOperatorCodes(name)) == null) {
                    operatorCodes = new ArrayList<>();
                    if (operatorCode != null) {
                        operatorCodes.add(operatorCode);
                    }
                    cmbProvider.getItems().add(new ProviderTM(id, name, operatorCodes));
                } else {
                    operatorCodes.add(operatorCode);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private List<String> getProviderOperatorCodes(String providerName) {
        for (Object item : cmbProvider.getItems()) {
            ProviderTM provider = (ProviderTM) item;
            if (provider.getName().equals(providerName)) return provider.getOperatorCodes();
        }

        return null;
    }


    private void addStudentsToTableView(ResultSet rst) throws SQLException {

    }

    private void deleteStudent(ActionEvent actionEvent, StudentTM student) {

        try {
            connection.setAutoCommit(false);
            PreparedStatement PSTM_FIND_CONTACT_FROM_STUDENT_ID_QUERY = connection.prepareStatement("SELECT * FROM contact WHERE student_id =?;");
            PSTM_FIND_CONTACT_FROM_STUDENT_ID_QUERY.setInt(1, student.getStudentID());

            PreparedStatement PSTM_DELETE_CONTACTS_OF_STUDENT_ID = connection.prepareStatement("DELETE FROM contact WHERE student_id = ?;");
            PSTM_DELETE_CONTACTS_OF_STUDENT_ID.setInt(1, student.getStudentID());

            if (PSTM_FIND_CONTACT_FROM_STUDENT_ID_QUERY.executeQuery().next()) {

                int deletedRows = PSTM_DELETE_CONTACTS_OF_STUDENT_ID.executeUpdate();
                if (deletedRows == 0) {
                    throw new RuntimeException("Failed to delete contacts");
                }
            }

            PreparedStatement PSTM_DELETE_STUDENT = connection.prepareStatement("DELETE FROM student WHERE id =?;");
            PSTM_DELETE_STUDENT.setInt(1, student.getStudentID());
            if (PSTM_DELETE_STUDENT.executeUpdate() != 1) {
                throw new RuntimeException("Failed to delete the student");
            }

            connection.commit();
            StringBuilder sb = new StringBuilder();
            sb.append("#");
            sb.append(student.getStudentID());
            sb.append(" - ");
            sb.append(student.getName());
            sb.append(" has been deleted successfully");

            Alert alert = new Alert(Alert.AlertType.INFORMATION, sb.toString());
            alert.setWidth(sb.length());
            alert.show();
            tblStudent.getItems().remove(student);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("Failed to delete the student");
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

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
