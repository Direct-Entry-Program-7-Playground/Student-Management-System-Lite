/*
 *
 *  * Copyright (c) 2021 Manoj Randeni. All rights reserved.
 *  * Licensed under the Apache license. See License.txt in the project root for license information
 *
 */

package lk.ijse.dep7.sms_lite.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProviderFormController {
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCode;
    @FXML
    private ListView lstvwCodeList;
    @FXML
    private Button btnAddCode;
    @FXML
    private Button btnDeleteCode;
    @FXML
    private Button btnClearCodeList;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView tblStudent;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colPhone;
    @FXML
    private TableColumn colAction;

    public void initialize() {

        Platform.runLater(() -> {

        });
    }

    @FXML
    private void btnAddCode_onAction(ActionEvent event) {
    }

    @FXML
    private void btnDeleteCode_onAction(ActionEvent event) {
    }

    @FXML
    private void btnClearCodeList_onAction(ActionEvent event) {
    }

    @FXML
    private void btnClear_onAction(ActionEvent event) {
    }

    @FXML
    private void btnSave_onAction(ActionEvent event) {
    }

    @FXML
    private void btnDelete_onAction(ActionEvent event) {
    }
}
