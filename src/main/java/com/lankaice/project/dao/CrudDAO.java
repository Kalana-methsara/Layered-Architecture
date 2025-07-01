package com.lankaice.project.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> {
    List<T> getAll();
    String getNextId();
    boolean save(T t);
    boolean update(T t);
    boolean delete(String id);
    List<String> getAllIds();
    Optional<T> findById(String id);
}
