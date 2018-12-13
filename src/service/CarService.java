package service;

import domain.Car;

import java.util.List;

public interface CarService {
    Car findById(Long id) throws ServiceException;

    List<Car> findAll() throws ServiceException;

    void save(Car car) throws ServiceException;

    void delete(Long id) throws ServiceException;

    void update(Car car) throws ServiceException;
}
