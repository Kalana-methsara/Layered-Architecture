package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.RawMaterialDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.RawMaterialsDto;
import com.lankaice.project.entity.RawMaterials;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RawMaterialDAOImpl implements RawMaterialDAO {

    @Override
    public List<RawMaterials> getItemsBySupplier(String supplierId) throws SQLException, SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Raw_Materials WHERE supplier_id = ?", supplierId);
        List<RawMaterials> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new RawMaterials(
                    rs.getInt("material_id"),
                    rs.getString("supplier_id"),
                    rs.getString("name"),
                    rs.getString("unit_type"),
                    rs.getDouble("unit_cost"),
                    rs.getString("lastUpdate"),
                    rs.getInt("quantity_available")
            ));
        }
        return list;
    }

    @Override
    public boolean updateMaterialQtyAfterPurchase(String materialId, int qtyToAdd) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Raw_Materials SET quantity_available = quantity_available + ?, lastUpdate = CURRENT_TIMESTAMP WHERE material_id = ?";
        return SQLUtil.execute(sql, qtyToAdd, materialId);
    }

    @Override
    public boolean updateMaterialPrice(int materialId, double newPrice) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Raw_Materials SET unit_cost = ?, lastUpdate = CURRENT_TIMESTAMP WHERE material_id = ?";
        return SQLUtil.execute(sql, newPrice, materialId);
    }
}
