package service;

import dao.DaoException;
import dao.OrderDao;
import domain.Car;
import domain.Order;
import domain.User;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private UserService userService;
    private CarService carService;

    public OrderServiceImpl(OrderDao orderDao, UserService userService, CarService carService) {
        this.orderDao = orderDao;
        this.userService = userService;
        this.carService = carService;
    }

    public OrderServiceImpl() {
    }


    @Override
    public Order findById(Long id) throws ServiceException {
        try {
            Order order = orderDao.read(id);
            Car car = carService.findById(order.getCar().getId());
            User user = userService.findById(order.getUser().getId());
            order.setCar(car);
            order.setUser(user);
            return order;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        try {
            List<Order> orders = orderDao.getAllOrders();
            for (Order order : orders) {
                Car car = carService.findById(order.getCar().getId());
                User user = userService.findById(order.getUser().getId());
                order.setCar(car);
                order.setUser(user);
            }
            return orders;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public Long save(Order order) throws ServiceException {
        try {
            return orderDao.create(order);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            orderDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void update(Order order) throws ServiceException {
        try {
            orderDao.update(order);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void setConfirmed(Order order) throws ServiceException {
        try {
            orderDao.confirm(order);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void setRejected(Order order) throws ServiceException {
        try {
            orderDao.reject(order);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void setPaid(Order order) throws ServiceException {
        try {
            orderDao.setPaid(order);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void setUnPaid(Order order) throws ServiceException {
        try {
            orderDao.setUnPaid(order);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void SetReviewed(Order order) throws ServiceException {
        try {
            orderDao.setReviewed(order);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

}
