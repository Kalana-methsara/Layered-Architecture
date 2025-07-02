package com.lankaice.project.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> extends SuperDAO{
    List<T> getAll() throws SQLException;
    String getNextId();
    boolean save(T t);
    boolean update(T t);
    boolean delete(String id);
    List<String> getAllIds();
    Optional<T> findById(String id);
}
