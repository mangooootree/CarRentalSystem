package service;

import domain.User;

import java.util.List;

public interface UserService {
    User findById(Long id) throws ServiceException;

    User findByLoginAndPassword(String login, String password) throws ServiceException;

    List<User> findAll() throws ServiceException;

    Long save(User user) throws ServiceException;

    void update(User user) throws ServiceException;

    void changePassword(Long userId, String oldPassword, String newPassword) ;

    void delete(Long id) throws ServiceException;

    boolean checkLogin(String login) throws ServiceException;


}
