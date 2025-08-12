package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.CustomerBO;
import com.lankaice.project.bo.exception.DuplicateException;
import com.lankaice.project.bo.exception.InUseException;
import com.lankaice.project.bo.exception.NotFoundException;
import com.lankaice.project.bo.util.EntityDTOConverter;
import com.lankaice.project.dao.DAOFactory;
import com.lankaice.project.dao.custom.CustomerDAO;
import com.lankaice.project.dao.custom.OrdersDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.CustomerDto;
import com.lankaice.project.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.CUSTOMER);
    private final OrdersDAO orderDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.ORDERS);
    private final EntityDTOConverter converter = new EntityDTOConverter();


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




    @Override
    public void saveCustomer(CustomerDto dto) throws DuplicateException, Exception {
        Optional<Customer> optionalCustomer = customerDAO.findById(dto.getCustomerId());
        if (optionalCustomer.isPresent()) {
//            duplicate id
            throw new DuplicateException("Duplicate customer id");
        }

        Optional<Customer> customerByNicOptional = customerDAO.findCustomerByNic(dto.getNic());
        if (customerByNicOptional.isPresent()) {
            throw new DuplicateException("Duplicate customer nic");
        }

        if (customerDAO.existsCustomerByPhoneNumber(dto.getContact())) {
            throw new DuplicateException("Duplicate customer phone number");
        }

        Customer customer = converter.getCustomer(dto);

        boolean save = customerDAO.save(customer);
    }

//    private Customer customerDtoTOEntity(){
//
//    }

    @Override
    public void updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        Optional<Customer> optionalCustomer = customerDAO.findById(dto.getCustomerId());
        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException("Customer not found");
        }

        Optional<Customer> customerByNicOptional = customerDAO.findCustomerByNic(dto.getNic());
        if (customerByNicOptional.isPresent()) {
            Customer customer = customerByNicOptional.get();

            if (customer.getCustomerId() != dto.getCustomerId()) {
                throw new DuplicateException("Duplicate nic");
            }
        }

        Customer customer = converter.getCustomer(dto);
        customerDAO.update(customer);
    }

    @Override
    public boolean deleteCustomer(String id) throws InUseException, Exception {
        Optional<Customer> optionalCustomer = customerDAO.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException("Customer not found..!");
        }

        // customer have orders ?
        if (orderDAO.existOrdersByCustomerId(id)) {
            throw new InUseException("Customer has orders");
        }

        try {
            boolean delete = customerDAO.delete(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String lastId = customerDAO.getLastCustomerId();
        char tableChar = 'C';
        if (lastId != null) {
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format(tableChar + "%03d", nextIdNumber);
        }
        return tableChar + "001";
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerCount();
    }

    @Override
    public String getLastCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getLastCustomerId();
    }
    @Override
    public List<CustomerDto> searchCustomer(String searchText, String searchText1, String searchText2,
                                            String searchText3, String searchText4, String searchText5,
                                            String searchText6) throws SQLException, ClassNotFoundException {
        List<Customer> customers = customerDAO.searchCustomer(
                searchText, searchText1, searchText2,
                searchText3, searchText4, searchText5, searchText6
        );

        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            customerDtos.add(new CustomerDto(
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getNic(),
                    customer.getEmail(),
                    customer.getContact(),
                    customer.getAddress(),
                    customer.getDescription()
            ));
        }
        return customerDtos;
    }
    @Override
    public String findNameById(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.findNameById(customerId);
    }
    @Override
    public String findIdByName(String customerName) throws SQLException, ClassNotFoundException {
        return customerDAO.findIdByName(customerName);
    }
}
