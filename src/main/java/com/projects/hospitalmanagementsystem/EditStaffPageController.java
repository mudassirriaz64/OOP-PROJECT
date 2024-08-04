package com.projects.hospitalmanagementsystem;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class EditStaffPageController implements Initializable {

    @FXML
    private TextField editStaff_staffID;

    @FXML
    private TextField editStaff_fullName;

    @FXML
    private TextField editStaff_email;

    @FXML
    private TextField editStaff_password;

    @FXML
    private ComboBox<String> editStaff_role;

    @FXML
    private ComboBox<String> editStaff_gender;

    @FXML
    private TextField editStaff_mobileNumber;

    @FXML
    private ImageView editStaff_imageView;

    @FXML
    private Button editStaff_importBtn;

    @FXML
    private TextArea editStaff_address;

    @FXML
    private ComboBox<String> editStaff_status;

    @FXML
    private Button editStaff_updateBtn;

    @FXML
    private Button editStaff_cancelBtn;

    private AlertMessage alert = new AlertMessage();
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void importBtn() {
        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*jpg", "*png", "*jpeg"));

        File file = open.showOpenDialog(editStaff_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 112, 121, false, true);
            editStaff_imageView.setImage(image);
        }
    }

    public void displayStaffData() {
        String sql = "SELECT * FROM staff WHERE staff_id = '" + editStaff_staffID.getText() + "'";
        connect = DatabaseConnection.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                editStaff_fullName.setText(result.getString("full_name"));
                editStaff_email.setText(result.getString("email"));
                editStaff_password.setText(result.getString("password"));
                editStaff_role.getSelectionModel().select(result.getString("role"));
                editStaff_gender.getSelectionModel().select(result.getString("gender"));
                editStaff_mobileNumber.setText(result.getString("mobile_number"));
                editStaff_address.setText(result.getString("address"));
                editStaff_status.getSelectionModel().select(result.getString("status"));

                image = new Image("File:" + result.getString("image"), 112, 121, false, true);
                editStaff_imageView.setImage(image);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBtn() {
        connect = DatabaseConnection.connectDB();

        if (editStaff_staffID.getText().isEmpty()
                || editStaff_fullName.getText().isEmpty()
                || editStaff_email.getText().isEmpty()
                || editStaff_password.getText().isEmpty()
                || editStaff_role.getSelectionModel().getSelectedItem() == null
                || editStaff_gender.getSelectionModel().getSelectedItem() == null
                || editStaff_mobileNumber.getText().isEmpty()
                || editStaff_address.getText().isEmpty()
                || editStaff_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            boolean isActive = "active".equalsIgnoreCase(editStaff_status.getSelectionModel().getSelectedItem().toString());

            if (Data.path == null || "".equals(Data.path)) {
                // Update data without changing image
                String updateData = "UPDATE staff SET full_name = ?, email = ?, password = ?, role = ?, gender = ?, mobile_number = ?, address = ?, status = ?, modify_date = ?, delete_date = " + (isActive ? "NULL" : "delete_date") + " WHERE staff_id = ?";
                try {
                    if (alert.confirmationMessage("Are you sure you want to Update Staff ID: " + editStaff_staffID.getText() + "?")) {
                        prepare = connect.prepareStatement(updateData);
                        prepare.setString(1, editStaff_fullName.getText());
                        prepare.setString(2, editStaff_email.getText());
                        prepare.setString(3, editStaff_password.getText());
                        prepare.setString(4, editStaff_role.getSelectionModel().getSelectedItem().toString());
                        prepare.setString(5, editStaff_gender.getSelectionModel().getSelectedItem().toString());
                        prepare.setString(6, editStaff_mobileNumber.getText());
                        prepare.setString(7, editStaff_address.getText());
                        prepare.setString(8, editStaff_status.getSelectionModel().getSelectedItem().toString());
                        prepare.setDate(9, sqlDate);
                        prepare.setString(10, editStaff_staffID.getText());
                        prepare.executeUpdate();
                        alert.successMessage("Staff ID: " + editStaff_staffID.getText() + " updated successfully.");
                    } else {
                        alert.errorMessage("Cancelled.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Update data with image change
                try {
                    if (alert.confirmationMessage("Are you sure you want to Update Staff ID: " + editStaff_staffID.getText() + "?")) {
                        String path = Data.path;
                        path = path.replace("\\", "\\\\");
                        Path transfer = Paths.get(path);

                        Path copy = Paths.get("C:\\Users\\user\\IdeaProjects\\OOP PROJECT\\Staff Pictures\\" + editStaff_staffID.getText() + ".jpg");

                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                        String insertImage = copy.toString();
                        insertImage = insertImage.replace("\\", "\\\\");

                        String updateData = "UPDATE staff SET full_name = ?, email = ?, password = ?, role = ?, gender = ?, mobile_number = ?, image = ?, address = ?, status = ?, modify_date = ?, delete_date = " + (isActive ? "NULL" : "delete_date") + " WHERE staff_id = ?";

                        prepare = connect.prepareStatement(updateData);
                        prepare.setString(1, editStaff_fullName.getText());
                        prepare.setString(2, editStaff_email.getText());
                        prepare.setString(3, editStaff_password.getText());
                        prepare.setString(4, editStaff_role.getSelectionModel().getSelectedItem().toString());
                        prepare.setString(5, editStaff_gender.getSelectionModel().getSelectedItem().toString());
                        prepare.setString(6, editStaff_mobileNumber.getText());
                        prepare.setString(7, insertImage);
                        prepare.setString(8, editStaff_address.getText());
                        prepare.setString(9, editStaff_status.getSelectionModel().getSelectedItem().toString());
                        prepare.setDate(10, sqlDate);
                        prepare.setString(11, editStaff_staffID.getText());
                        prepare.executeUpdate();
                        alert.successMessage("Staff ID: " + editStaff_staffID.getText() + " updated successfully.");
                    } else {
                        alert.errorMessage("Cancelled.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        displayStaffData();
    }


    public void cancelBtn(javafx.event.ActionEvent actionEvent)
    {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setField()
    {
        editStaff_staffID.setText(Data.temp_staffID);
        editStaff_fullName.setText(Data.temp_staffName);
        editStaff_email.setText(Data.temp_staffEmail);
        editStaff_password.setText(Data.temp_staffPassword);
        editStaff_role.getSelectionModel().select(Data.temp_staffRole);
        editStaff_gender.getSelectionModel().select(Data.temp_staffGender);
        editStaff_mobileNumber.setText(Data.temp_staffMobileNumber);
        editStaff_address.setText(Data.temp_staffAddress);
        editStaff_status.getSelectionModel().select(Data.temp_staffStatus);

        image = new Image("File:" + Data.temp_staffImagePath, 112, 121, false, true);
        editStaff_imageView.setImage(image);
    }

    public void roleList() {
        List<String> roleList = new ArrayList<>();

        for (String role : Data.roles) {
            roleList.add(role);
        }

        ObservableList<String> listData = FXCollections.observableList(roleList);
        editStaff_role.setItems(listData);
    }

    public void genderList() {
        List<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");

        ObservableList<String> listData = FXCollections.observableList(genderList);
        editStaff_gender.setItems(listData);
    }

    public void statusList() {
        List<String> statusList = new ArrayList<>();
        statusList.add("Active");
        statusList.add("Inactive");

        ObservableList<String> listData = FXCollections.observableList(statusList);
        editStaff_status.setItems(listData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setField();
        roleList();
        genderList();
        statusList();
    }
}

