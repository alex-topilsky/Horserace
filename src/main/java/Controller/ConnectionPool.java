package Controller;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class ConnectionPool {
    private String URL;
    private String LOGIN;
    private String PASSWORD;
    private Driver driver;

    private static ConnectionPool instance;

    private PriorityQueue<Connection> readyToUse;
    private ArrayList<Connection> alreadyInUse;

    private static final Logger log = Logger.getLogger(ConnectionPool.class);

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            log.info("JDBC драйвер подключен");
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ConnectionPool(String URL, String LOGIN, String PASSWORD) {
        this.URL = URL;
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;

        try {
            driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            readyToUse = new PriorityQueue<Connection>();
            alreadyInUse = new ArrayList<>();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public static synchronized ConnectionPool getInstance(String url, String user, String password) {
        if (instance == null) {
            instance = new ConnectionPool(url, user, password);
        }
        return instance;
    }

    synchronized public Connection getConnection() {
        if(readyToUse.size()==0)
        {
            try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)){
                alreadyInUse.add(connection);
                log.info("Connection выдан");
                return connection;
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
        Connection connection = readyToUse.poll();
        return connection;
    }

    synchronized public void putConnection(Connection connection) {
        if(connection != null) {
            alreadyInUse.remove(connection);
            readyToUse.add(connection);
            log.info("Connection получен");
        }
    }
}
