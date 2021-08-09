/*
 *
 *  * Copyright (c) 2021 Manoj Randeni. All rights reserved.
 *  * Licensed under the Apache license. See License.txt in the project root for license information
 *
 */

package lk.ijse.dep7.sms_lite.view;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SMSLite", "root", "7251mMm7251");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }));
        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to connect with the Database").showAndWait();
            e.printStackTrace();
            System.exit(1);
        }


    }

    public DBConnection getInstance() {
        return (instance == null) ? instance = new DBConnection() : instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
