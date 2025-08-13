package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.RawMaterials;
import com.lankaice.project.entity.Salary;

import java.sql.SQLException;
import java.util.List;

public interface RawMaterialDAO extends CrudDAO<RawMaterials> {
    List<RawMaterials> getItemsBySupplier(String supplierId) throws SQLException, SQLException, ClassNotFoundException;

    boolean updateMaterialQtyAfterPurchase(String materialId, int qtyToAdd) throws SQLException, ClassNotFoundException;

    boolean updateMaterialPrice(int materialId, double newPrice) throws SQLException, ClassNotFoundException;
}
