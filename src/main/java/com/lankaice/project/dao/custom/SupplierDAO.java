package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier> {
    List<Supplier> searchSupplier(String searchText, String searchText1, String searchText2, String searchText3, String searchText4, String searchText5);

    int getSupplierCount() throws SQLException, ClassNotFoundException;

    String getLastSupplierId() throws SQLException, ClassNotFoundException;
}
