package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SupplierBO extends SuperBO {

    List<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException;

    boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException;

    Optional<SupplierDto> findSupplierById(String supplierId) throws SQLException, ClassNotFoundException;

    List<SupplierDto> searchSupplier(String searchText,
                                     String searchText1,
                                     String searchText2,
                                     String searchText3,
                                     String searchText4,
                                     String searchText5) throws SQLException, ClassNotFoundException;

    int getSupplierCount() throws SQLException, ClassNotFoundException;

    String getLastSupplierId() throws SQLException, ClassNotFoundException;
}
