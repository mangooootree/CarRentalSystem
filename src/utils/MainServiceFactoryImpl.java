package utils;

import dao.BillDao;
import dao.CarDao;
import dao.OrderDao;
import dao.UserDao;
import dao.mysqlimpl.BillDaoImpl;
import dao.mysqlimpl.CarDaoImpl;
import dao.mysqlimpl.OrderDaoImpl;
import dao.mysqlimpl.UserDaoImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.*;

import java.sql.Connection;
import java.sql.SQLException;


public class MainServiceFactoryImpl implements ServiceFactory {
    private Connection connection;
    private static final Logger log = LogManager.getLogger(MainServiceFactoryImpl.class.getName());

    @Override
    public UserService getUserService() throws FactoryException {
        return new UserServiceImpl(getUserDao());
    }

    @Override
    public CarService getCarService() throws FactoryException {
        return new CarServiceImpl(getCarDao());
    }

    @Override
    public OrderService getOrderService() throws FactoryException {
        return new OrderServiceImpl(getOrderDao(), getUserService(), getCarService());
    }

    @Override
    public BillService getBillService() throws FactoryException {
        return new BillServiceImpl(getBillDao(), getOrderService());
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
        if (connection == null) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch (PoolException e) {
                throw new FactoryException();
            }
        }
        return connection;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            log.debug("Realizing connection");
            ConnectionPool.getInstance().freeConnection(connection);
            connection = null;
        }
    }
}
