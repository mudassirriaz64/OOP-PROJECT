package com.projects.hospitalmanagementsystem;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StaffDashboardController implements Initializable {


    @FXML
    private Button Staffduty_Btn;
    @FXML
    private AnchorPane Duty_form;

    @FXML
    private AnchorPane StaffProfile_form;

    @FXML
    private Button Staff_dutyupdateBtn;

    @FXML
    private ComboBox<String> Staff_dutyStatus;

    @FXML
    private Button Staff_dutyclearBtn;

    @FXML
    private DatePicker Staff_dutydate;

    @FXML
    private Button Staff_dutydltbtn;

    @FXML
    private TextArea Staff_dutyschedule;

    @FXML
    private TextField Staff_role;

    @FXML
    private Button appointments_btn;

    @FXML
    private Label current_form;

    @FXML
    private AreaChart<?, ?> dashboard_chart_PD;



    @FXML
    private TableView<Duty> dashboard_tableView;

    @FXML
    private Label dashboard_InactiveDuties;

    @FXML
    private Label dashboard_ActiveDuties;

    @FXML
    private Label dashboard_TotalDuties;

    @FXML
    private Button dashboard_btn;

    @FXML
    private TableColumn<Duty, String> dashboard_col_dutyschedule;

    @FXML
    private TableColumn<Duty, String> dashboard_col_dutyshift;

    @FXML
    private TableColumn<Duty, String> dashboard_col_dutystatus;

    @FXML
    private TableColumn<Duty, String> dashboard_col_staffrole;

    @FXML
    private TableColumn<Duty, String> dashboard_col_dutydate;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label date_time;

    @FXML
    private TableColumn<?, ?> employees_col_dateDelete;

    @FXML
    private TableColumn<?, ?> employees_col_dateModify;

    @FXML
    private TableColumn<?, ?> employees_col_dateModify1;

    @FXML
    private TableColumn<?, ?> employees_col_email;

    @FXML
    private TableColumn<?, ?> employees_col_hireDate;

    @FXML
    private TableColumn<?, ?> employees_col_jobTitle;

    @FXML
    private TableColumn<?, ?> employees_col_status;

    @FXML
    private TableView<?> employees_tableView;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nav_StaffID;

    @FXML
    private Label nav_username;

    @FXML
    private TextArea profile_address;

    @FXML
    private Button profile_btn;

    @FXML
    private Circle profile_circleImage;

    @FXML
    private TextField profile_doctorID;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<?> profile_gender;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private Label profile_label_doctorID;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_name;

    @FXML
    private TextField profile_mobileNumber;

    @FXML
    private TextField profile_name;

    @FXML
    private ComboBox<String> profile_role;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private ComboBox<String> staff_dutyshift;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private ComboBox<String> Staff_dutyroles;

    private ObservableList<Duty> dashboardGetDutyData;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @FXML
    void StaffAddDutyBtn(ActionEvent event) {
        String role = Staff_dutyroles.getSelectionModel().getSelectedItem().toString();
        String dutydate = Staff_dutydate.getEditor().getText(); // Assuming format is MM/dd/yyyy
        String schedule = Staff_dutyschedule.getText();
        String status = Staff_dutyStatus.getSelectionModel().getSelectedItem().toString();
        String shift = staff_dutyshift.getSelectionModel().getSelectedItem().toString();

        try {
            // Convert the dutydate to java.sql.Date in the correct format
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date parsedDate = dateFormat.parse(dutydate);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            // Check if another staff member with the same role is already on duty at the specified time
            if (isStaffOnDuty(sqlDate, shift, role)) {
                alert.errorMessage("Staff Already on Duty, Another staff member with the same role is already on duty at this time.");
                return;
            }

            Connection con = DatabaseConnection.connectDB();
            String query = "INSERT INTO Duty (staff_id, name, role, shift, date, schedule, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, Data.staff_id);
            pst.setString(2, Data.staff_name);
            pst.setString(3, role);
            pst.setString(4, shift);
            pst.setDate(5, sqlDate);
            pst.setString(6, schedule);
            pst.setString(7, status);
            int rowsAffected = pst.executeUpdate();
            con.close();

            if (rowsAffected > 0) {
                alert.successMessage("Duty Added Successfully");
            }
        } catch (ParseException pe) {
            pe.printStackTrace(); // Handle date parsing error
        } catch (Exception e) {
            e.printStackTrace(); // Handle SQL or other errors
        }
    }

    // Method to check if another staff member with the same role is already on duty at the specified time
    private boolean isStaffOnDuty(Date date, String shift, String role) {
        boolean isOnDuty = false;
        try {
            Connection con = DatabaseConnection.connectDB();
            String query = "SELECT * FROM Duty WHERE date = ? AND shift = ? AND role = ? AND status = 'Active'";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setDate(1, (java.sql.Date) date);
            pst.setString(2, shift);
            pst.setString(3, role);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                // If there is a result, another staff member with the same role is already on duty
                isOnDuty = true;
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
        return isOnDuty;
    }



    @FXML
    void StaffDutyClearBtn(ActionEvent event)
    {
        Staff_role.clear();
        Staff_dutydate.getEditor().clear();
        Staff_dutyschedule.clear();
        Staff_dutyStatus.getEditor().clear();
        staff_dutyshift.getEditor().clear();
    }

    @FXML
    void StaffDutyDeleteBtn(ActionEvent event)
    {


    }

    @FXML
    void StaffDutyUpdateBtn(ActionEvent event) {

    }

    @FXML
    void DutySelect(MouseEvent event) {

    }

    @FXML
    void logoutBtn(ActionEvent event) {

    }

    @FXML
    void profileChangeProfile(ActionEvent event) {

    }

    public void dashboardDisplayData()
    {
        dashboardGetDutyData = dashboardDutyTableView();

        dashboard_col_dutydate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dashboard_col_dutyschedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        dashboard_col_dutyshift.setCellValueFactory(new PropertyValueFactory<>("shift"));
        dashboard_col_dutystatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        dashboard_col_staffrole.setCellValueFactory(new PropertyValueFactory<>("role"));

        dashboard_tableView.setItems(dashboardGetDutyData);
    }

    public ObservableList<Duty> dashboardDutyTableView() {

        ObservableList<Duty> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM duty WHERE Staff_id = '"
                + Data.staff_id + "' and status='Active' and date_delete IS NULL ";

        connect = DatabaseConnection.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Duty dData;
            while (result.next())
            {
                dData = new Duty(result.getString("shift"), result.getString("Schedule"),
                        result.getDate("date"), result.getString("status"),result.getString("role"));


                listData.add(dData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    @FXML
    void profileUpdateBtn(ActionEvent event) {

    }

    public void DutyStatusList()
    {
        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        Staff_dutyStatus.setItems(listData);
    }

    public void StaffRoleList()
    {
        List<String> listR = new ArrayList<>();

        for (String data : Data.roles) {
            listR.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listR);
        Staff_dutyroles.setItems(listData);
    }

    public void StaffShiftList() {
        List<String> listSh = new ArrayList<>();

        if (Data.shifts != null) {
            for (String data : Data.shifts) {
                listSh.add(data);
            }
        }


        ObservableList listData = FXCollections.observableArrayList(listSh);
        staff_dutyshift.setItems(listData);
    }

    @FXML
    void switchForm(ActionEvent event)
    {
        if (event.getSource() == dashboard_btn)
        {
            Duty_form.setVisible(false);
            StaffProfile_form.setVisible(false);
            dashboard_form.setVisible(true);
        }
        else if (event.getSource() == Staffduty_Btn)
        {
            Duty_form.setVisible(true);
            StaffProfile_form.setVisible(false);
            dashboard_form.setVisible(false);
        }
        else if (event.getSource() == profile_btn)
        {
            Duty_form.setVisible(false);
            StaffProfile_form.setVisible(true);
            dashboard_form.setVisible(false);
        }

    }

    public void dashbboardDisplayTD() {
        String sql = "SELECT COUNT(id) FROM duty Where Staff_id = '"
                + Data.staff_id + "'";
        connect = DatabaseConnection.connectDB();
        int getTD = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTD = result.getInt("COUNT(id)");
            }
            dashboard_TotalDuties.setText(String.valueOf(getTD));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayAD() {
        String sql = "SELECT COUNT(id) FROM duty Where status='Active' AND Staff_id = '"
                + Data.staff_id + "'";
        connect = DatabaseConnection.connectDB();
        int getAD = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getAD = result.getInt("COUNT(id)");
            }
            dashboard_ActiveDuties.setText(String.valueOf(getAD));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayID() {
        String sql = "SELECT COUNT(id) FROM duty Where status='Inactive' AND Staff_id = '"
                + Data.staff_id + "'";
        connect = DatabaseConnection.connectDB();
        int getID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getID = result.getInt("COUNT(id)");
            }
            dashboard_InactiveDuties.setText(String.valueOf(getID));
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

    public void displayStaffIDNumberName() {

        String name = Data.staff_name;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        nav_username.setText(name);
        nav_StaffID.setText(Data.staff_id);
        top_username.setText(name);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        DutyStatusList();
        StaffRoleList();
        StaffShiftList();
        runTime();
        displayStaffIDNumberName();
        dashbboardDisplayTD();
        dashbboardDisplayAD();
        dashbboardDisplayID();
        dashboardDisplayData();
    }


}