package dao;

import domain.Car;

import java.util.List;

public interface CarDao extends Dao<Car>{
    List<Car> getAllCars() throws DaoException;
}
