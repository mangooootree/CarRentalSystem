package dao.mysqlimpl;

import dao.BillDao;
import dao.DaoException;
import domain.Bill;
import domain.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl extends BaseDaoImpl implements BillDao {

    @Override
    public List<Bill> getAllBills() throws DaoException {
        String sql = "SELECT `id`, `order_id`, `price`, `damageDescription`, `paid` FROM `bill`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Bill> bills = new ArrayList<>();
            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setId(resultSet.getLong("id"));
                bill.setOrder(new Order());
                bill.getOrder().setId(resultSet.getLong("order_id"));
                bill.setPrice(resultSet.getLong("price"));
                bill.setDamageDescription(resultSet.getString("damageDescription"));
                bill.setPaid(resultSet.getBoolean("paid"));
                bills.add(bill);
            }
            return bills;
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
    public Long create(Bill bill) throws DaoException {
        String sql = "INSERT INTO `bill` (`order_id`, `price`, `damageDescription`, `paid`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, bill.getOrder().getId());
            statement.setLong(2, bill.getPrice());
            statement.setString(3, bill.getDamageDescription());
            statement.setBoolean(4, bill.isPaid());
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
    public Bill read(Long id) throws DaoException {
        String sql = "SELECT `id`, `order_id`, `price`, `damageDescription`, `paid` FROM `bill` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Bill bill = null;
            if (resultSet.next()) {
                bill = new Bill();
                bill.setId(resultSet.getLong("id"));
                bill.setOrder(new Order());
                bill.getOrder().setId(resultSet.getLong("order_id"));
                bill.setPrice(resultSet.getLong("price"));
                bill.setDamageDescription(resultSet.getString("damageDescription"));
                bill.setPaid(resultSet.getBoolean("paid"));
            }
            return bill;
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
    public void update(Bill bill) throws DaoException {
        String sql = "UPDATE `bill` SET `order_id` = ?, `price` = ?, `damageDescription` = ?, `paid` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, bill.getOrder().getId());
            statement.setLong(2, bill.getPrice());
            statement.setString(3, bill.getDamageDescription());
            statement.setBoolean(4, bill.isPaid());
            statement.setLong(5, bill.getId());
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
        String sql = "DELETE FROM `bill` WHERE `id` = ?";
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
    public void setPaid(Bill bill) throws DaoException {
        String sql = "UPDATE `bill` SET `paid` = ?, WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setBoolean(1, true);
            statement.setLong(2, bill.getId());
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
    public void setUnPaid(Bill bill) throws DaoException {
        String sql = "UPDATE `bill` SET `paid` = ?, WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setBoolean(1, false);
            statement.setLong(2, bill.getId());
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
