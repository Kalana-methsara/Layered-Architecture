package com.lankaice.project.controller;

import com.lankaice.project.bo.BOFactoryImpl;
import com.lankaice.project.bo.BOType;
import com.lankaice.project.bo.custom.EmployeeBO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeReportController implements Initializable {

    @FXML
    private AnchorPane employeeReport;

    @FXML
    private Label lblCashier;

    @FXML
    private Label lblDriver;

    @FXML
    private Label lblManager;

    @FXML
    private Label lblSupervisor;

    @FXML
    private Label lblWorker;

    private final EmployeeBO employeeBO = ((BOFactoryImpl) BOFactoryImpl.getInstance()).getBO(BOType.EMPLOYEE);


    @FXML
    void btnClose(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    loadEmployeeCounts();
    }
    private void loadEmployeeCounts() {
        try {
            lblManager.setText(String.valueOf(employeeBO.getEmployeeCountByRole("Manager")));
            lblCashier.setText(String.valueOf(employeeBO.getEmployeeCountByRole("Cashier")));
            lblDriver.setText(String.valueOf(employeeBO.getEmployeeCountByRole("Driver")));
            lblSupervisor.setText(String.valueOf(employeeBO.getEmployeeCountByRole("Supervisor")));
            lblWorker.setText(String.valueOf(employeeBO.getEmployeeCountByRole("Worker")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
