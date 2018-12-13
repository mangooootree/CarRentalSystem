package service;

import dao.BillDao;
import dao.DaoException;
import domain.Bill;

import java.util.List;

public class BillServiceImpl implements BillService {
    private BillDao billDao;

    public BillServiceImpl(BillDao billDao) {
        this.billDao = billDao;
    }

    public BillServiceImpl() {
    }

    @Override
    public Bill findById(Long id) throws ServiceException {
        try {
            return billDao.read(id);
        }
        catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public List<Bill> findAll() throws ServiceException {
        try {
            return billDao.getAllBills();
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
