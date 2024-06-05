package com.projects.hospitalmanagementsystem;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author WINDOWS 10
 */
public class DoctorDashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private Button logout_btn;

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
    private Button patients_btn;

    @FXML
    private Button appointments_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_IP;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_tA;

    @FXML
    private AreaChart<?, ?> dashboad_chart_PD;

    @FXML
    private BarChart<?, ?> dashboad_chart_DD;

    @FXML
    private TableView<Appointment> dashboad_tableView;

    @FXML
    private TableColumn<Appointment, String> dashboad_col_appointmentID;

    @FXML
    private TableColumn<Appointment, String> dashboad_col_name;

    @FXML
    private TableColumn<Appointment, String> dashboad_col_description;

    @FXML
    private TableColumn<Appointment, String> dashboad_col_appointmentDate;

    @FXML
    private TableColumn<Appointment, String> dashboad_col_status;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private TextField patients_patientID;

    @FXML
    private TextField patients_patientName;

    @FXML
    private TextField patients_mobileNumber;

    @FXML
    private TextField patients_password;

    @FXML
    private TextArea patients_address;

    @FXML
    private Button patients_confirmBtn;

    @FXML
    private Label patients_PA_patientID;

    @FXML
    private Label patients_PA_password;

    @FXML
    private Label patients_PA_dateCreated;

    @FXML
    private Label patients_PI_patientName;

    @FXML
    private Label patients_PI_gender;

    @FXML
    private Label patients_PI_mobileNumber;

    @FXML
    private Label patients_PI_address;

    @FXML
    private Button patients_PI_addBtn;

    @FXML
    private Button patients_PI_recordBtn;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private TableView<Appointment> appointments_tableView;

    @FXML
    private TableColumn<Appointment, String> appointments_col_appointmentID;

    @FXML
    private TableColumn<Appointment, String> appointments_col_name;

    @FXML
    private TableColumn<Appointment, String> appointments_col_gender;

    @FXML
    private TableColumn<Appointment, String> appointments_col_contactNumber;

    @FXML
    private TableColumn<Appointment, String> appointments_col_description;

    @FXML
    private TableColumn<Appointment, String> appointments_col_date;

    @FXML
    private TableColumn<Appointment, String> appointments_col_dateModify;

    @FXML
    private TableColumn<Appointment, String> appointments_col_dateDelete;

    @FXML
    private TableColumn<Appointment, String> appointments_col_status;

    @FXML
    private TableColumn<Appointment, String> appointments_col_action;

    @FXML
    private TextField appointment_appointmentID;

    @FXML
    private TextField appointment_name;

    @FXML
    private ComboBox<String> appointment_gender;

    @FXML
    private TextField appointment_description;

    @FXML
    private TextField appointment_diagnosis;

    @FXML
    private TextField appointment_treatment;

    @FXML
    private TextField appointment_mobileNumber;

    @FXML
    private TextArea appointment_address;

    @FXML
    private ComboBox<String> appointment_status;

    @FXML
    private DatePicker appointment_schedule;

    @FXML
    private Button appointment_insertBtn;

    @FXML
    private Button appointment_updateBtn;

    @FXML
    private Button appointment_clearBtn;

    @FXML
    private Button appointment_deleteBtn;

    @FXML
    private ComboBox<String> patients_gender;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circleImage;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_doctorID;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_doctorID;

    @FXML
    private TextField profile_name;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private TextField profile_mobileNumber;

    @FXML
    private TextArea profile_address;

    @FXML
    private ComboBox<String> profile_specialization;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_updateBtn;

    //    DATABASE TOOLSs
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    private final AlertMessage alert = new AlertMessage();

    public void dashbboardDisplayIP()
    {
        String sql = "SELECT COUNT(id) FROM patient WHERE status = 'Inactive' AND Doctor_id = '"
                + Data.doctor_id + "'";
        connect = DatabaseConnection.connectDB();
        int getIP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next())
            {
                getIP = result.getInt("COUNT(id)");
            }
            dashboard_IP.setText(String.valueOf(getIP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayTP() {
        String sql = "SELECT COUNT(id) FROM patient WHERE Doctor_id = '"
                + Data.doctor_id + "'";
        connect = DatabaseConnection.connectDB();
        int getTP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTP = result.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(getTP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayAP() {
        String sql = "SELECT COUNT(id) AS count FROM patient WHERE status = 'Active' AND Doctor_id = '"
                + Data.doctor_id + "'";
        connect = DatabaseConnection.connectDB();
        int getAP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getAP = result.getInt("count");
            }
            dashboard_AP.setText(String.valueOf(getAP));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    public void dashbboardDisplayTA() {
        String sql = "SELECT COUNT(id) FROM appointment WHERE status = 'Active' AND Doctor_id = '"
                + Data.doctor_id + "'";
        connect = DatabaseConnection.connectDB();
        int getTA = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTA = result.getInt("COUNT(id)");
            }
            dashboard_tA.setText(String.valueOf(getTA));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Appointment> dashboardAppointmentTableView() {

        ObservableList<Appointment> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE Doctor_id = '"
                + Data.doctor_id + "'";

        connect = DatabaseConnection.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Appointment aData;
            while (result.next()) {
                aData = new Appointment(result.getInt("appointment_id"),
                        result.getString("name"), result.getString("description"),
                        result.getDate("date"), result.getString("status"));

                listData.add(aData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Appointment> dashboardGetData;

    public void dashboardDisplayData() {
        dashboardGetData = dashboardAppointmentTableView();

        dashboad_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        dashboad_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboad_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dashboad_col_appointmentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dashboad_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboad_tableView.setItems(dashboardGetData);
    }

    public void dashboardNOP() {

        dashboad_chart_PD.getData().clear();

        String sql = "SELECT DATE(date) AS appointment_date, COUNT(id) AS appointment_count FROM patient WHERE Doctor_id = '" + Data.doctor_id + "' GROUP BY DATE(date) ORDER BY DATE(date) ASC LIMIT 8";

        connect = DatabaseConnection.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_PD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardNOA() {

        dashboad_chart_DD.getData().clear();

        String sql = "SELECT DATE(date) AS appointment_date, COUNT(id) AS appointment_count FROM patient WHERE Doctor_id = '" + Data.doctor_id + "' GROUP BY DATE(date) ORDER BY DATE(date) ASC LIMIT 7";

        connect = DatabaseConnection.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_DD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void patientConfirmBtn() {

        // CHECK IF SOME OR ALL FIELDS ARE EMPTY
        if (patients_patientID.getText().isEmpty()
                || patients_patientName.getText().isEmpty()
                || patients_gender.getSelectionModel().getSelectedItem() == null
                || patients_mobileNumber.getText().isEmpty()
                || patients_password.getText().isEmpty()
                || patients_address.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            // TO DISPLAY THE DATA FROM PERSONAL ACCOUNT
            patients_PA_patientID.setText(patients_patientID.getText());
            patients_PA_password.setText(patients_password.getText());
            patients_PA_dateCreated.setText(String.valueOf(sqlDate));

            // TO DISPLAY THE DATA FROM PERSONAL INFORMATION
            patients_PI_patientName.setText(patients_patientName.getText());
            patients_PI_gender.setText(patients_gender.getSelectionModel().getSelectedItem());
            patients_PI_mobileNumber.setText(patients_mobileNumber.getText());
            patients_PI_address.setText(patients_address.getText());
        }

    }

    @FXML
    private void patientAddBtn() {

        if (patients_PA_patientID.getText().isEmpty()
                || patients_PA_password.getText().isEmpty()
                || patients_PA_dateCreated.getText().isEmpty()
                || patients_PI_patientName.getText().isEmpty()
                || patients_PI_gender.getText().isEmpty()
                || patients_PI_mobileNumber.getText().isEmpty()
                || patients_PI_address.getText().isEmpty()) {
            alert.errorMessage("Something went wrong");
        } else {
            Connection connect = null;
            Statement statement = null;
            ResultSet result = null;
            PreparedStatement prepare = null;

            try {
                connect = DatabaseConnection.connectDB();
                String doctorName = "";
                String doctorSpecialization = "";

                String getDoctor = "SELECT * FROM doctor WHERE doctor_id = ?";
                prepare = connect.prepareStatement(getDoctor);
                prepare.setString(1, nav_adminID.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    doctorName = result.getString("full_name");
                    doctorSpecialization = result.getString("specialization");
                }

                // Close the previous statement and result set
                prepare.close();
                result.close();

                // CHECK IF THE PATIENT ID THAT THE DOCTOR WANTS TO INSERT/ADD IS EXISTING ALREADY
                String checkPatientID = "SELECT * FROM patient WHERE patient_id = ?";
                prepare = connect.prepareStatement(checkPatientID);
                prepare.setString(1, patients_PA_patientID.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(patients_PA_patientID.getText() + " already exists");
                } else {
                    if (doctorSpecialization == null || doctorSpecialization.isEmpty()) {
                        doctorSpecialization = "N/A";
                    }

                    String insertData = "INSERT INTO patient (patient_id, password, full_name, mobile_number, "
                            + "address, doctor, doctor_id, specialization, date, status) "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, patients_PA_patientID.getText());
                    prepare.setString(2, patients_PA_password.getText());
                    prepare.setString(3, patients_PI_patientName.getText());
                    prepare.setString(4, patients_PI_mobileNumber.getText());
                    prepare.setString(5, patients_PI_address.getText());
                    prepare.setString(6, nav_username.getText());
                    prepare.setString(7, nav_adminID.getText());
                    prepare.setString(8, doctorSpecialization);
                    prepare.setDate(9, sqlDate);
                    prepare.setString(10, "Confirm");

                    prepare.executeUpdate();

                    alert.successMessage("Added successfully!");
                    // TO CLEAR ALL FIELDS AND SOME LABELS
                    patientClearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (result != null) result.close();
                    if (prepare != null) prepare.close();
                    if (connect != null) connect.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @FXML
    private void patientRecordBtn() {
        try {
            // LINK THE NAME OF YOUR FXML FOR RECORD PAGE
            Parent root = FXMLLoader.load(getClass().getResource("PatientRecordPage.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Hospital Management System | Record of Patients");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void patientClearFields() {
        patients_patientID.clear();
        patients_patientName.clear();
        patients_gender.getSelectionModel().clearSelection();
        patients_mobileNumber.clear();
        patients_password.clear();
        patients_address.clear();

        patients_PA_patientID.setText("");
        patients_PA_password.setText("");
        patients_PA_dateCreated.setText("");

        patients_PI_patientName.setText("");
        patients_PI_gender.setText("");
        patients_PI_mobileNumber.setText("");
        patients_PI_address.setText("");
    }

    private void patientGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableList(listG);

        patients_gender.setItems(listData);

    }

    public void appointmentInsertBtn() {
        // CHECK IF THE FIELD(S) ARE EMPTY
        if (appointment_appointmentID.getText().isEmpty()
                || appointment_name.getText().isEmpty()
                || appointment_gender.getSelectionModel().getSelectedItem() == null
                || appointment_mobileNumber.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                || appointment_status.getSelectionModel().getSelectedItem() == null
                || appointment_schedule.getValue() == null) {
            alert.errorMessage("Please fill the blank fields");
        } else {
            Connection connect = null;
            PreparedStatement prepare = null;
            ResultSet result = null;

            try {
                connect = DatabaseConnection.connectDB();

                // CHECK IF THE APPOINTMENT ID ALREADY EXISTS
                String checkAppointmentID = "SELECT * FROM appointment WHERE appointment_id = ?";
                prepare = connect.prepareStatement(checkAppointmentID);
                prepare.setString(1, appointment_appointmentID.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(appointment_appointmentID.getText() + " is already taken");
                } else {
                    result.close(); // Close the previous ResultSet

                    // GET DOCTOR SPECIALIZATION
                    String getDoctorData = "SELECT * FROM doctor WHERE doctor_id = ?";
                    prepare = connect.prepareStatement(getDoctorData);
                    prepare.setString(1, Data.doctor_id);
                    result = prepare.executeQuery();

                    String getSpecialization = "";
                    if (result.next()) {
                        getSpecialization = result.getString("specialization");
                    }
                    result.close(); // Close the previous ResultSet

                    // INSERT NEW APPOINTMENT DATA
                    String insertData = "INSERT INTO appointment (appointment_id, name, gender, description, diagnosis, treatment, mobile_number, address, date, status, doctor, doctor_id, specialization, schedule) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    prepare = connect.prepareStatement(insertData);

                    prepare.setString(1, appointment_appointmentID.getText());
                    prepare.setString(2, appointment_name.getText());
                    prepare.setString(3, appointment_gender.getSelectionModel().getSelectedItem().toString());
                    prepare.setString(4, appointment_description.getText());
                    prepare.setString(5, appointment_diagnosis.getText());
                    prepare.setString(6, appointment_treatment.getText());
                    prepare.setString(7, appointment_mobileNumber.getText());
                    prepare.setString(8, appointment_address.getText());

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
                    prepare.setDate(9, sqlDate);
                    prepare.setString(10, appointment_status.getSelectionModel().getSelectedItem().toString());
                    prepare.setString(11, Data.doctor_name);
                    prepare.setString(12, Data.doctor_id);
                    prepare.setString(13, getSpecialization);
                    prepare.setDate(14, java.sql.Date.valueOf(appointment_schedule.getValue()));

                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    alert.successMessage("Successfully added!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (result != null) result.close();
                    if (prepare != null) prepare.close();
                    if (connect != null) connect.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void appointmentUpdateBtn() {

        if (appointment_appointmentID.getText().isEmpty()
                || appointment_name.getText().isEmpty()
                || appointment_gender.getSelectionModel().getSelectedItem() == null
                || appointment_mobileNumber.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                || appointment_status.getSelectionModel().getSelectedItem() == null
                || appointment_schedule.getValue() == null) {
            alert.errorMessage("Please fill the blank fields");
        } else {
            // TO GET THE DATE TODAY
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

            String updateData = "UPDATE appointment SET name = '"
                    + appointment_name.getText() + "', gender = '"
                    + appointment_gender.getSelectionModel().getSelectedItem() + "', mobile_number = '"
                    + appointment_mobileNumber.getText() + "', description = '"
                    + appointment_description.getText() + "', address = '"
                    + appointment_address.getText() + "', status = '"
                    + appointment_status.getSelectionModel().getSelectedItem() + "', schedule = '"
                    + appointment_schedule.getValue() + "', date_modify = '"
                    + sqlDate + "' WHERE appointment_id = '"
                    + appointment_appointmentID.getText() + "'";

            connect = DatabaseConnection.connectDB();

            try {
                if (alert.confirmationMessage("Are you sure you want to UPDATE Appointment ID: "
                        + appointment_appointmentID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    alert.successMessage("Successully Updated!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void appointmentDeleteBtn() {
        if (appointment_appointmentID.getText().isEmpty()) {
            alert.errorMessage("Please select the item first");
        } else {
            String updateData = "UPDATE appointment SET date_delete = ?, status = 'Inactive' WHERE appointment_id = ?";
            connect = DatabaseConnection.connectDB();
            try {
                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                if (alert.confirmationMessage("Are you sure you want to DELETE Appointment ID: " + appointment_appointmentID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setDate(1, sqlDate);
                    prepare.setString(2, appointment_appointmentID.getText());
                    prepare.executeUpdate();
                    appointmentShowData();
                    appointmentClearBtn();
                    appointmentAppointmentID();
                    alert.successMessage("Successfully Updated!");
                } else {
                    alert.errorMessage("Cancelled.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Close the prepared statement and connection
                try {
                    if (prepare != null) {
                        prepare.close();
                    }
                    if (connect != null) {
                        connect.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // TO CLEAR ALL FIELDS
    public void appointmentClearBtn() {
        appointment_name.clear();
        appointment_gender.getSelectionModel().clearSelection();
        appointment_mobileNumber.clear();
        appointment_description.clear();
        appointment_treatment.clear();
        appointment_diagnosis.clear();
        appointment_address.clear();
        appointment_status.getSelectionModel().clearSelection();
        appointment_schedule.setValue(null);
    }

    private Integer appointmentID;

    public void appointmentGetAppointmentID()
    {
        String sql = "SELECT MAX(appointment_id) FROM Appointment";
        connect = DatabaseConnection.connectDB();

        int tempAppID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                tempAppID = result.getInt("MAX(appointment_id)");
            }
            if (tempAppID == 0) {
                tempAppID += 1;
            } else {
                tempAppID += 1;
            }
            appointmentID = tempAppID;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appointmentAppointmentID()
    {
        appointmentGetAppointmentID();

        appointment_appointmentID.setText("" + appointmentID);
        appointment_appointmentID.setDisable(true);

    }

    public void appointmentGenderList() {
        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        appointment_gender.setItems(listData);

    }

    public void appointmentStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        appointment_status.setItems(listData);

    }

    public ObservableList<Appointment> appointmentGetData()
    {

        ObservableList<Appointment> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE date_delete IS NULL and Doctor_id = '"
                + Data.doctor_id + "'";

        connect = DatabaseConnection.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Appointment appData;

            while (result.next())
            {

                appData = new Appointment(result.getInt("appointment_id"),
                        result.getString("name"), result.getString("gender"),
                        result.getLong("mobile_number"), result.getString("description"),
                        result.getString("diagnosis"), result.getString("treatment"),
                        result.getString("address"), result.getDate("date"),
                        result.getDate("date_modify"), result.getDate("date_delete"),
                        result.getString("status"), result.getDate("schedule"));
                listData.add(appData);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<Appointment> appoinmentListData;

    public void appointmentShowData() {
        appoinmentListData = appointmentGetData();

        appointments_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointments_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointments_tableView.setItems(appoinmentListData);
    }
// TO SELECT THE DATA PER ROW IN THE TABLE

    public void appointmentSelect() {

        Appointment appData = appointments_tableView.getSelectionModel().getSelectedItem();
        int num = appointments_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        appointment_appointmentID.setText("" + appData.getAppointmentID());
        appointment_name.setText(appData.getName());
        appointment_gender.getSelectionModel().select(appData.getGender());
        appointment_mobileNumber.setText("" + appData.getMobileNumber());
        appointment_description.setText(appData.getDescription());
        appointment_diagnosis.setText(appData.getDiagnosis());
        appointment_treatment.setText(appData.getTreatment());
        appointment_address.setText(appData.getAddress());
        appointment_status.getSelectionModel().select(appData.getStatus());

    }

    public void profileUpdateBtn() {

        connect = DatabaseConnection.connectDB();

        if (profile_doctorID.getText().isEmpty()
                || profile_name.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_gender.getSelectionModel().getSelectedItem() == null
                || profile_mobileNumber.getText().isEmpty()
                || profile_address.getText().isEmpty()
                || profile_specialization.getSelectionModel().getSelectedItem() == null
                || profile_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            // CHECK IF THE PATH IS NULL
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE doctor SET full_name = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, specialization = ?, status = ?, modify_date = ?"
                        + " WHERE doctor_id = '"
                        + Data.doctor_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobileNumber.getText());
                    prepare.setString(5, profile_address.getText());
                    prepare.setString(6, profile_specialization.getSelectionModel().getSelectedItem());
                    prepare.setString(7, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String updateData = "UPDATE doctor SET full_name = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, image = ?, specialization = ?, status = ?, modify_date = ?"
                        + " WHERE doctor_id = '"
                        + Data.doctor_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobileNumber.getText());
                    prepare.setString(5, profile_address.getText());
                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    // LINK YOUR DIRECTORY FOLDER
                    Path copy = Paths.get("C:\\Users\\user\\IdeaProjects\\OOP PROJECT\\Pictures\\"
                            + Data.doctor_id + ".jpg");

                    try {
                        // TO PUT THE IMAGE FILE TO YOUR DIRECTORY FOLDER
                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    prepare.setString(6, copy.toAbsolutePath().toString());
                    prepare.setString(7, profile_specialization.getSelectionModel().getSelectedItem());
                    prepare.setString(8, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(9, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void profileChangeProfile() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*png", "*jpg", "*jpeg"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 128, 103, false, true);
            profile_circleImage.setFill(new ImagePattern(image));
        }

    }

    public void profileLabels() {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        connect = DatabaseConnection.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_label_doctorID.setText(result.getString("doctor_id"));
                profile_label_name.setText(result.getString("full_name"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileFields() {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";

        connect = DatabaseConnection.connectDB();
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_doctorID.setText(result.getString("doctor_id"));
                profile_name.setText(result.getString("full_name"));
                profile_email.setText(result.getString("email"));
                profile_gender.getSelectionModel().select(result.getString("gender"));
                profile_mobileNumber.setText(result.getString("mobile_number"));
                profile_address.setText(result.getString("address"));
                profile_specialization.getSelectionModel().select(result.getString("specialization"));
                profile_status.getSelectionModel().select(result.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileDisplayImages() {

        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        String temp_path1 = "";
        String temp_path2 = "";
        connect = DatabaseConnection.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_path1 = "File:" + result.getString("image");
                temp_path2 = "File:" + result.getString("image");

                if (result.getString("image") != null) {
                    image = new Image(temp_path1, 1012, 22, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(temp_path2, 128, 103, false, true);
                    profile_circleImage.setFill(new ImagePattern(image));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        profile_gender.setItems(listData);

    }

    private String[] specialization = {"Allergist", "Dermatologist", "Ophthalmologist", "Gynecologist", "Cardiologist"};

    public void profileSpecializedList() {

        List<String> listS = new ArrayList<>();

        for (String data : specialization) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_specialization.setItems(listData);
    }

    public void profileStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }

    public void displayAdminIDNumberName() {

        String name = Data.doctor_name;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        nav_username.setText(name);
        nav_adminID.setText(Data.doctor_id);
        top_username.setText(name);

    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == patients_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == appointments_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            profile_form.setVisible(false);
        } else if (event.getSource() == profile_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(true);
        }
    }

    public void logoutBtn() {

        try {
            if (alert.confirmationMessage("Are you sure you want to logout?")) {
                Data.doctor_id = "";
                Data.doctor_name = "";
                Parent root = FXMLLoader.load(getClass().getResource("DoctorLoginPage.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                // TO HIDE YOUR MAIN FORM
                logout_btn.getScene().getWindow().hide();

                Data.doctor_id = "";
                Data.doctor_name = "";
                Data.temp_PatientID = 0;
                Data.temp_name = "";
                Data.temp_gender = "";
                Data.temp_number = Long.parseLong("0");
                Data.temp_address = "";
                Data.temp_status = "";
                Data.temp_date = "";
                Data.temp_path = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void runTime() {
        new Thread() {
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {
                        Thread.sleep(1000);
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
        displayAdminIDNumberName();
        runTime();

        dashbboardDisplayIP();
        dashbboardDisplayTP();
        dashbboardDisplayAP();
        dashbboardDisplayTA();
        dashboardDisplayData();
        dashboardNOP();
        dashboardNOA();

        // TO SHOW THE DATA IMMEDIATELY ONCE YOU LOGGED IN YOUR ACCOUNT
        appointmentShowData();
        appointmentGenderList();
        appointmentStatusList();
        appointmentAppointmentID();

        // TO SHOW THE DATA IMMEDIATELY THE PATIENT'S GENDER COMBOXBOX
        patientGenderList();

//        PROFILE
        profileLabels();
        profileFields(); // TO DISPLAY ALL DETAILS TO THE FIELDS
        profileGenderList();
        profileSpecializedList();
        profileStatusList();
        profileDisplayImages(); // TO DISPLAY THE PROFILE PICTURE OF THE DOCTOR

    }

}