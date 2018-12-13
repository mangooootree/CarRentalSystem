package utils;

import dao.BillDao;
import dao.CarDao;
import dao.OrderDao;
import dao.UserDao;
import service.BillService;
import service.CarService;
import service.OrderService;
import service.UserService;

import java.sql.Connection;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService() throws FactoryException;
    CarService getCarService() throws FactoryException;
    OrderService getOrderService() throws FactoryException;
    BillService getBillService() throws FactoryException;

    UserDao getUserDao() throws FactoryException;
    CarDao getCarDao() throws FactoryException;
    OrderDao getOrderDao() throws FactoryException;
    BillDao getBillDao() throws FactoryException;

    Connection getConnection() throws FactoryException;
}
