package com.projects.hospitalmanagementsystem;

import java.sql.Date;

public class Patient {

    private Integer id;
    private Integer patientID;
    private String password;
    private String fullName;
    private Long mobileNumber;
    private String address;
    private String image;
    private String description;
    private String diagnosis;
    private String treatment;
    private String doctor;
    private String specialization;
    private String gender;
    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;

    public Patient(Integer id, Integer patientID, String password, String fullName, Long mobileNumber,
                   String gender, String address, String image, String description, String diagnosis, String treatment,
                   String doctor, String specialization, Date date, Date dateModify, Date dateDelete, String status) {
        this.id = id;
        this.patientID = patientID;
        this.password = password;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.address = address;
        this.image = image;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctor = doctor;
        this.specialization = specialization;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;
    }

    public Patient(Integer id, Integer patientID, String fullName, String gender,
                   Long mobileNumber, String address, String status, Date date,
                   Date dateModify, Date dateDelete) {
        this.id = id;
        this.patientID = patientID;
        this.fullName = fullName;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.status = status;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
    }

    public Patient(Integer id, Integer patientID, String fullName, String gender,
                   String description, String diagnosis, String treatment,
                   String doctor, String image, Date date) {
        this.id = id;
        this.patientID = patientID;
        this.fullName = fullName;
        this.gender = gender;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctor = doctor;
        this.image = image;
        this.date = date;
    }

    public Patient(Integer id, Integer patientID, String description,
                   String diagnosis, String treatment, Date date) {
        this.id = id;
        this.patientID = patientID;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getSpecialization() {
        return specialization;
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
