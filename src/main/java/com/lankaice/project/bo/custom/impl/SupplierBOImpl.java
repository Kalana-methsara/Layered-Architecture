package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.SupplierBO;
import com.lankaice.project.bo.util.EntityDTOConverter;
import com.lankaice.project.dao.custom.SupplierDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.SupplierDto;
import com.lankaice.project.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierBOImpl implements SupplierBO {

    private final SupplierDAO supplierDAO =
            DAOFactoryImpl.getInstance().getDAO(DAOType.SUPPLIER);

    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        List<Supplier> entityList = supplierDAO.getAll();
        List<SupplierDto> dtoList = new ArrayList<>();
        for (Supplier supplier : entityList) {
            dtoList.add(converter.getSupplierDto(supplier));
        }
        return dtoList;
    }

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(converter.getSupplier(dto));
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(converter.getSupplier(dto));
    }

    @Override
    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supplierId);
    }

    @Override
    public Optional<SupplierDto> findSupplierById(String supplierId) throws SQLException, ClassNotFoundException {
        Optional<Supplier> supplierOpt = supplierDAO.findById(supplierId);
        return supplierOpt.map(converter::getSupplierDto);
    }

    @Override
    public List<SupplierDto> searchSupplier(String searchText,
                                            String searchText1,
                                            String searchText2,
                                            String searchText3,
                                            String searchText4,
                                            String searchText5) throws SQLException, ClassNotFoundException {
        List<Supplier> entityList = supplierDAO.searchSupplier(searchText, searchText1, searchText2, searchText3, searchText4, searchText5);
        List<SupplierDto> dtoList = new ArrayList<>();
        for (Supplier supplier : entityList) {
            dtoList.add(converter.getSupplierDto(supplier));
        }
        return dtoList;
    }

    @Override
    public int getSupplierCount() throws SQLException, ClassNotFoundException {
        return supplierDAO.getSupplierCount();
    }

    @Override
    public String getLastSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getLastSupplierId();
    }
}
