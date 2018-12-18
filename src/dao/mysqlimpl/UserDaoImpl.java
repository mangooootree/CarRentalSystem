package dao.mysqlimpl;

import dao.DaoException;
import dao.UserDao;
import domain.Role;
import domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
    public List<User> getAllUsers() throws DaoException {
        String sql = "SELECT `id`, `firstname`, `lastname`, `passport`, `login`, `password`, `role` FROM `user`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setPassport(resultSet.getString("passport"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                users.add(user);
            }
            return users;
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
    public Long create(User user) throws DaoException {
        String sql = "INSERT INTO `user` (`firstname`, `lastname`, `passport`, `login`, `password`, `role`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getPassport());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getRole().ordinal());
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
    public User read(Long id) throws DaoException {
        String sql = "SELECT `id`, `firstname`, `lastname`, `passport`, `login`, `password`, `role` FROM `user` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setPassport(resultSet.getString("passport"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
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
    public void update(User user) throws DaoException {
        String sql = "UPDATE `user` SET `firstname` = ?, `lastname` = ?, `passport` = ?, `login` = ?, `password` = ?, `role` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getPassport());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getRole().ordinal());
            statement.setLong(7, user.getId());
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
        String sql = "DELETE FROM `user` WHERE `id` = ?";
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

    public boolean checkLogin(String login) throws DaoException {
        String sql = "SELECT * FROM USER WHERE LOGIN = ?;";
        PreparedStatement statement = null;
        ResultSet rs;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            rs = statement.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            throw new DaoException();
        } finally {
            try {
                if (statement != null){ statement.close();}
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public User readByLoginAndPassword(String login, String password) throws DaoException {
        String sql = "SELECT `id`, `firstname`, `lastname`, `passport`, `role` FROM `user` WHERE `login` = ? AND `password`=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setPassport(resultSet.getString("passport"));
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
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
}
