package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.bo.exception.DuplicateException;
import com.lankaice.project.bo.exception.InUseException;
import com.lankaice.project.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    List<CustomerDto> getAllCustomers() throws SQLException,ClassNotFoundException;

    void saveCustomer(CustomerDto dto) throws DuplicateException, Exception;

    void updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    void deleteCustomer(String id) throws InUseException, Exception;

    String getNextId() throws SQLException,ClassNotFoundException;

    int getCustomerCount() throws SQLException, ClassNotFoundException;

    public String getLastCustomerId() throws SQLException, ClassNotFoundException;

    List<CustomerDto> searchCustomer(String searchText, String searchText1, String searchText2,
                                     String searchText3, String searchText4, String searchText5,
                                     String searchText6) throws SQLException, ClassNotFoundException;
    String findNameById(String customerId) throws SQLException, ClassNotFoundException;
    String findIdByName(String customerName) throws SQLException, ClassNotFoundException;

}
