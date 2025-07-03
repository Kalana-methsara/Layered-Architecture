package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.CustomerDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Customer");
        List<Customer> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Customer(
                    rs.getString("customer_id"),
                    rs.getString("name"),
                    rs.getString("nic"),
                    rs.getString("email"),
                    rs.getString("contact"),
                    rs.getString("address"),
                    rs.getString("description")
            ));
        }
        return list;   }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer (customer_id, name, nic, email, contact, address, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql,
                customer.getCustomerId(),
                customer.getName(),
                customer.getNic(),
                customer.getEmail(),
                customer.getContact(),
                customer.getAddress(),
                customer.getDescription()
        );    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Customer SET name=?, nic=?, email=?, contact=?, address=?, description=? WHERE customer_id=?";
        return SQLUtil.execute(sql,
                customer.getName(),
                customer.getNic(),
                customer.getEmail(),
                customer.getContact(),
                customer.getAddress(),
                customer.getDescription(),
                customer.getCustomerId()
        );    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Customer WHERE customer_id=?";
        return SQLUtil.execute(sql, id);
    }

    @Override
    public List<String> getAllIds() {
        return List.of();
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.empty();
    }

    @Override
    public String findNameById(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(
                "SELECT name FROM Customer WHERE customer_id=?",
                customerId
        );
        if (rst.next()) {
            return rst.getString("name");
        }
        return "";
    }

    @Override
    public String findIdByName(String customerName) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(
                "SELECT customer_id FROM Customer WHERE name=?",customerName
        );
        if (rst.next()) {
            return rst.getString("customer_id");
        }
        return "";
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM Customer");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public String getLastCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT MAX(customer_id) FROM Customer");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public List<Customer> searchCustomer(String searchText, String searchText1, String searchText2, String searchText3, String searchText4, String searchText5, String searchText6) {
        List<Customer> customerList = new ArrayList<>();

        String sql = "SELECT * FROM Customer WHERE " +
                "customer_id LIKE ? OR " +
                "name LIKE ? OR " +
                "nic LIKE ? OR " +
                "email LIKE ? OR " +
                "contact LIKE ? OR " +
                "address LIKE ? OR " +
                "description LIKE ?";

        try {
            ResultSet rs = SQLUtil.execute(sql,
                    "%" + searchText + "%",
                    "%" + searchText1 + "%",
                    "%" + searchText2 + "%",
                    "%" + searchText3 + "%",
                    "%" + searchText4 + "%",
                    "%" + searchText5 + "%",
                    "%" + searchText6 + "%"
            );

            while (rs.next()) {
                customerList.add(new Customer(
                        rs.getString("customer_id"),
                        rs.getString("name"),
                        rs.getString("nic"),
                        rs.getString("email"),
                        rs.getString("contact"),
                        rs.getString("address"),
                        rs.getString("description")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // or use logger
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return customerList;
        }
}
