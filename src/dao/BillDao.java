package dao;

import domain.Bill;

import java.util.List;

public interface BillDao extends Dao<Bill> {
    List<Bill> getAllBills()throws DaoException;
    void setPaid(Bill bill) throws DaoException;
    void setUnPaid(Bill bill) throws DaoException;
}
