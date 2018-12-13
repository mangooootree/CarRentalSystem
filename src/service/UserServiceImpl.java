package service;

import dao.DaoException;
import dao.UserDao;
import domain.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl() {
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.getAllUsers();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            userDao.create(user);
        } catch (DaoException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (DaoException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        //todo changePassword
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public boolean checkLogin(String login) throws ServiceException {
        try {
            return userDao.checkLogin(login);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}
