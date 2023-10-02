package com.devteam.management_of_noncommunicable_diseases.Model;

/*import com.devteam.management_of_noncommunicable_diseases.Dao.PeopleDao;*/
import javafx.stage.Window;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class People {
    String idNumber;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String gender;
    String address;
    String phoneNumber;
    String email;
    String note;
    LocalDateTime deletedAt;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deleted_at) {
        this.deletedAt = deleted_at;
    }

    Window owner;
    /*PeopleDao peopleDao = new PeopleDao();*/

    public People() {

    }

    public People(String idNumber, String firstName, String lastName,LocalDate dateOfBirth, String gender, String address, String phoneNumber, String email, String note) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.note = note;
    }

   /* public void add() throws SQLException {
        peopleDao.addPeople(owner, this.idNumber, this.firstName,this.lastName, String.valueOf(this.dateOfBirth), String.valueOf(this.gender), this.address, this.phoneNumber, this.email, this.note );
    }*/

    protected void update () {

    }

    protected void delete () {

    }


}
