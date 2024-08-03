package com.projects.hospitalmanagementsystem;

import java.sql.Date;

public class Staff {
    private Integer id;
    private final String staffID;
    private String password;
    private String fullName;
    private String email;
    private String gender;
    private Long mobileNumber;
    private final String role;
    private String address;
    private String image;
    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;

    public Staff(Integer id, String staffID, String password, String fullName,
                  String email, String gender, Long mobileNumber, String role, String address,
                  String image, Date date, Date dateModify, Date dateDelete, String status) {
        this.id = id;
        this.staffID = staffID;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.role = role;
        this.address = address;
        this.image = image;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;
    }
    public Staff(String staffID, String fullName, String role, String status) {
        this.staffID = staffID;
        this.fullName = fullName;
        this.role= role;
        this.status = status;
    }

    public Staff(Integer id, String staffID, String fullName, String role, String email, String image) {
        this.id = id;
        this.staffID= staffID;
        this.fullName = fullName;
        this.role = role;
        this.email = email;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getPassword() {
        return password;
    }

    public  String getFullName()
    {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public String getRole() {
        return role;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }

    public Date getDateModify() {
        return dateModify;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public String getStatus() {
        return status;
    }

}


