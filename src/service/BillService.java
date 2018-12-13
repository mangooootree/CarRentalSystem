package service;

import domain.Bill;

import java.util.List;

public interface BillService {
    Bill findById(Long id) throws ServiceException;

    List<Bill> findAll() throws ServiceException;

    void save(Bill bill) throws ServiceException;

    void delete(Long id) throws ServiceException;

    void update(Bill bill) throws ServiceException;

    void setPaid(Bill bill) throws ServiceException;
    void setUnPaid(Bill bill) throws ServiceException;
}
