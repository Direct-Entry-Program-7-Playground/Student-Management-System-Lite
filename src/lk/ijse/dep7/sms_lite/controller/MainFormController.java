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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lk.ijse.dep7.sms_lite.AppInitializer;

import java.io.IOException;

public class MainFormController {
    @FXML
    private Button btnSMS;
    @FXML
    private Button btnProviders;

    public static void navigation(NavigationMenu navigationMenu) throws IOException {

        String url = "";
        String title = "";

        switch (navigationMenu) {

            case STUDENTFORM: {
                url = "../view/StudentManagementForm.fxml";
                title = "Student Management System";
                break;
            }
            case PROVIDERFORM: {
                url = "../view/ProviderForm.fxml";
                title = "Service Providers";
                break;
            }
            case MAINFORM: {
                url = "../view/MainForm.fxml";
                title = "Student Management System - Lite";
                break;
            }

        }

        Parent root = FXMLLoader.load(MainFormController.class.getResource(url));
        Scene scene = new Scene(root);

        Stage stage = AppInitializer.getPrimaryStage();
        stage.setScene(scene);

        String finalTitle = title;
        Platform.runLater(() -> {
            stage.setTitle(finalTitle);
            stage.sizeToScene();
            stage.centerOnScreen();
        });
    }

    @FXML
    private void btnSMS_onAction(ActionEvent event) throws IOException {
        navigation(NavigationMenu.STUDENTFORM);

    }

    @FXML
    private void btnProviders_onAction(ActionEvent event) throws IOException {
        navigation(NavigationMenu.PROVIDERFORM);
    }

    public enum NavigationMenu {
        STUDENTFORM, PROVIDERFORM, MAINFORM
    }
}
