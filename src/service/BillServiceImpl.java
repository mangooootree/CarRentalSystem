package service;

import dao.BillDao;
import dao.DaoException;
import domain.Bill;
import domain.Order;

import java.util.List;

public class BillServiceImpl implements BillService {
    private BillDao billDao;
    private OrderService orderService;

    public BillServiceImpl(BillDao billDao, OrderService orderService) {
        this.billDao = billDao;
        this.orderService = orderService;
    }

    public BillServiceImpl() {
    }

    @Override
    public Bill findById(Long id) throws ServiceException {
        try {
            Bill bill = billDao.read(id);
            Order order = orderService.findById(bill.getOrder().getId());
            bill.setOrder(order);
            return bill;

        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public List<Bill> findAll() throws ServiceException {
        try {
            List<Bill> bills = billDao.getAllBills();
            for (Bill bill: bills){
                Order order = orderService.findById(bill.getOrder().getId());
                bill.setOrder(order);
            }
            return bills;
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void save(Bill bill) throws ServiceException {
        try {
            billDao.create(bill);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            billDao.delete(id);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void update(Bill bill) throws ServiceException {
        try {
            billDao.update(bill);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void setPaid(Bill bill) throws ServiceException {
        try {
            billDao.setPaid(bill);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void setUnPaid(Bill bill) throws ServiceException {
        try {
            billDao.setUnPaid(bill);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }
}
