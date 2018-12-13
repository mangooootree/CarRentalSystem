package dao.mysqlimpl;

import dao.DaoException;
import dao.OrderDao;
import domain.Car;
import domain.Order;
import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

    @Override
    public List<Order> getAllOrders() throws DaoException {
        String sql = "SELECT `id`, `user_id`, `car_id`, `date`, `amountOfDays`, `totalCost`, `isPaid`, `reviewed`, `isAccepted`, `comments` FROM `order`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setUser(new User());
                order.getUser().setId(resultSet.getLong("user_id"));
                order.setCar(new Car());
                order.getCar().setId(resultSet.getLong("car_id"));
                order.setDate(new java.util.Date(resultSet.getTimestamp("date").getTime()));
                order.setAmountOfDays(resultSet.getInt("amountOfDays"));
                order.setTotalCost(resultSet.getLong("totalCost"));
                order.setPaid(resultSet.getBoolean("isPaid"));
                order.setReviewed(resultSet.getBoolean("reviewed"));
                order.setAccepted(resultSet.getBoolean("isAccepted"));
                order.setComments(resultSet.getString("comments"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Long create(Order order) throws DaoException {
        String sql = "INSERT INTO `order` (`user_id`, `car_id`, `date`, `amountOfDays`, `totalCost`, `isPaid`, `reviewed`, `isAccepted`, `comments`) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUser().getId());
            statement.setLong(2, order.getCar().getId());
            statement.setTimestamp(3, new Timestamp(order.getDate().getTime()));
            statement.setInt(4, order.getAmountOfDays());
            statement.setLong(5, order.getTotalCost());
            statement.setBoolean(6, order.isPaid());
            statement.setBoolean(7, order.isReviewed());
            statement.setBoolean(8, order.isAccepted());
            statement.setString(9, order.getComments());
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
                statement.close();
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Order read(Long id) throws DaoException {
        String sql = "SELECT `id`,`user_id`, `car_id`, `date`, `amountOfDays`, `totalCost`, `isPaid`, `reviewed`, `isAccepted`, `comments` FROM `order` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setUser(new User());
                order.getUser().setId(resultSet.getLong("user_id"));
                order.setCar(new Car());
                order.getCar().setId(resultSet.getLong("car_id"));
                order.setDate(new java.util.Date(resultSet.getTimestamp("date").getTime()));
                order.setAmountOfDays(resultSet.getInt("amountOfDays"));
                order.setTotalCost(resultSet.getLong("totalCost"));
                order.setPaid(resultSet.getBoolean("isPaid"));
                order.setReviewed(resultSet.getBoolean("reviewed"));
                order.setAccepted(resultSet.getBoolean("isAccepted"));
                order.setComments(resultSet.getString("comments"));
            }
            return order;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update(Order order) throws DaoException {
        String sql = "UPDATE `order` SET `user_id` = ?, `car_id` = ?, `date` = ?, `amountOfDays` = ?, `totalCost` = ?, `isPaid` = ?, `isAccepted` = ?, `reviewed` = ?, `comments`=? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, order.getUser().getId());
            statement.setLong(2, order.getCar().getId());
            statement.setTimestamp(3, new Timestamp(order.getDate().getTime()));
            statement.setInt(4, order.getAmountOfDays());
            statement.setLong(5, order.getTotalCost());
            statement.setBoolean(6, order.isPaid());
            statement.setBoolean(7, order.isAccepted());
            statement.setBoolean(8, order.isReviewed());
            statement.setString(9, order.getComments());
            statement.setLong(10, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }

    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `order` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void confirm(Order order) throws DaoException {
        String sql = "UPDATE `order` SET `isAccepted` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setBoolean(1, true);
            statement.setLong(2, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void reject(Order order) throws DaoException {
        String sql = "UPDATE `order` SET `isAccepted` = ?, `comments`= ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setBoolean(1, false);
            statement.setString(2, order.getComments());
            statement.setLong(3, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void setPaid(Order order) throws DaoException {
        String sql = "UPDATE `order` SET `isPaid` = ?, WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setBoolean(1, true);
            statement.setLong(2, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void setUnPaid(Order order) throws DaoException {
        String sql = "UPDATE `order` SET `isPaid` = ?, WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setBoolean(1, false);
            statement.setLong(2, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void setReviewed(Order order) throws DaoException {
        String sql = "UPDATE `order` SET `reviewed` = ?, WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setBoolean(1, true);
            statement.setLong(2, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }
}
