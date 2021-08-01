package lk.ijse.dep7.sms_lite.controller;

import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import lk.ijse.dep7.sms_lite.model.tm.StudentTM;


public class StudentManagementFormController {
    BooleanProperty idAndNameValid = new SimpleBooleanProperty(false);
    BooleanProperty hasPhoneNumber = new SimpleBooleanProperty(false);
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
            btnRowDelete.setPrefWidth(30);
            btnRowDelete.setGraphic(img);

            btnRowDelete.setOnAction(
                    (event) -> {
                        tblStudent.getItems().remove(param.getValue());
                    }
            );
            return new ReadOnlyObjectWrapper<>(btnRowDelete);

        });

        init();

        Platform.runLater(() -> {

            // Set listeners
            tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedStudent) -> {
                txtID.clear();
                txtName.clear();
                txtPhone.clear();
                lstvwPhoneList.getItems().clear();

                if (selectedStudent != null) {
                    txtID.setDisable(true);
                    btnSave.setDisable(false);
                    btnSave.setText("Update");
                    btnDelete.setDisable(false);

                    txtID.setText(selectedStudent.getStudentID());
                    txtName.setText(selectedStudent.getName());
                    lstvwPhoneList.getItems().addAll(selectedStudent.getPhoneNumbers().split(", "));
                    btnClearPhoneList.setDisable(false);
                } else {
                    txtID.setDisable(false);
                    btnSave.setDisable(true);
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    btnClearPhoneList.setDisable(true);

                }
            });

            ChangeListener listener = (ChangeListener<String>) (observable, oldValue, newValue) -> {

                if (btnSave.getText().equals("Save")) {
                    String id = txtID.getText();
                    String name = txtName.getText();

                    if (id.matches("SID\\d{4}") && name.matches("[A-Za-z ]{3,}")) {
                        idAndNameValid.setValue(true);
                    } else {
                        idAndNameValid.setValue(false);
                    }

                    if (idAndNameValid.get() && hasPhoneNumber.get()) {
                        btnSave.setDisable(false);
                    } else {
                        btnSave.setDisable(true);
                    }
                }

            };

            txtID.textProperty().addListener(listener);
            txtName.textProperty().addListener(listener);

            lstvwPhoneList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedItem) -> {
                if (selectedItem != null) {
                    btnDeletePhoneNumber.setDisable(false);

                } else {
                    btnDeletePhoneNumber.setDisable(true);
                }
            });

            idAndNameValid.addListener((observable, oldValue, newValue) -> {
                if (idAndNameValid.get() && hasPhoneNumber.get()) {
                    btnSave.setDisable(false);
                } else {
                    btnSave.setDisable(true);
                }
            });
            hasPhoneNumber.addListener((observable, oldValue, newValue) -> {
                if (idAndNameValid.get() && hasPhoneNumber.get()) {
                    btnSave.setDisable(false);
                } else {
                    btnSave.setDisable(true);
                }
            });


            txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            });

            txtPhone.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.matches("\\d{3}[- ]\\d{3}[- ]\\d{4}")) {
                    btnAddPhone.setDisable(false);
                } else {
                    btnAddPhone.setDisable(true);
                }
            });

            // Add some dummy data
            tblStudent.getItems().add(new StudentTM("SID0001", "Nuwan Kulasekara", new String[]{"077-460 2589"}));
            tblStudent.getItems().add(new StudentTM("SID0002", "Bhanuka Rajapaksh", new String[]{"071-258 2589", "075-346 7896"}));
        });
    }

    private void init() {
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

        btnAddPhone.setDisable(true);
        btnClearPhoneList.setDisable(true);
        btnDeletePhoneNumber.setDisable(true);

        txtID.requestFocus();
    }

    @FXML
    private void btnAddPhone_onAction(ActionEvent actionEvent) {

        String phoneNumber = txtPhone.getText();
        lstvwPhoneList.getItems().add(phoneNumber);
        txtPhone.clear();
        txtPhone.requestFocus();

        btnClearPhoneList.setDisable(false);
        hasPhoneNumber.setValue(true);
    }


    @FXML
    private void btnDeletePhoneNumber_onAction(ActionEvent event) {
        lstvwPhoneList.getItems().removeAll(lstvwPhoneList.getSelectionModel().getSelectedItems());
        txtPhone.requestFocus();

        if (lstvwPhoneList.getItems().size() == 0) {
            hasPhoneNumber.setValue(false);
            btnClearPhoneList.setDisable(true);
        }
    }

    @FXML
    private void btnClearPhoneList_onAction(ActionEvent event) {
        lstvwPhoneList.getItems().clear();
        txtPhone.requestFocus();

        hasPhoneNumber.setValue(false);
        btnDeletePhoneNumber.setDisable(true);
        btnClearPhoneList.setDisable(true);

    }

    @FXML
    private void btnClear_onAction(ActionEvent actionEvent) {
        txtID.clear();
        txtName.clear();
        txtPhone.clear();
        txtSearch.clear();
        tblStudent.getSelectionModel().clearSelection();

        lstvwPhoneList.getItems().clear();
        btnClearPhoneList.setDisable(true);
        btnDeletePhoneNumber.setDisable(true);

        txtID.requestFocus();
    }


    @FXML
    private void btnSave_onAction(ActionEvent actionEvent) {
    }

    @FXML
    private void btnDelete_onAction(ActionEvent actionEvent) {

        tblStudent.getItems().removeAll(tblStudent.getSelectionModel().getSelectedItems());

    }

    private void tableColumnAutoSize() {
        double tableWidth = colID.widthProperty().get();
        tableWidth += colAction.widthProperty().get();
        tableWidth += colPhone.widthProperty().get();

        DoubleBinding autoWidth = tblStudent.widthProperty().subtract(tableWidth).subtract(5);
        colName.prefWidthProperty().bind(autoWidth);
    }

}
