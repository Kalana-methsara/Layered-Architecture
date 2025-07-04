package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    User searchUser(String userName, String password) throws SQLException, ClassNotFoundException;

    boolean isOnlyOneUserExists() throws SQLException, ClassNotFoundException;
}
