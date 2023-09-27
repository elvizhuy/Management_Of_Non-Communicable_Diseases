package com.devteam.management_of_noncommunicable_diseases.Model;

public class DepartmentFacilities {
    int id;
    int facilityId;
    int departmentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public DepartmentFacilities() {

    }

    public DepartmentFacilities(int id, int facilityId, int departmentId) {
        this.id = id;
        this.facilityId = facilityId;
        this.departmentId = departmentId;
    }
}
