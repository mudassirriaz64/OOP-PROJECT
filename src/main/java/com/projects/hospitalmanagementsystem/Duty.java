package com.projects.hospitalmanagementsystem;

import java.util.Date;

public class Duty {

    private int id;
    private long dutyId;
    private String staffId;
    private String name;
    private String gender;
    private String role;
    private String shift;
    private long mobileNumber;
    private String address;
    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;
    private String schedule;

    // Constructors
    public Duty() {}

    public Duty( String shift, String schedule, Date date, String status, String role) {
        this.shift = shift; // Convert String to long
        this.schedule = schedule;
        this.date = date;
        this.status = status;
        this.role = role;
    }

    public Duty(int id, long dutyId, String staffId, String name, String gender, String role, String shift, long mobileNumber, String address, Date date, Date dateModify, Date dateDelete, String status, String schedule) {
        this.id = id;
        this.dutyId = dutyId;
        this.staffId = staffId;
        this.name = name;
        this.gender = gender;
        this.role = role;
        this.shift = shift;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;
        this.schedule = schedule;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDutyId() {
        return dutyId;
    }

    public void setDutyId(long dutyId) {
        this.dutyId = dutyId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateModify() {
        return dateModify;
    }

    public void setDateModify(Date dateModify) {
        this.dateModify = dateModify;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public void setDateDelete(Date dateDelete) {
        this.dateDelete = dateDelete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}