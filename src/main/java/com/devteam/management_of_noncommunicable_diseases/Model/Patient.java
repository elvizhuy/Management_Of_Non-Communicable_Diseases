package com.devteam.management_of_noncommunicable_diseases.Model;

import java.time.LocalDate;

public class Patient {
    int id;
    String peopleId;

    int diseaseId;

    LocalDate firstYearFound;

    String firstPlaceFound;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public LocalDate getFirstYearFound() {
        return firstYearFound;
    }

    public void setFirstYearFound(LocalDate firstYearFound) {
        this.firstYearFound = firstYearFound;
    }

    public String getFirstPlaceFound() {
        return firstPlaceFound;
    }

    public void setFirstPlaceFound(String firstPlaceFound) {
        this.firstPlaceFound = firstPlaceFound;
    }

    public Patient () {}

    public Patient(int id, String peopleId, int diseaseId, LocalDate firstYearFound, String firstPlaceFound) {
        this.id = id;
        this.peopleId = peopleId;
        this.diseaseId = diseaseId;
        this.firstYearFound = firstYearFound;
        this.firstPlaceFound = firstPlaceFound;
    }
}
