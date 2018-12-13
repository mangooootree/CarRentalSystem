package dao;

import domain.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    List<User> getAllUsers() throws DaoException;
    boolean checkLogin(String login) throws DaoException;
    User readByLoginAndPassword(String login, String password) throws DaoException;

}

