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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdminDashboardController implements Initializable {

    @FXML
    private AnchorPane duties_form;
    // GIVE NAME OF ALL COMPONENTS
    @FXML
    private AnchorPane main_form;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private Label date_time;

    @FXML
    private Label current_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button doctors_btn;

    @FXML
    private Button staff_btn;

    @FXML
    private Button patients_btn;

    @FXML
    private Button appointments_btn;

    @FXML
    private Button duties_btn;

    @FXML
    private Button payment_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_AD;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_TA;

    @FXML
    private AreaChart<?, ?> dashboard_chart_PD;

    @FXML
    private BarChart<?, ?> dashboard_chart_DD;

    @FXML
    private TableView<Doctor> dashboad_tableView;

    @FXML
    private TableColumn<Doctor, String> dashboad_col_doctorID;

    @FXML
    private TableColumn<Doctor, String> dashboad_col_name;

    @FXML
    private TableColumn<Doctor, String> dashboad_col_specialization;

    @FXML
    private TableColumn<Doctor, String> dashboad_col_status;

    @FXML
    private AnchorPane doctors_form;

    @FXML
    private TableView<Doctor> doctors_tableView;

    @FXML
    private TableColumn<Doctor, String> doctors_col_doctorID;

    @FXML
    private TableColumn<Doctor, String> doctors_col_name;

    @FXML
    private TableColumn<Doctor, String> doctors_col_gender;

    @FXML
    private TableColumn<Doctor, String> doctors_col_contactNumber;

    @FXML
    private TableColumn<Doctor, String> doctors_col_email;

    @FXML
    private TableColumn<Doctor, String> doctors_col_specialization;

    @FXML
    private TableColumn<Doctor, String> doctors_col_address;

    @FXML
    private TableColumn<Doctor, String> doctors_col_status;

    @FXML
    private TableColumn<Doctor, String> doctors_col_action;

    @FXML
    private AnchorPane staff_form;

    @FXML
    private TableView<Staff> staff_tableView;

    @FXML
    private TableColumn<Staff, String> staff_col_staffID;

    @FXML
    private TableColumn<Staff, String> staff_col_name;

    @FXML
    private TableColumn<Staff, String> staff_col_gender;

    @FXML
    private TableColumn<Staff, String> staff_col_contactNumber;

    @FXML
    private TableColumn<Staff, String> staff_col_email;

    @FXML
    private TableColumn<Staff, String> staff_col_role;

    @FXML
    private TableColumn<Staff, String> staff_col_address;

    @FXML
    private TableColumn<Staff, String> staff_col_status;

    @FXML
    private TableColumn<Staff, String> staff_col_action;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private TableView<Patient> patients_tableView;

    @FXML
    private TableColumn<Patient, String> patients_col_patientID;

    @FXML
    private TableColumn<Patient, String> patients_col_name;

    @FXML
    private TableColumn<Patient, String> patients_col_gender;

    @FXML
    private TableColumn<Patient, String> patients_col_contactNumber;

    @FXML
    private TableColumn<Patient, String> patients_col_description;

    @FXML
    private TableColumn<Patient, String> patients_col_date;

    @FXML
    private TableColumn<Patient, String> patients_col_dateModify;

    @FXML
    private TableColumn<Patient, String> patients_col_dateDelete;

    @FXML
    private TableColumn<Patient, String> patients_col_status;

    @FXML
    private TableColumn<Patient, String> patients_col_action;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private TableView<Appointment> appointments_tableView;

    @FXML
    private TableColumn<Appointment, String> appointments_appointmentID;

    @FXML
    private TableColumn<Appointment, String> appointments_name;

    @FXML
    private TableColumn<Appointment, String> appointments_gender;

    @FXML
    private TableColumn<Appointment, String> appointments_contactNumber;

    @FXML
    private TableColumn<Appointment, String> appointments_description;

    @FXML
    private TableColumn<Appointment, String> appointments_date;

    @FXML
    private TableColumn<Appointment, String> appointments_dateModify;

    @FXML
    private TableColumn<Appointment, String> appointments_dateDelete;

    @FXML
    private TableColumn<Appointment, String> appointments_status;

    @FXML
    private TableColumn<Appointment, String> appointments_action;


    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circle;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_adminIO;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_adminID;

    @FXML
    private TextField profile_username;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private AnchorPane payment_form;

    @FXML
    private TableView<Patient> payment_tableView;

    @FXML
    private TableColumn<Patient, String> payment_col_patientID;

    @FXML
    private TableColumn<Patient, String> payment_col_name;

    @FXML
    private TableColumn<Patient, String> payment_col_gender;

    @FXML
    private TableColumn<Patient, String> payment_col_diagnosis;

    @FXML
    private TableColumn<Patient, String> payment_col_doctor;

    @FXML
    private TableColumn<Patient, String> payment_col_date;

    @FXML
    private Circle payment_circle;

    @FXML
    private Button payment_checkoutBtn;

    @FXML
    private Label payment_patientID;

    @FXML
    private Label payment_name;

    @FXML
    private Label payment_doctor;

    @FXML
    private Label payment_date;

    @FXML
    private Button logout_btn;

    //    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    private Image image;

    private ObservableList<Staff> staffListData;

    public void dashboardAD() {

        String sql = "SELECT COUNT(id) FROM doctor WHERE status = 'Active' AND delete_date IS NULL";

        connect = DatabaseConnection.connectDB();

        int tempAD = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempAD = result.getInt("COUNT(id)");
            }
            dashboard_AD.setText(String.valueOf(tempAD));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTP() {

        String sql = "SELECT COUNT(id) FROM patient";

        connect = DatabaseConnection.connectDB();

        int tempTP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTP = result.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(tempTP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardAP() {

        String sql = "SELECT COUNT(id) FROM patient WHERE date_delete IS NULL AND status = 'Active'";

        connect = DatabaseConnection.connectDB();

        int tempAP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempAP = result.getInt("COUNT(id)");
            }
            dashboard_AP.setText(String.valueOf(tempAP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTA() {

        String sql = "SELECT COUNT(id) FROM appointment WHERE date_delete IS NULL";

        connect = DatabaseConnection.connectDB();

        int tempTA = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTA = result.getInt("COUNT(id)");
            }
            dashboard_TA.setText(String.valueOf(tempTA));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Doctor> dashboardGetDoctorData() {

        ObservableList<Doctor> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM doctor WHERE delete_date IS NULL";

        connect = DatabaseConnection.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Doctor dData;

            while (result.next()) {
                dData = new Doctor(result.getString("doctor_id"),
                        result.getString("full_name"), result.getString("specialization"),
                        result.getString("status"));

                listData.add(dData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<Doctor> dashboardGetDoctorListData;

    public void dashboardGetDoctorDisplayData() {
        dashboardGetDoctorListData = dashboardGetDoctorData();

        dashboad_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        dashboad_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        dashboad_col_specialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        dashboad_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboad_tableView.setItems(dashboardGetDoctorListData);

    }



    public void dashboardPatientDataChart() {
        dashboard_chart_PD.getData().clear();

        String selectData = "SELECT date, COUNT(id) FROM patient WHERE date_delete IS NULL GROUP BY date LIMIT 8";

        connect = DatabaseConnection.connectDB();
        XYChart.Series chart = new XYChart.Series<>();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_chart_PD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardDoctorDataChart() {
        dashboard_chart_DD.getData().clear();

        String selectData = "SELECT date, COUNT(id) FROM doctor WHERE delete_date IS NULL GROUP BY date LIMIT 8";

        connect = DatabaseConnection.connectDB();
        XYChart.Series chart = new XYChart.Series<>();
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_chart_DD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Doctor> doctorGetData() {
        ObservableList<Doctor> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM doctor";

        connect = DatabaseConnection.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Doctor dData;
            while (result.next()) {
                dData = new Doctor(result.getInt("id"), result.getString("doctor_id"),
                        result.getString("password"), result.getString("full_name"),
                        result.getString("email"), result.getString("gender"),
                        result.getLong("mobile_number"), result.getString("specialization"),
                        result.getString("address"), result.getString("image"),
                        result.getDate("date"), result.getDate("modify_date"),
                        result.getDate("delete_date"), result.getString("status"));

                listData.add(dData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Doctor> doctorListData;



    public void doctorDisplayData() {
        doctorListData = doctorGetData();

        doctors_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        doctors_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        doctors_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        doctors_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        doctors_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        doctors_col_specialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        doctors_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        doctors_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        doctors_tableView.setItems(doctorListData);

    }




    public ObservableList<Staff> staffGetData() {
        ObservableList<Staff> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM staff ";
        connect = DatabaseConnection.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                list.add(new Staff(
                        result.getInt("id"),
                        result.getString("staff_id"),
                        result.getString("password"),
                        result.getString("full_name"),
                        result.getString("email"),
                        result.getString("gender"),
                        result.getLong("mobile_number"),
                        result.getString("role"),
                        result.getString("address"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getDate("modify_date"),
                        result.getDate("delete_date"),
                        result.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }



    public void staffActionButton() {
        connect = DatabaseConnection.connectDB();
        ObservableList<Staff> staffListData = staffGetData();

        Callback<TableColumn<Staff, String>, TableCell<Staff, String>> cellFactory = (TableColumn<Staff, String> param) -> {
            final TableCell<Staff, String> cell = new TableCell<Staff, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                Staff sData = staff_tableView.getSelectionModel().getSelectedItem();
                                int num = staff_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_staffID = sData.getStaffID();
                                Data.temp_staffName = sData.getFullName();
                                Data.temp_staffEmail = sData.getEmail();
                                Data.temp_staffPassword = sData.getPassword();
                                Data.temp_staffRole = sData.getRole();
                                Data.temp_staffGender = sData.getGender();
                                Data.temp_staffMobileNumber = String.valueOf(sData.getMobileNumber());
                                Data.temp_staffAddress = sData.getAddress();
                                Data.temp_staffStatus = sData.getStatus();
                                Data.temp_staffImagePath = sData.getImage();

                                Parent root = FXMLLoader.load(getClass().getResource("EditStaffPage.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            Staff sData = getTableView().getItems().get(getIndex());
                            int num = getIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "UPDATE staff SET delete_date = ? , status = 'Inactive' WHERE staff_id = '"
                                    + sData.getStaffID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Staff ID: " + sData.getStaffID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    staffGetData();
                                    alert.successMessage("Deleted Successfully!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };

        staff_col_action.setCellFactory(cellFactory);
        staff_tableView.setItems(staffListData);
    }



    public void doctorActionButton() {

        connect = DatabaseConnection.connectDB();
        doctorListData = doctorGetData();

        Callback<TableColumn<Doctor, String>, TableCell<Doctor, String>> cellFactory = (TableColumn<Doctor, String> param) -> {
            final TableCell<Doctor, String> cell = new TableCell<Doctor, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                Doctor pData = doctors_tableView.getSelectionModel().getSelectedItem();
                                int num = doctors_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_doctorID = pData.getDoctorID();
                                Data.temp_doctorName = pData.getFullName();
                                Data.temp_doctorEmail = pData.getEmail();
                                Data.temp_doctorPassword = pData.getPassword();
                                Data.temp_doctorSpecialization = pData.getSpecialization();
                                Data.temp_doctorGender = pData.getGender();
                                Data.temp_doctorMobileNumber = String.valueOf(pData.getMobileNumber());
                                Data.temp_doctorAddress = pData.getAddress();
                                Data.temp_doctorStatus = pData.getStatus();
                                Data.temp_doctorImagePath = pData.getImage();

                                Parent root = FXMLLoader.load(getClass().getResource("EditDoctor.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            Doctor pData = doctors_tableView.getSelectionModel().getSelectedItem();
                            int num = doctors_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "UPDATE doctor SET delete_date = ? , status='Inactive' WHERE doctor_id = '"
                                    + pData.getDoctorID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Doctor ID: " + pData.getDoctorID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    doctorGetData();
                                    alert.successMessage("Deleted Successfully!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        doctors_col_action.setCellFactory(cellFactory);
        doctors_tableView.setItems(doctorListData);

    }

    public ObservableList<Patient> patientGetData() {

        ObservableList<Patient> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM patient";

        connect = DatabaseConnection.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Patient pData;

            while (result.next()) {
                pData = new Patient(result.getInt("id"), result.getInt("patient_id"),
                        result.getString("password"), result.getString("full_name"),
                        result.getLong("mobile_number"), result.getString("gender"),
                        result.getString("address"),
                        result.getString("image"), result.getString("description"),
                        result.getString("diagnosis"),
                        result.getString("treatment"), result.getString("doctor"),
                        result.getString("specialization"), result.getDate("date"),
                        result.getDate("date_modify"), result.getDate("date_delete"),
                        result.getString("status"));

                listData.add(pData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<Patient> patientListData;

    public void patientDisplayData() {
        patientListData = patientGetData();

        patients_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        patients_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        patients_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        patients_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        patients_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        patients_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        patients_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        patients_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        patients_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        patients_tableView.setItems(patientListData);
    }

    public void patientActionButton() {

        connect = DatabaseConnection.connectDB();
        patientListData = patientGetData();

        Callback<TableColumn<Patient, String>, TableCell<Patient, String>> cellFactory = (TableColumn<Patient, String> param) -> {
            final TableCell<Patient, String> cell = new TableCell<Patient, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                Patient pData = patients_tableView.getSelectionModel().getSelectedItem();
                                int num = patients_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_PatientID = pData.getPatientID();
                                Data.temp_address = pData.getAddress();
                                Data.temp_name = pData.getFullName();
                                Data.temp_gender = pData.getGender();
                                Data.temp_number = pData.getMobileNumber();
                                Data.temp_status = pData.getStatus();

                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditPatient.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            Patient pData = patients_tableView.getSelectionModel().getSelectedItem();
                            int num = patients_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "UPDATE patient SET date_delete = ?, status = 'Inactive' WHERE patient_id = ?";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Patient ID: " + pData.getPatientID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.setInt(2, pData.getPatientID());
                                    prepare.executeUpdate();

                                    doctorGetData();
                                    alert.successMessage("Deleted Successfully!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        patients_col_action.setCellFactory(cellFactory);
        patients_tableView.setItems(patientListData);

    }

    public ObservableList<Appointment> appointmentGetData() {

        ObservableList<Appointment> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment";

        connect = DatabaseConnection.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Appointment aData;
            while (result.next()) {
                aData = new Appointment(result.getInt("id"), result.getInt("appointment_id"),
                        result.getString("name"), result.getString("gender"), result.getLong("mobile_number"),
                        result.getString("description"), result.getString("diagnosis"),
                        result.getString("treatment"), result.getString("address"),
                        result.getString("doctor"), result.getString("specialization"),
                        result.getDate("date"), result.getDate("date_modify"),
                        result.getDate("date_delete"), result.getString("status"),
                        result.getDate("schedule"));
                listData.add(aData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Appointment> appointmentListData;

    public void appointmentDisplayData() {
        appointmentListData = appointmentGetData();

        appointments_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointments_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointments_tableView.setItems(appointmentListData);

    }

    public void appointmentActionButton() {

        connect = DatabaseConnection.connectDB();
        appointmentListData = appointmentGetData();

        Callback<TableColumn<Appointment, String>, TableCell<Appointment, String>> cellFactory = (TableColumn<Appointment, String> param) -> {
            final TableCell<Appointment, String> cell = new TableCell<Appointment, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                Appointment aData = appointments_tableView.getSelectionModel().getSelectedItem();
                                int num = appointments_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_appID = String.valueOf(aData.getAppointmentID());
                                Data.temp_appName = aData.getName();
                                Data.temp_appGender = aData.getGender();
                                Data.temp_appAddress = aData.getAddress();
                                Data.temp_appDescription = aData.getDescription();
                                Data.temp_appDiagnosis = aData.getDiagnosis();
                                Data.temp_appTreatment = aData.getTreatment();
                                Data.temp_appMobileNumber = String.valueOf(aData.getMobileNumber());
                                Data.temp_appDoctor = aData.getDoctorID();
                                Data.temp_appSpecialization = aData.getSpecialization();
                                Data.temp_appStatus = aData.getStatus();

                                Parent root = FXMLLoader.load(getClass().getResource("EditAppointmentForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            Appointment aData = appointments_tableView.getSelectionModel().getSelectedItem();
                            int num = appointments_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "UPDATE appointment SET date_delete = ? AND status ='Inactive' WHERE appointment_id = '"
                                    + aData.getAppointmentID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Appointment ID: " + aData.getAppointmentID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    doctorGetData();
                                    alert.successMessage("Deleted Successfully!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        appointments_action.setCellFactory(cellFactory);
        appointments_tableView.setItems(appointmentListData);

    }

    public ObservableList<Patient> paymentGetData() {

        ObservableList<Patient> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM patient WHERE date_delete IS NULL AND status_pay IS NULL";
        connect = DatabaseConnection.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Patient pData;
            while (result.next()) {

                pData = new Patient(result.getInt("id"),
                        result.getInt("patient_id"), result.getString("full_name"),
                        result.getString("gender"), result.getString("description"),
                        result.getString("diagnosis"), result.getString("treatment"),
                        result.getString("doctor"), result.getString("image"), result.getDate("date"));

                listData.add(pData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<Patient> paymentListData;

    public void paymentDisplayData() {
        paymentListData = paymentGetData();

        payment_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        payment_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        payment_col_diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        payment_col_doctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        payment_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        payment_tableView.setItems(paymentListData);

    }

    public void paymentSelectItems() {

        Patient pData = payment_tableView.getSelectionModel().getSelectedItem();
        int num = payment_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        if (pData.getImage() != null) {
            String path = "File:" + pData.getImage();
            image = new Image(path, 144, 105, false, true);
            payment_circle.setFill(new ImagePattern(image));

            Data.temp_path = pData.getImage();
        }

        Data.temp_PatientID = pData.getPatientID();
        Data.temp_name = pData.getFullName();
        Data.temp_date = String.valueOf(pData.getDate());

        payment_patientID.setText(String.valueOf(pData.getPatientID()));
        payment_name.setText(pData.getFullName());
        payment_doctor.setText(pData.getDoctor());
        payment_date.setText(String.valueOf(pData.getDate()));

    }

    public void paymentCheckOutBtn() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("CheckOutPatient.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Hospital Management System | Check-Out");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileUpdateBtn() {
        connect = DatabaseConnection.connectDB();
        if (profile_adminID.getText().isEmpty()
                || profile_username.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE admin SET username = ?, email = ?, gender = ? "
                        + "WHERE admin_id = " + Data.admin_id;

                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_username.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_status.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    profileDisplayInfo();

                    alert.successMessage("Updated Successfully");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                String updateData = "UPDATE admin SET username = ?, email = ?, image = ?, gender = ? "
                        + "WHERE admin_id = " + Data.admin_id;
                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_username.getText());
                    prepare.setString(2, profile_email.getText());

                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    Path copy = Paths.get("C:\\Users\\user\\IdeaProjects\\OOP Project\\Admin Pictures\\"
                            + Data.admin_id + ".jpg");

                    Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    prepare.setString(3, copy.toAbsolutePath().toString());
                    prepare.setString(4, profile_status.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();
                    profileDisplayInfo();
                    profileDisplayImages();
                    alert.successMessage("Updated Successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void profileDisplayImages() {

        String selectData = "SELECT * FROM admin WHERE admin_id = " + Data.admin_id;
        connect = DatabaseConnection.connectDB();

        String tempPath1 = "";
        String tempPath2 = "";
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                tempPath1 = "File:" + result.getString("image");
                tempPath2 = "File:" + result.getString("image");

                if (result.getString("image") != null) {
                    image = new Image(tempPath1, 1012, 22, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(tempPath2, 137, 95, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void staffDisplayData() {
        staffListData = staffGetData();

        staff_col_staffID.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        staff_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        staff_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        staff_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        staff_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        staff_col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        staff_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        staff_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        staff_tableView.setItems(staffListData);
    }

    public void profileInsertImage() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*jpg", "*jpeg", "*png"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 137, 95, false, true);
            profile_circle.setFill(new ImagePattern(image));
        }

    }

    public void profileDisplayInfo() {

        String sql = "SELECT * FROM admin WHERE admin_id = " + Data.admin_id;
        System.out.println(Data.admin_id);
        connect = DatabaseConnection.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_adminID.setText(result.getString("admin_id"));
                profile_username.setText(result.getString("username"));
                profile_email.setText(result.getString("email"));
                profile_status.getSelectionModel().select(result.getString("gender"));

                profile_label_adminIO.setText(result.getString("admin_id"));
                profile_label_name.setText(result.getString("username"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : Data.gender) {
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }

    // MAKE SURE THAT THE ID OF EVERY COMPONENTS TO THE OTHER IS DIFFERENT
    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            System.out.println("Switching to Dashboard Form");
            dashboard_form.setVisible(true);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            staff_form.setVisible(false);
            duties_form.setVisible(false);

            dashboardAD();
            dashboardTP();
            dashboardAP();
            dashboardTA();
            dashboardGetDoctorDisplayData();

            current_form.setText("Dashboard Form");
        } else if (event.getSource() == doctors_btn) {
            System.out.println("Switching to Doctor's Form");
            dashboard_form.setVisible(false);
            doctors_form.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            staff_form.setVisible(false);
            duties_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF DOCTORS IN TABLEVIEW
            doctorDisplayData();
            doctorActionButton();

            current_form.setText("Doctor's Form");
        } else if (event.getSource() == patients_btn) {
            System.out.println("Switching to Patient's Form");
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            staff_form.setVisible(false);
            duties_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF PATIENTS IN TABLEVIEW
            patientDisplayData();
            patientActionButton();

            current_form.setText("Patient's Form");
        } else if (event.getSource() == staff_btn)
        {
            System.out.println("Switching to Staff Form");
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            staff_form.setVisible(true);
            duties_form.setVisible(false);


            current_form.setText("Staff's Form");
        }
        else if(event.getSource()== duties_btn)
        {
            System.out.println("Switching to Duties Form");
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            staff_form.setVisible(false);
            duties_form.setVisible(true);

            staffDisplayData();
            staffActionButton();

            current_form.setText("Duties Form");
        }
        else if (event.getSource() == appointments_btn)
        {
            System.out.println("Switching to Appointment's Form");
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            staff_form.setVisible(false);
            duties_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF APPOINTMENTS IN TABLEVIEW
            appointmentDisplayData();
            appointmentActionButton();

            current_form.setText("Appointment's Form");
        } else if (event.getSource() == payment_btn)
        {
            System.out.println("Switching to Payment Form");
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(true);
            profile_form.setVisible(false);
            staff_form.setVisible(false);
            duties_form.setVisible(false);

            paymentDisplayData();

            current_form.setText("Payment Form");
        } else if (event.getSource() == profile_btn)
        {
            System.out.println("Switching to Profile Form");
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(true);
            staff_form.setVisible(false);
            duties_form.setVisible(false);

            profileStatusList();
            profileDisplayInfo();
            profileDisplayImages();

            current_form.setText("Profile Form");
        }
    }


    public void displayAdminIDUsername() {

        String sql = "SELECT * FROM admin WHERE username = '"
                + Data.admin_username + "'";

        connect = DatabaseConnection.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                nav_adminID.setText(result.getString("admin_id"));
                String tempUsername = result.getString("username");
                tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1); // TO SET THE FIRST LETTER TO UPPER CASE
                nav_username.setText(tempUsername);
                top_username.setText(tempUsername);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void logoutBtn() {

        try {
            if (alert.confirmationMessage("Are you sure you want to logout?")) {
                Parent root = FXMLLoader.load(getClass().getResource("AdminLoginPage.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();
                logout_btn.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void runTime() {

        new Thread()
        {

            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {

                        Thread.sleep(1000); // 1000 ms = 1s

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Platform.runLater(() -> {
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        runTime();
        displayAdminIDUsername();

//        DASHBOARD
        dashboardAD();
        dashboardTP();
        dashboardAP();
        dashboardTA();
        dashboardGetDoctorDisplayData();
        dashboardPatientDataChart();
        dashboardDoctorDataChart();

        // TO DISPLAY IMMEDIATELY THE DATA OF DOCTORS IN TABLEVIEW
        doctorDisplayData();
        doctorActionButton();

        // TO DISPLAY IMMEDIATELY THE DATA OF PATIENTS IN TABLEVIEW
        patientDisplayData();
        patientActionButton();

        // TO DISPLAY IMMEDIATELY THE DATA OF APPOINTMENTS IN TABLEVIEW
        appointmentDisplayData();
        appointmentActionButton();


        //For Staff

        staffDisplayData();
        staffActionButton();

        // TO DISPLAY IMMEDIATELY THE DATA OF PAYMENT IN TABLEVIEW
        paymentDisplayData();

        profileStatusList();
        profileDisplayInfo();
        profileDisplayImages();
    }

}

