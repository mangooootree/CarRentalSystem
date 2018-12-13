package service;

import domain.Order;

import java.util.List;

public interface OrderService {
    Order findById(Long id) throws ServiceException;

    List<Order> findAll() throws ServiceException;

    Long save(Order order) throws ServiceException;

    void delete(Long id) throws ServiceException;

    void update(Order order) throws ServiceException;

    void setConfirmed(Order order) throws ServiceException;
    void setRejected(Order order) throws ServiceException;

    void setPaid(Order order) throws ServiceException;
    void setUnPaid(Order order) throws ServiceException;
    void SetReviewed (Order order)  throws ServiceException;
}
