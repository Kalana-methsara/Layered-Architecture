package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.UserDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserBO extends SuperBO {

    UserDto searchUser(String userName, String password) throws SQLException, ClassNotFoundException;

    boolean isOnlyOneUserExists() throws SQLException, ClassNotFoundException;

    List<UserDto> getAllUsers() throws SQLException, ClassNotFoundException;

    boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;

    boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteUser(String userName) throws SQLException, ClassNotFoundException;

    Optional<UserDto> findUserById(String id) throws SQLException, ClassNotFoundException;
}
