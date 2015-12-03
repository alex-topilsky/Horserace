package DAO.Users;

import Controller.ConnectionPool;
import DAO.AbstractDao;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends AbstractDao<UserBean> {
    private static final Logger log = Logger.getLogger(UsersDao.class);

    public UsersDao(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public void delete(UserBean user) {
        String sql = "DELETE * FROM users WHERE id_user=" + user.getIdUser();
        executeQuery(sql);
    }

    @Override
    public UserBean getItem(int idUser) {
        UserBean user = new UserBean();
        String sql = "SELECT * FROM users WHERE id_user=" + idUser;
        Connection connection = connectionPool.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            if (rs.next()) {
                user.setIdUser(rs.getInt("id_user"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setBalance(rs.getDouble("balance"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
           log.error(e.getMessage());
        } finally {
            connectionPool.freeConnection(connection);
        }
        return user;
    }

    public UserBean getUser(String login, String password) {
        UserBean user = new UserBean();

        String sql = "select * from users where login = ? and password = ?";
        int result = 0;

        try (Connection connect = connectionPool.getConnection();
             PreparedStatement prepareStatement = connect.prepareStatement(sql)) {
            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);

            ResultSet rs = prepareStatement.executeQuery();
            if (rs.next()) {
                user.setIdUser(rs.getInt("id_user"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setBalance(rs.getDouble("balance"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public UserBean getUser(String login) {
        UserBean user = new UserBean();

        String sql = "select * from users where login = ?";
        int result = 0;

        try (Connection connect = connectionPool.getConnection();
             PreparedStatement prepareStatement = connect.prepareStatement(sql)) {
            prepareStatement.setString(1, login);

            ResultSet rs = prepareStatement.executeQuery();
            if (rs.next()) {
                user.setIdUser(rs.getInt("id_user"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setBalance(rs.getDouble("balance"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<UserBean> getAll() {
        ArrayList<UserBean> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        Connection connection = connectionPool.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            UserBean user;
            while (rs.next()) {
                user = new UserBean();
                user.setIdUser(rs.getInt("id_user"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setBalance(rs.getDouble("balance"));
                user.setRole(rs.getString("role"));

                users.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return users;
    }

    @Override
    public void add(UserBean user) {
        String sql = "INSERT INTO users(name, login, password, balance, role) VALUES('"
                + user.getName() + "', '"
                + user.getLogin() + "', '"
                + user.getPassword() + "', '"
                + user.getBalance() + "', '"
                + user.getRole() + "')";
        execute(sql);
    }

    @Override
    public void edit(UserBean user) {
        String sql = "UPDATE users SET name='" + user.getName()+
                "', login='" + user.getLogin()+
                "', password='" + user.getPassword()+
                "', balance='" + user.getBalance()+
                "', role='" + user.getRole()+
                "' WHERE id_user=" + user.getIdUser();
        executeUpdate(sql);
    }
}
