/*
 *
 *  * Copyright (c) 2021 Manoj Randeni. All rights reserved.
 *  * Licensed under the Apache license. See License.txt in the project root for license information
 *
 */

package lk.ijse.dep7.sms_lite.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    @FXML
    private Button btnSMS;
    @FXML
    private Button btnProviders;

    @FXML
    private void btnSMS_onAction(ActionEvent event) throws IOException {
        loadForm("../view/StudentManagementForm.fxml", "Student Management System");

    }

    @FXML
    private void btnProviders_onAction(ActionEvent event) throws IOException {
        loadForm("../view/ProviderForm.fxml", "Service Providers");
    }


    private void loadForm(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource(location));
        Scene scene = new Scene(root);

        Stage stage = (Stage) btnSMS.getScene().getWindow();

        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }
}
