package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.RawMaterialBO;
import com.lankaice.project.bo.util.EntityDTOConverter;
import com.lankaice.project.dao.custom.RawMaterialDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.RawMaterialsDto;
import com.lankaice.project.entity.RawMaterials;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RawMaterialBOImpl implements RawMaterialBO {

    private final RawMaterialDAO rawMaterialDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.RAW_MATERIAL);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<RawMaterialsDto> getItemsBySupplier(String supplierId) throws SQLException, ClassNotFoundException {
        List<RawMaterials> entities = rawMaterialDAO.getItemsBySupplier(supplierId);
        List<RawMaterialsDto> dtos = new ArrayList<>();

        for (RawMaterials entity : entities) {
            dtos.add(converter.getRawMaterialsDto(entity));
        }

        return dtos;
    }


    @Override
    public boolean updateMaterialQtyAfterPurchase(int materialId, int qtyToAdd) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.updateMaterialQtyAfterPurchase(String.valueOf(materialId), qtyToAdd);
    }


    @Override
    public boolean updateMaterialPrice(int materialId, double newPrice) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.updateMaterialPrice(materialId, newPrice);
    }
}
