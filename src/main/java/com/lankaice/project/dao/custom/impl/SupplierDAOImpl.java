package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.SupplierDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.SupplierDto;
import com.lankaice.project.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Supplier");
        List<Supplier> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Supplier(
                    rs.getString("supplier_id"),
                    rs.getString("name"),
                    rs.getString("nic"),
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getString("address")
            ));
        }
        return list;
    }

    @Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Supplier (supplier_id, name, nic,contact,email,address) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql,
                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getNic(),
                supplier.getContact(),
                supplier.getEmail(),
                supplier.getAddress()
        );
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Supplier SET name=?, nic=?, email=?, contact=?, address=? WHERE supplier_id=?";
        return SQLUtil.execute(sql,
                supplier.getName(),
                supplier.getNic(),
                supplier.getEmail(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.getSupplierId()
        );    }

    @Override
    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Supplier WHERE supplier_id = ?";
        return SQLUtil.execute(sql,supplierId);
    }

    @Override
    public List<Supplier> searchSupplier(String searchText, String searchText1, String searchText2, String searchText3, String searchText4, String searchText5) {
        List<Supplier> supplierList = new ArrayList<>();

        String sql = "SELECT * FROM Supplier WHERE " +
                "supplier_id LIKE ? OR " +
                "name LIKE ? OR " +
                "nic LIKE ? OR " +
                "email LIKE ? OR " +
                "contact LIKE ? OR " +
                "address LIKE ? ";

        try {
            ResultSet rs = SQLUtil.execute(sql,
                    "%" + searchText + "%",
                    "%" + searchText1 + "%",
                    "%" + searchText2 + "%",
                    "%" + searchText3 + "%",
                    "%" + searchText4 + "%",
                    "%" + searchText5 + "%"
            );

            while (rs.next()) {
                supplierList.add(new Supplier(
                        rs.getString("supplier_id"),
                        rs.getString("name"),
                        rs.getString("nic"),
                        rs.getString("contact"),
                        rs.getString("email"),
                        rs.getString("address")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // or use logger
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return supplierList;
    }

    @Override
    public int getSupplierCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM Supplier");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public String getLastSupplierId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT MAX(supplier_id) FROM Supplier");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;    }
}
