package com.lankaice.project.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> extends SuperDAO{
    List<T> getAll() throws SQLException ,ClassNotFoundException;
    boolean save(T t) throws SQLException , ClassNotFoundException;
    boolean update(T t) throws SQLException , ClassNotFoundException;
    boolean delete(String id)  throws SQLException , ClassNotFoundException;
    Optional<T> findById(String id) throws SQLException, ClassNotFoundException;

}
