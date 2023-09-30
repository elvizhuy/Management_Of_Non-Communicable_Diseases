package com.devteam.management_of_noncommunicable_diseases.Controller;

import com.devteam.management_of_noncommunicable_diseases.Interface.ComboBoxData;
import com.devteam.management_of_noncommunicable_diseases.Interface.InfoBox;
import com.devteam.management_of_noncommunicable_diseases.Interface.ShowAlert;
import com.devteam.management_of_noncommunicable_diseases.Model.Staff;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaffDao implements InfoBox, ComboBoxData {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Window owner;

    public void addStaff(Window owner, String username, String firstName, String lastName, String jobCode, String email, String idNumber, String phoneNumber, String passWord, String confirmPassword, String startWork, String dateOfBirth) throws SQLException {
        String INSERT_ACCOUNTS_QUERY = "INSERT into accounts (user_name,password) VALUES (?,?)";
        String INSERT_STAFFS_QUERY = "INSERT into staffs (first_name,last_name,email,id_number,phone_number,date_of_birth,start_work) VALUES (?,?,?,?,?,?,?,?)";

        boolean checkNameEmpty = validateEmptyFields(username, "Nhập tên đăng nhập", owner);
        boolean checkNameExisted = checkExistedItem(username, "accounts", "user_name", "Tên đã tồn tại", owner);
        boolean checkFirstName = validateEmptyFields(firstName, "Nhập tên", owner);
        boolean checkLastName = validateEmptyFields(lastName, "Nhập họ", owner);
        boolean checkEmailExist = checkExistedItem(email, "staffs", "email", "Email đã tồn tại", owner);
        boolean checkEmptyEmail = validateEmptyFields(email, "Nhập email", owner);
        boolean validOrInvalid = checkEmailValid(email, owner);
        boolean checkEmptyIdNumber = validateEmptyFields(idNumber, "Nhập số cccd", owner);
        boolean checkEmptyPhoneNumber = validateEmptyFields(phoneNumber, "Nhập số điện thoại", owner);
        boolean checkEmptyPassword = validateEmptyFields(passWord, "Nhập mật khẩu", owner);
        boolean checkEmptyConfirmPassword = validateEmptyFields(passWord, "Nhập mật khẩu", owner);
        boolean checkMatchingPass = checkMatchingPasswordAndLength(passWord, confirmPassword, owner);
        boolean checkExistingIdNumberAndLength = checkIdNumberAndLength(idNumber, "staffs", "ID đã tồn tại!", owner);
        boolean checkEmptyStartWork = validateEmptyFields(startWork, "Nhập ngày bắt đầu làm việc", owner);

        if (checkNameExisted && checkNameEmpty && checkMatchingPass
                && checkFirstName && checkLastName && checkEmailExist
                && checkEmptyEmail && validOrInvalid && checkEmptyIdNumber
                && checkExistingIdNumberAndLength && checkEmptyPassword
                && checkEmptyConfirmPassword && checkEmptyPhoneNumber && checkEmptyStartWork) {
            try {
                connection = DBConnection.open();
                assert connection != null;
                MD5 md5 = new MD5();
                String encodePassword = md5.encode(passWord);
                preparedStatement = connection.prepareStatement(INSERT_ACCOUNTS_QUERY);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, encodePassword);
                preparedStatement = connection.prepareStatement(INSERT_STAFFS_QUERY);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, idNumber);
                preparedStatement.setString(5, phoneNumber);
                preparedStatement.setString(6, String.valueOf(Date.valueOf(dateOfBirth)));
                preparedStatement.setString(7, String.valueOf(Date.valueOf(startWork)));
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBConnection.closeAll(connection, preparedStatement, resultSet);
            }
        } else {
            new Thread(() -> {
                Platform.runLater(() -> {
                    InfoBox.infoBox("Thiếu thông tin,hãy kiểm tra lại", null, "Thất Bại...");
                });
            }).start();
        }
    }

    public static Staff searchStaff (String idNumber) throws SQLException, ClassNotFoundException {
        String FIND_SPECIFIC_STAFF = "SELECT * FROM staffs WHERE id_number = " + idNumber;
        try {
            ResultSet rs = DBConnection.dbExecuteQuery(FIND_SPECIFIC_STAFF);
            return getStaffFromResultSet(rs);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Staff> searchStaffs (String name,String ID) throws SQLException, ClassNotFoundException {
        String SELECT_ALL_STAFFS =
                """
                        SELECT S.*,DF.*,P.*,SP.*,JC.*,FL.*,DM.*\s
                        FROM staffs S\s
                        join department_facilities DF on S.department_facilities_id = DF.id
                        inner join facilities FL on DF.facility_id = FL.id
                        inner join departments DM on DF.department_id = DM.id
                        join job_codes JC on S.job_code = JC.id
                        join positions P on S.position_id = P.id
                        join specializations SP on S.specialization_id = SP.id""";
        String SELECT_ALL_WITH_CONDITION =
                """
                SELECT S.*,DF.*,P.*,SP.*,JC.*,FL.*,DM.*
                FROM staffs S
                join department_facilities DF on S.department_facilities_id = DF.id
                inner join facilities FL on DF.facility_id = FL.id
                inner join departments DM on DF.department_id = DM.id
                join job_codes JC on S.job_code = JC.id
                join positions P on S.position_id = P.id
                join specializations SP on S.specialization_id = SP.id
                where S.first_name like ? or S.id_number = ?""";
        try {
            if (name == null || ID == null) {
                ResultSet rs = DBConnection.dbExecuteQuery(SELECT_ALL_STAFFS);
                return getStaffList(rs);
            }else {
                ResultSet rs = DBConnection.dbPrepareStatementAndExecuteQuery(SELECT_ALL_WITH_CONDITION,name,ID);
                return getStaffList(rs);
            }
        } catch (SQLException e) {
            System.out.println("Lệnh thực thi thất bại: " + e);
            throw e;
        }
    }

    private static Staff getStaffFromResultSet(ResultSet rs) throws SQLException {
        Staff staff = null;
        if (rs.next()) {
            staff = new Staff();
            setStaffProperties(rs, staff);
        }
        return staff;
    }

    private static ObservableList<Staff> getStaffList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Staff> staffList = FXCollections.observableArrayList();
        while (rs.next()) {
            Staff staff = new Staff();
            setStaffProperties(rs, staff);
            staffList.add(staff);
        }
        return staffList;
    }

    private static void setStaffProperties(ResultSet rs, Staff staff) throws SQLException {
        staff.setIdNumber(rs.getString("id_number"));
        staff.setFirstName(rs.getString("first_name"));
        staff.setLastName(rs.getString("last_name"));
        staff.setEmail(rs.getString("email"));
        staff.setPhoneNumber(rs.getString("phone_number"));
        staff.setStartWork(rs.getDate("start_work").toLocalDate());
        staff.setJobCode(rs.getString("job_code"));
        staff.setDepartmentFacilityId(rs.getInt("department_facilities_id"));
        staff.setPosition(rs.getInt("position"));
        staff.setSpecializationId(rs.getInt("specialization_id"));
    }

    public void updateStaff(Window owner) throws SQLException {


//        String QUERY_UPDATE_STAFF = "UPDATE staffs SET id_number = ?, email = ?, first_name = ?, last_name = ?, job_code = ?, phone_number = ? WHERE id = ?";
//        try {
//            connection = DBConnection.open();
//            assert connection != null;
//            preparedStatement = connection.prepareStatement(FIND_SPECIFIC_STAFF);
//            preparedStatement.setInt(1, staff.getId());
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                String id_number = resultSet.getString("id_number");
//                String first_name = resultSet.getString("first_name");
//                String last_name = resultSet.getString("last_name");
//                String idOfUserInDb = resultSet.getString("phone_number");
//                String Email = resultSet.getString("email");
//                String job_code = resultSet.getString("job_code");
//            }
//            preparedStatement = connection.prepareStatement(QUERY_UPDATE_STAFF);
//            preparedStatement.setString(1, staff.getIdNumber());
//            preparedStatement.setString(2, staff.getEmail());
//            preparedStatement.setString(3, staff.getFirstName());
//            preparedStatement.setString(4, staff.getLastName());
//            preparedStatement.setString(5, staff.getJobCode());
//            preparedStatement.setString(6, staff.getPhoneNumber());
//
//            preparedStatement.executeUpdate();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            DBConnection.closeAll(connection, preparedStatement, resultSet);
//        }
    }

    protected boolean validateEmptyFields(String dataField, String textToNotice, Window owner) {
        if (dataField.isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
            return false;
        }
        return true;
    }

    protected boolean checkMatchingPasswordAndLength(String passWord, String confirmPassword, Window owner) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{4,}");
        Matcher matcher = pattern.matcher(passWord);
        if (!Objects.equals(passWord, confirmPassword)) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Mật khẩu không khớp");
            return false;
        } else {
            if (!matcher.matches()) {
                ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Mật khẩu tối thiểu 4 ký tự");
                return false;
            }
        }
        return true;
    }

    protected boolean checkEmailValid(String Email, Window owner) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(Email);
        if (!matcher.matches()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Định dạng email ko hợp lệ");
            return false;
        }
        return true;
    }

    public boolean checkIdNumberAndLength(String IdFromUserInput, String database, String textToNotice, Window owner) throws SQLException {
        String SELECT_ID_NUMBER_QUERY = "SELECT id_number FROM " + database + " WHERE id_number = ?";
        String IdInDatabase = null;
        connection = DBConnection.open();
        assert connection != null;
        preparedStatement = connection.prepareStatement(SELECT_ID_NUMBER_QUERY);
        preparedStatement.setInt(1, Integer.parseInt(IdFromUserInput));
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            IdInDatabase = resultSet.getString("id_number");
            Pattern pattern = Pattern.compile("[0-9]{12}");
            Matcher matcher = pattern.matcher(IdFromUserInput);
            if (!Objects.equals(IdFromUserInput, IdInDatabase)) {
                ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
                return false;
            } else {
                if (!matcher.matches()) {
                    ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Độ dài không hợp lệ,Id phải đủ 12 số");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkExistedItem(String itemFromUserInput, String databaseName, String columnName, String textToNotice, Window owner) throws SQLException {
        String SELECT_ITEM_QUERY = "SELECT " + columnName + " FROM " + databaseName + " WHERE " + columnName + " = ?";
        String itemInDatabase = null;
        connection = DBConnection.open();
        assert connection != null;
        preparedStatement = connection.prepareStatement(SELECT_ITEM_QUERY);
        preparedStatement.setString(1, itemFromUserInput);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            itemInDatabase = resultSet.getString(columnName);
            if (Objects.equals(itemFromUserInput, itemInDatabase)) {
                ShowAlert.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", textToNotice);
                return false;
            }
        }
        return true;
    }
}
