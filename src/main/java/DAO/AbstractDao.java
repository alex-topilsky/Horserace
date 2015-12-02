package DAO;

import Controller.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T> {
    protected ConnectionPool connectionPool;
    private static final Logger log = Logger.getLogger(AbstractDao.class);

    public AbstractDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    //Select
    public ResultSet executeQuery(String sql) {
        ResultSet resultSet=null;
        try {
            Connection connection = connectionPool.getConnection();
            resultSet = connection.createStatement().executeQuery(sql);
            connectionPool.freeConnection(connection);
            log.info("Запрос executeQuery: " + sql + " выполнен.");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        finally {
            return resultSet;
        }
    }

    //INSERT, UPDATE или DELETE
    public int executeUpdate(String sql) {
        int result=-1;
        try {
            Connection connection = connectionPool.getConnection();
            result = connection.createStatement().executeUpdate(sql);
            connectionPool.freeConnection(connection);
            log.info("Запрос executeUpdate: " + sql + " выполнен.");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        finally {
            return result;
        }
    }

    //ADD
    public void execute(String sql) {
        try {
            Connection connection = connectionPool.getConnection();
            connection.createStatement().execute(sql);
            connectionPool.freeConnection(connection);
            log.info("Запрос execute: " + sql + " выполнен.");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    abstract public void delete(T object);

    abstract public T getItem(int id);

    abstract public List<T> getAll();

    abstract public void add(T object);

    abstract public void edit(T object);
}
