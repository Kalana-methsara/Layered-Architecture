package com.lankaice.project.bo.custom;

import com.lankaice.project.dto.RawMaterialsDto;
import com.lankaice.project.entity.RawMaterials;

import java.sql.SQLException;
import java.util.List;

public interface RawMaterialBO {
    List<RawMaterialsDto> getItemsBySupplier(String supplierId) throws SQLException, ClassNotFoundException;

    boolean updateMaterialQtyAfterPurchase(int materialId, int qtyToAdd) throws SQLException, ClassNotFoundException;

    boolean updateMaterialPrice(int materialId, double newPrice) throws SQLException, ClassNotFoundException;
}
