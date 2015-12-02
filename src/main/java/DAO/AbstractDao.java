package DAO;

import Controller.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T> {
    protected ConnectionPool connectionPool;
    private static final Logger log = Logger.getLogger(AbstractDao.class);

    public AbstractDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void executeQuery(String sql) {
        try {
            Connection connection = connectionPool.getConnection();
            connection.createStatement().execute(sql);
            connectionPool.putConnection(connection);
            log.info("Запрос: " + sql + " выполнен.");
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
