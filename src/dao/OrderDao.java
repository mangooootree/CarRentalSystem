package dao;

import domain.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> getAllOrders() throws DaoException;
    void confirm(Order order) throws DaoException;
    void reject(Order order) throws DaoException;
    void setPaid(Order order) throws DaoException;
    void setUnPaid(Order order) throws DaoException;
    void setReviewed(Order order) throws DaoException;
}
