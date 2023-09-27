package com.devteam.management_of_noncommunicable_diseases.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Insurance {
    String insuranceId;

    String registerPlace;

    LocalDate startDate;

    LocalDate expirationDate;

    LocalDateTime deletedAt;

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getRegisterPlace() {
        return registerPlace;
    }

    public void setRegisterPlace(String registerPlace) {
        this.registerPlace = registerPlace;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Insurance () {}

    public Insurance(String insuranceId, String registerPlace, LocalDate startDate, LocalDate expirationDate, LocalDateTime deletedAt) {
        this.insuranceId = insuranceId;
        this.registerPlace = registerPlace;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.deletedAt = deletedAt;
    }
}
