package com.projects.hospitalmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HospitalManagementSystem extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("AdminLoginPage.fxml"));

        Scene scene = new Scene(root);

        stage.setMinWidth(340);
        stage.setMinHeight(580);

        stage.setTitle("Hospital Management System");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
