package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.UserBO;
import com.lankaice.project.dao.custom.UserDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.UserDto;
import com.lankaice.project.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = (UserDAO) DAOFactoryImpl.getInstance().getDAO(DAOType.USER);

    @Override
    public UserDto searchUser(String userName, String password) throws SQLException, ClassNotFoundException {
        User user = userDAO.searchUser(userName, password);
        if (user != null) {
            return new UserDto(
                    user.getUsername(),
                    user.getPassword(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole()
            );
        }
        return null;
    }

    @Override
    public boolean isOnlyOneUserExists() throws SQLException, ClassNotFoundException {
        return userDAO.isOnlyOneUserExists();
    }

    @Override
    public List<UserDto> getAllUsers() throws SQLException, ClassNotFoundException {
        List<User> entityList = userDAO.getAll();
        List<UserDto> dtoList = new ArrayList<>();
        for (User user : entityList) {
            dtoList.add(new UserDto(
                    user.getUsername(),
                    user.getPassword(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole()
            ));
        }
        return dtoList;
    }

    @Override
    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(
                dto.getUsername(),
                dto.getPassword(),
                dto.getName(),
                dto.getEmail(),
                dto.getRole()
        ));
    }

    @Override
    public boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(
                dto.getUsername(),
                dto.getPassword(),
                dto.getName(),
                dto.getEmail(),
                dto.getRole()
        ));
    }

    @Override
    public boolean deleteUser(String userName) throws SQLException, ClassNotFoundException {
        return userDAO.delete(userName);
    }

    @Override
    public Optional<UserDto> findUserById(String id) throws SQLException, ClassNotFoundException {
        Optional<User> entity = userDAO.findById(id);
        if (entity.isPresent()) {
            User user = entity.get();
            return Optional.of(new UserDto(
                    user.getUsername(),
                    user.getPassword(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole()
            ));
        }
        return Optional.empty();
    }
}
