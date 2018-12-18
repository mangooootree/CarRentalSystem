package dao.mysqlimpl;

import dao.CarDao;
import dao.DaoException;
import domain.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl extends BaseDaoImpl implements CarDao {

    @Override
    public List<Car> getAllCars() throws DaoException {
        String sql = "SELECT `id`, `model`, `color`, `licenceplate`, `isAutomaticTransmission`, `isOrdered`, `price` FROM `CAR`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getLong("id"));
                car.setModel(resultSet.getString("model"));
                car.setColor(resultSet.getString("color"));
                car.setLicencePlate(resultSet.getString("licenceplate"));
                car.setAutomaticTransmission((resultSet.getBoolean("isAutomaticTransmission")));
                car.setOrdered(resultSet.getBoolean("isOrdered"));
                car.setPrice(resultSet.getLong("price"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                if (statement != null){ statement.close();}
                if (resultSet != null){resultSet.close();}
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Long create(Car car) throws DaoException {
        String sql = "INSERT INTO `car` (`model`, `color`, `licenceplate`, `isAutomaticTransmission`, `isOrdered`, `price`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, car.getModel());
            statement.setString(2, car.getColor());
            statement.setString(3, car.getLicencePlate());
            statement.setBoolean(4, car.isAutomaticTransmission());
            statement.setBoolean(5, car.isOrdered());
            statement.setLong(6, car.getPrice());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                if (statement != null){ statement.close();}
                if (resultSet != null){resultSet.close();}
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Car read(Long id) throws DaoException {
        String sql = "SELECT `id`, `model`, `color`, `licenceplate`, `isAutomaticTransmission`, `isOrdered`, `price` FROM `car` WHERE ID = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Car car = null;
            if (resultSet.next()) {
                car = new Car();
                car.setId(resultSet.getLong("id"));
                car.setModel(resultSet.getString("model"));
                car.setColor(resultSet.getString("color"));
                car.setLicencePlate(resultSet.getString("licenceplate"));
                car.setAutomaticTransmission((resultSet.getBoolean("isAutomaticTransmission")));
                car.setOrdered(resultSet.getBoolean("isOrdered"));
                car.setPrice(resultSet.getLong("price"));
            }
            return car;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                if (statement != null){ statement.close();}
                if (resultSet != null){resultSet.close();}
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update(Car car) throws DaoException {
        String sql = "UPDATE `car` SET `model` = ?, `color` = ?, `licenceplate` = ?, `isAutomaticTransmission` = ?, `isOrdered` = ?, `price` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, car.getModel());
            statement.setString(2, car.getColor());
            statement.setString(3, car.getLicencePlate());
            statement.setBoolean(4, car.isAutomaticTransmission());
            statement.setBoolean(5, car.isOrdered());
            statement.setLong(6, car.getPrice());
            statement.setLong(7, car.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                if (statement != null){ statement.close();}
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `car` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                if (statement != null){ statement.close();}
            } catch (Exception e) {
            }
        }
    }
}
