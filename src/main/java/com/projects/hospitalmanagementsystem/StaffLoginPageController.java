package com.projects.hospitalmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author WINDOWS
 */
public class StaffLoginPageController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_staffID;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_showPassword;

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private Button login_loginBtn;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField register_fullName;

    @FXML
    private TextField register_email;

    @FXML
    private TextField register_staffID;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_showPassword;

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private Button register_signupBtn;

    @FXML
    private Hyperlink register_loginHere;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @FXML
    void loginAccount() {

        if (login_staffID.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect Staff ID/Password");
        } else {

            String sql = "SELECT * FROM staff WHERE staff_id = ? AND password = ? AND delete_date IS NULL";
            connect = DatabaseConnection.connectDB();

            try {

                if (!login_showPassword.isVisible()) {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_showPassword.setText(login_password.getText());
                    }
                } else {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_password.setText(login_showPassword.getText());
                    }
                }

                // CHECK IF THE STATUS OF THE STAFF IS CONFIRMED
                String checkStatus = "SELECT status FROM staff WHERE staff_id = '"
                        + login_staffID.getText() + "' AND password = '"
                        + login_password.getText() + "' AND status = 'Confirm'";

                prepare = connect.prepareStatement(checkStatus);
                result = prepare.executeQuery();

                if (result.next()) {

                    alert.errorMessage("Need the confirmation of the Admin!");
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, login_staffID.getText());
                    prepare.setString(2, login_password.getText());

                    result = prepare.executeQuery();

                    if (result.next()) {

                        Data.staff_id = result.getString("staff_id");
                        Data.staff_name = result.getString("full_name");

                        alert.successMessage("Login Successfully!");

                        // LINK YOUR STAFF MAIN FORM
                        Parent root = FXMLLoader.load(getClass().getResource("StaffDashboard.fxml"));
                        Stage stage = new Stage();

                        stage.setTitle("Hospital Management System | Staff Main Form");
                        stage.setScene(new Scene(root));
                        stage.show();

                        // TO HIDE YOUR STAFF PAGE
                        login_loginBtn.getScene().getWindow().hide();

                    } else {
                        alert.errorMessage("Incorrect Staff ID/Password");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void loginShowPassword() {

        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_password.setVisible(false);
            login_showPassword.setVisible(true);
        } else {
            login_password.setText(login_showPassword.getText());
            login_password.setVisible(true);
            login_showPassword.setVisible(false);
        }

    }

    @FXML
    void registerAccount() {

        if (register_fullName.getText().isEmpty()
                || register_email.getText().isEmpty()
                || register_staffID.getText().isEmpty()
                || register_password.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {

            String checkStaffID = "SELECT * FROM staff WHERE staff_id = '"
                    + register_staffID.getText() + "'";

            connect = DatabaseConnection.connectDB();

            try {

                if (!register_showPassword.isVisible()) {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_showPassword.setText(register_password.getText());
                    }
                } else {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_password.setText(register_showPassword.getText());
                    }
                }

                prepare = connect.prepareStatement(checkStaffID);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(register_staffID.getText() + " is already taken");
                } else if (register_password.getText().length() < 8) {
                    alert.errorMessage("Invalid password, at least 8 characters needed");
                } else {

                    String insertData = "INSERT INTO staff (full_name, email, staff_id, password, date, status) "
                            + "VALUES(?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertData);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(1, register_fullName.getText());
                    prepare.setString(2, register_email.getText());
                    prepare.setString(3, register_staffID.getText());
                    prepare.setString(4, register_password.getText());
                    prepare.setString(5, String.valueOf(sqlDate));
                    prepare.setString(6, "Confirm");

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully!");

                    login_form.setVisible(true);
                    register_form.setVisible(false);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void registerShowPassword() {

        if (register_checkBox.isSelected()) {
            register_showPassword.setText(register_password.getText());
            register_showPassword.setVisible(true);
            register_password.setVisible(false);
        } else {
            register_password.setText(register_showPassword.getText());
            register_showPassword.setVisible(false);
            register_password.setVisible(true);
        }

    }

    public void registerStaffID() {
        if (register_staffID.getText().isEmpty()) { // Check if the TextField is empty
            String staffID = "SID-";
            int tempID = 0;
            String sql = "SELECT MAX(id) FROM staff";

            connect = DatabaseConnection.connectDB();

            try {

                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                if (result.next()) {
                    tempID = result.getInt("MAX(id)");
                }

                if (tempID == 0) {
                    tempID += 1;
                    staffID += tempID;
                } else {
                    staffID += (tempID + 1);
                }

                register_staffID.setText(staffID);
                register_staffID.setDisable(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void userList() {

        List<String> listU = new ArrayList<>();

        for (String data : Users.user) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }

    public void switchPage() {

        if (login_user.getSelectionModel().getSelectedItem() == "Admin Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("AdminLoginPage.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Doctor Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("DoctorLoginPage.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Patient Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("PatientLoginPage.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (login_user.getSelectionModel().getSelectedItem()=="Staff Portal")
        {
            try
            {
                Parent root=FXMLLoader.load(getClass().getResource("StaffLoginPage.fxml"));
                Stage stage=new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinHeight(580);
                stage.setMinWidth(340);

                stage.setScene(new Scene(root));
                stage.show();

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        login_user.getScene().getWindow().hide();

    }

    @FXML
    void switchForm(ActionEvent event) {

        if (event.getSource() == register_loginHere) {
            login_form.setVisible(true);
            register_form.setVisible(false);
        } else if (event.getSource() == login_registerHere) {
            login_form.setVisible(false);
            register_form.setVisible(true);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerStaffID();
        userList();
    }

}
