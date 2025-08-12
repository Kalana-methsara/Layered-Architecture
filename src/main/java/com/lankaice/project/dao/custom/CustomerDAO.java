package com.lankaice.project.dao.custom;


import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerDAO extends CrudDAO<Customer> {

     String findNameById(String customerId) throws SQLException, ClassNotFoundException;

     String findIdByName(String customerName) throws SQLException, ClassNotFoundException;

    int getCustomerCount() throws SQLException, ClassNotFoundException;

    String getLastCustomerId() throws SQLException, ClassNotFoundException;

    List<Customer> searchCustomer(String searchText, String searchText1, String searchText2, String searchText3, String searchText4, String searchText5, String searchText6);

    boolean existsCustomerByNic(String nic) throws SQLException, ClassNotFoundException;

    boolean existsCustomerByPhoneNumber(String phoneNumber) throws SQLException, ClassNotFoundException;

    Optional<Customer> findCustomerByNic(String nic) throws SQLException, ClassNotFoundException;

    }
