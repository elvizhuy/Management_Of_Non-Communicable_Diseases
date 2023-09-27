package com.devteam.management_of_noncommunicable_diseases.Model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Staff extends SQLException {
    String userName;
    String firstName;
    String lastName;
    String email;
    String idNumber;
    String phoneNumber;
    String passWord;
    String confirmPassword;
    String jobCode;
    String position;
    LocalDate startWork;
    LocalDateTime deletedAt;
    int facilityId;
    int departmentId;
    int departmentFacilityId = 0;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getConfirm_password() {
        return confirmPassword;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirmPassword = confirm_password;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public LocalDate getStartWork() {
        return startWork;
    }

    public void setStartWork(LocalDate startWork) {
        this.startWork = startWork;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Staff() {

    }

    public Staff(String userName, String firstName, String lastName, String email, String idNumber, String phoneNumber, String passWord, String confirmPassword, String jobCode, LocalDate startWork) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.confirmPassword = confirmPassword;
        this.jobCode = jobCode;
        this.startWork = startWork;
    }
}
