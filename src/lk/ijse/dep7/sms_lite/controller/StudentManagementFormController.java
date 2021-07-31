package lk.ijse.dep7.sms_lite.controller;

import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import lk.ijse.dep7.sms_lite.model.tm.StudentTM;


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

    public void initialize() {
        tableColumnAutoSize();

        // Allow multiple row selection
        tblStudent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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
                        System.out.println(tblStudent.getItems().remove(param.getValue()));
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
                    txtID.setText(selectedStudent.getStudentID());
                    txtName.setText(selectedStudent.getName());
                    lstvwPhoneList.getItems().addAll(selectedStudent.getPhoneNumbers().split(", "));

                } else {
                    txtID.setDisable(false);
                }
            });

            txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            });


            // Add some dummy data
            tblStudent.getItems().add(new StudentTM("SID0001", "Nuwan Kulasekara", new String[]{"077-460 2589"}));
            tblStudent.getItems().add(new StudentTM("SID0002", "Bhanuka Rajapaksh", new String[]{"071-258 2589","075-346 7896"}));
        });
    }

    private void init() {
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
        tableWidth += colAction.widthProperty().get();
        tableWidth += colPhone.widthProperty().get();

        DoubleBinding autoWidth = tblStudent.widthProperty().subtract(tableWidth).subtract(5);
        colName.prefWidthProperty().bind(autoWidth);
    }
}
