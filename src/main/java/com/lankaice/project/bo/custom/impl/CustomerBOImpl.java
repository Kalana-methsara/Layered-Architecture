package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.CustomerBO;
import com.lankaice.project.dao.custom.CustomerDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.CustomerDto;
import com.lankaice.project.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.CUSTOMER);

    @Override
    public List<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> customers = customerDAO.getAll();
        List<CustomerDto> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDto customerDto = new CustomerDto(
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getNic(),
                    customer.getEmail(),
                    customer.getContact(),
                    customer.getAddress(),
                    customer.getDescription()
            );
            customerDTOS.add(customerDto);
        }
        return customerDTOS;
    }

}
