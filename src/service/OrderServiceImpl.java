package service;

import dao.CarDao;
import dao.DaoException;
import dao.OrderDao;
import dao.UserDao;
import domain.Car;
import domain.Order;
import domain.User;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private OrderDao orderDao;
    private UserDao userDao;
    private CarDao carDao;

    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, CarDao carDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.carDao = carDao;
    }

    public OrderServiceImpl() {
    }


    @Override
    public Order findById(Long id) throws ServiceException {
        try {
            Order order = orderDao.read(id);
            Car car = carDao.read(order.getCar().getId());
            User user = userDao.read(order.getUser().getId());
            order.setCar(car);
            order.setUser(user);
            return order;
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        try {
            List<Order> orders = orderDao.getAllOrders();
            for (Order order: orders){
                Car car = carDao.read(order.getCar().getId());
                User user = userDao.read(order.getUser().getId());
                order.setCar(car);
                order.setUser(user);
            }
            return orders;
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public Long save(Order order) throws ServiceException {
        try {
            return orderDao.create(order);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            orderDao.delete(id);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void update(Order order) throws ServiceException {
        try {
            orderDao.update(order);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void setConfirmed(Order order) throws ServiceException {
        try {
            orderDao.confirm(order);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void setRejected(Order order) throws ServiceException {
        try {
            orderDao.reject(order);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void setPaid(Order order) throws ServiceException {
        try {
            orderDao.setPaid(order);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void setUnPaid(Order order) throws ServiceException {
        try {
            orderDao.setUnPaid(order);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void SetReviewed(Order order) throws ServiceException {
        try {
            orderDao.setReviewed(order);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

}
