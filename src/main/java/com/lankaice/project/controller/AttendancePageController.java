package com.lankaice.project.controller;

import com.lankaice.project.bo.BOFactoryImpl;
import com.lankaice.project.bo.BOType;
import com.lankaice.project.bo.custom.AttendanceBO;
import com.lankaice.project.bo.custom.EmployeeBO;
import com.lankaice.project.dto.AttendanceDto;
import com.lankaice.project.dto.EmployeeDto;
import com.lankaice.project.entity.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class AttendancePageController implements Initializable {

    @FXML
    private ChoiceBox<String> textHours;

    @FXML
    private ChoiceBox<String> textMinutes;

    @FXML
    private ChoiceBox<String> textHours1;

    @FXML
    private ChoiceBox<String> textMinutes1;

    @FXML
    private AnchorPane ancAttendance;


    private String oldShiftValue;

    @FXML
    private TableColumn<AttendanceDto, String> colDate;

    @FXML
    private TableColumn<AttendanceDto, String> colEmployeeId;

    @FXML
    private TableColumn<AttendanceDto, String> colInTime;

    @FXML
    private TableColumn<AttendanceDto, String> colName;

    @FXML
    private TableColumn<AttendanceDto, String> colOutTime;

    @FXML
    private TableColumn<AttendanceDto, String> colRole;

    @FXML
    private TableColumn<AttendanceDto, String> colShift;

    @FXML
    private TableColumn<AttendanceDto, String> colWorkingHours;

    @FXML
    private TableView<AttendanceDto> tableView;

    @FXML
    private DatePicker textDate;

    @FXML
    private TextField textEmployeeId;

    @FXML
    private Hyperlink textError;

    @FXML
    private Label textInTime;

    @FXML
    private TextField textName;

    @FXML
    private Label textOutTime;

    @FXML
    private ChoiceBox<String> textShift;
    @FXML
    private Label textStatus;

    @FXML
    private ChoiceBox<String> textRole;

    @FXML
    private Label textWorkingHours;

    private final AttendanceBO attendanceBO = ((BOFactoryImpl) BOFactoryImpl.getInstance()).getBO(BOType.ATTENDANCE);
    private final EmployeeBO employeeBO = ((BOFactoryImpl) BOFactoryImpl.getInstance()).getBO(BOType.EMPLOYEE);

    @FXML
    void SetData(MouseEvent event) {
        AttendanceDto attendanceDto = tableView.getSelectionModel().getSelectedItem();
        if (attendanceDto != null) {
            textEmployeeId.setText(attendanceDto.getEmployeeId());
            textName.setText(attendanceDto.getName());
            textDate.setValue(attendanceDto.getDate());
            textShift.setValue(attendanceDto.getShift());
            oldShiftValue=attendanceDto.getShift();
            textStatus.setText(attendanceDto.getStatus());
            textInTime.setText(attendanceDto.getInTime() != null ? attendanceDto.getInTime().toString() : "");
            textOutTime.setText(attendanceDto.getOutTime() != null ? attendanceDto.getOutTime().toString() : "");

            try {
                Employee employeeDto = employeeBO.searchById(attendanceDto.getEmployeeId());
                if (employeeDto != null) {
                    textRole.setValue(employeeDto.getJobRole());
                }
            } catch (Exception e) {
                e.printStackTrace();
                textRole.setValue("Unknown");
            }
            textWorkingHours.setText(calculateWorkingHours(attendanceDto.getInTime(), attendanceDto.getOutTime()));

        }
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            String id = textEmployeeId.getText();
            String name = textName.getText();
            LocalDate date = textDate.getValue();
            String shift = textShift.getValue();
            String status = "Present";
            LocalTime inTime = textInTime.getText().isEmpty() ? null : LocalTime.parse(textInTime.getText());
            LocalTime outTime = textOutTime.getText().isEmpty() ? null : LocalTime.parse(textOutTime.getText());

            if (id.isEmpty() || name.isEmpty() || shift == null) {
                showErrorMessage("Please fill all required fields.");
                return;
            }

            AttendanceDto dto = new AttendanceDto(id, name, date, shift, status,inTime, outTime);
            attendanceBO.saveAttendance(dto);

                showSuccessMessage("Attendance saved successfully!");
                btnClearOnAction(null);


        } catch (Exception e) {
            e.printStackTrace();
        showErrorMessage("Failed to save attendance.");
        }
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        textEmployeeId.clear();
        textName.clear();
        textInTime.setText("");
        textOutTime.setText("");
        textRole.setValue(null);
        textShift.setValue(null);
        textWorkingHours.setText("");
        textStatus.setText("");
        textDate.setValue(LocalDate.now());
        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            String id = textEmployeeId.getText();
            String name = textName.getText();
            LocalDate date = textDate.getValue();
            String newShift = textShift.getValue();
            String status = textStatus.getText();
            LocalTime inTime = textInTime.getText().isEmpty() ? null : LocalTime.parse(textInTime.getText());
            LocalTime outTime = textOutTime.getText().isEmpty() ? null : LocalTime.parse(textOutTime.getText());

            if (id.isEmpty() || name.isEmpty() || newShift == null) {
                showErrorMessage("Please fill all required fields.");
                return;
            }

            // Call update method with oldShift and newShift
            boolean isUpdated = attendanceBO.updateAttendanceShiftAndTimes(id, date, oldShiftValue, newShift, inTime, outTime);

            if (isUpdated) {
                showSuccessMessage("Attendance updated successfully!");
                btnClearOnAction(null);
            } else {
                showErrorMessage("Failed to update attendance.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage("Error while updating.");
        }
    }



    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            String id = textEmployeeId.getText();
            LocalDate date = textDate.getValue();
            String shift = textShift.getValue();

            if (id.isEmpty() || date == null) {
                showErrorMessage("Select a record to delete.");
                return;
            }

            boolean isDeleted = attendanceBO.deleteAttendance(id, LocalDate.parse(date.toString()),shift);

            if (isDeleted) {
                showSuccessMessage("Attendance deleted successfully!");
                btnClearOnAction(null);
            } else {
                showErrorMessage("Failed to delete attendance.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage("Error while deleting.");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textRole.setItems(FXCollections.observableArrayList("Worker", "Supervisor", "Cashier", "Driver", "Manager"));
        textShift.setItems(FXCollections.observableArrayList("Morning", "Night"));
        for (int i = 0; i <= 24; i++) {
            textHours.getItems().add(String.format("%02d", i));
            textHours1.getItems().add(String.format("%02d", i));
        }

        // Add 00 to 59 in minuteBox
        for (int i = 0; i <= 59; i++) {
            textMinutes.getItems().add(String.format("%02d", i));
            textMinutes1.getItems().add(String.format("%02d", i));
        }
        textDate.setValue(LocalDate.now());
        loadTable();
        textDate.valueProperty().addListener((obs, oldDate, newDate) -> loadTable());

    }


    private void loadTable() {
        LocalDate selectedDate = textDate.getValue();
        if (selectedDate == null) {
            return;
        }
        textDate.setValue(selectedDate);
        colRole.setCellValueFactory(cellData -> {
            AttendanceDto dto = cellData.getValue();
            try {
                Employee employeeDto = employeeBO.searchById(dto.getEmployeeId());
                if (employeeDto != null) {
                    return new SimpleStringProperty(employeeDto.getJobRole());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new SimpleStringProperty("Unknown");
        });

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colShift.setCellValueFactory(new PropertyValueFactory<>("shift"));
        colInTime.setCellValueFactory(new PropertyValueFactory<>("inTime"));
        colOutTime.setCellValueFactory(new PropertyValueFactory<>("outTime"));
        colWorkingHours.setCellValueFactory(new PropertyValueFactory<>("workingHours"));

        try {
            var attendanceList = attendanceBO.getAttendanceByDate(LocalDate.parse(selectedDate.toString()));

            for (AttendanceDto dto : attendanceList) {
                dto.setWorkingHours(calculateWorkingHours(dto.getInTime(), dto.getOutTime()));
            }

            // ✅ Sort attendanceList by inTime
            attendanceList.sort((a, b) -> {
                if (a.getInTime() == null && b.getInTime() == null) return 0;
                if (a.getInTime() == null) return 1;
                if (b.getInTime() == null) return -1;
                return a.getInTime().compareTo(b.getInTime());
            });

            tableView.getItems().setAll(attendanceList);

        } catch (Exception e) {
            e.printStackTrace();
            tableView.getItems().clear();
        }

    }


    private String calculateWorkingHours(LocalTime inTime, LocalTime outTime) {
        try {
            if (inTime != null && outTime != null) {
                java.time.Duration duration = java.time.Duration.between(inTime, outTime);
                long hours = duration.toHours();
                long minutes = duration.toMinutesPart();
                return String.format("%02d:%02d", hours, minutes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "00:00";
    }

    public void onSearchEmpoyee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       String employeeId = textEmployeeId.getText();
        if (employeeId.isEmpty()) {
            showErrorMessage("Please enter an Employee ID.");
            return;
        }
        Employee employee = employeeBO.searchById(employeeId);
        textName.setText(employee.getName());
        textRole.setValue(employee.getJobRole());
    }

    public void onKeyHours(KeyEvent keyEvent) {
            textMinutes.requestFocus();

    }

    public void onKeyMinutes(KeyEvent keyEvent) {
         String hours = textHours.getValue();
         String minutes = textMinutes.getValue();
         textInTime.setText(hours + ":" + minutes);

    }
    public void onKeyHours1(KeyEvent keyEvent) {
        textMinutes1.requestFocus();

    }

    public void onKeyMinutes1(KeyEvent keyEvent) {
        String hours = textHours1.getValue();
        String minutes = textMinutes1.getValue();
        textOutTime.setText(hours + ":" + minutes);

    }
    private void showErrorMessage(String message) {
        textError.setStyle("-fx-text-fill: red;");
        textError.setText(message);
    }

    private void showSuccessMessage(String message) {
        textError.setStyle("-fx-text-fill: green;");
        textError.setText(message);
    }
}
