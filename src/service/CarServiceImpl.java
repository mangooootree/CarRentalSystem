package service;

import dao.CarDao;
import dao.DaoException;
import domain.Car;

import java.util.List;

public class CarServiceImpl implements CarService {
    private CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    public CarServiceImpl() {
    }

    @Override
    public Car findById(Long id) throws ServiceException {
        try {
            return carDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public List<Car> findAll() throws ServiceException {
        try {
            return carDao.getAllCars();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public Long save(Car car) throws ServiceException {
        try {
            return carDao.create(car);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            carDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void update(Car car) throws ServiceException {
        try {
            carDao.update(car);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}
