package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.UserDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public User searchUser(String userName, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM User WHERE userName = ? AND password = ?";
        ResultSet resultSet = SQLUtil.execute(sql, userName, password);

        if (resultSet.next()) {
            return new User(
                    resultSet.getString("userName"),
                    resultSet.getString("password"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("role")
            );
        }
        return null;
    }

    @Override
    public boolean isOnlyOneUserExists() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS count FROM User";
        ResultSet resultSet = SQLUtil.execute(sql);

        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            return count == 1;
        }
        return false;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM User");
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User(
                    resultSet.getString("userName"),
                    resultSet.getString("password"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("role")
            );
            users.add(user);
        }
        return users;    }

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO User (userName, password, name, email, role) VALUES (?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE User SET password = ?, name = ?, email = ?, role = ? WHERE userName = ?";
        return SQLUtil.execute(sql,
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getUsername()
        );
    }

    @Override
    public boolean delete(String userName) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM User WHERE userName = ?";
        return SQLUtil.execute(sql, userName);
    }
}
