package utils;

import dao.BillDao;
import dao.CarDao;
import dao.OrderDao;
import dao.UserDao;
import dao.mysqlimpl.BillDaoImpl;
import dao.mysqlimpl.CarDaoImpl;
import dao.mysqlimpl.OrderDaoImpl;
import dao.mysqlimpl.UserDaoImpl;
import service.*;

import java.sql.Connection;
import java.sql.SQLException;


public class MainServiceFactoryImpl implements ServiceFactory {
    private Connection connection;

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl(getUserDao());
        return userService;
    }

    @Override
    public CarService getCarService() throws FactoryException {
        CarServiceImpl carService = new CarServiceImpl(getCarDao());
        return carService;
    }

    @Override
    public OrderService getOrderService() throws FactoryException {
        OrderServiceImpl orderService = new OrderServiceImpl(getOrderDao(), getUserService(), getCarService());
        return orderService;
    }

    @Override
    public BillService getBillService() throws FactoryException {
        BillServiceImpl billService = new BillServiceImpl(getBillDao(),getOrderService());
        return billService;
    }

    @Override
    public UserDao getUserDao() throws FactoryException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(getConnection());
        return userDao;
    }

    @Override
    public OrderDao getOrderDao() throws FactoryException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.setConnection(getConnection());
        return orderDao;
    }

    public CarDao getCarDao() throws FactoryException {
        CarDaoImpl carDao = new CarDaoImpl();
        carDao.setConnection(getConnection());
        return carDao;
    }

    public BillDao getBillDao() throws FactoryException {
        BillDaoImpl billDao = new BillDaoImpl();
        billDao.setConnection(getConnection());
        return billDao;
    }

    @Override
    public Connection getConnection() throws FactoryException {
        if(connection == null) {
            try {
                connection = Connector.getConnection();
            } catch(SQLException e) {
                throw new FactoryException(e);
            }
        }
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
            connection = null;
        } catch(Exception e) {}
    }
}
