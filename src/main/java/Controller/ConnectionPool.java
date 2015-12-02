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
    private String url;
    private String user;
    private String password;
    private int maxConn;
    private static ConnectionPool instance;
    private ArrayList<Connection> connectionList;

    private static final Logger log = Logger.getLogger(ConnectionPool.class);

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }
    }

    private ConnectionPool(String url, String user, String password, int maxConn) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.maxConn = maxConn;

        connectionList = new ArrayList<Connection>();
        try {
            for (int i = 0; i < maxConn; i++) {
                Connection conn = DriverManager.getConnection(url, user, password);
                connectionList.add(conn);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public static synchronized ConnectionPool getInstance(String url, String user, String password, int maxConn) {
        if (instance == null) {
            instance = new ConnectionPool(url, user, password, maxConn);
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection conn = null;
        if (!connectionList.isEmpty()) {
            conn = connectionList.get(connectionList.size() - 1);
            connectionList.remove(conn);

            try {
                if (conn.isClosed()) {
                    conn = getConnection();
                }
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        } else {
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
        return conn;
    }

    public synchronized void freeConnection(Connection conn) {
        if ((conn != null) && (connectionList.size() < maxConn)) {
            connectionList.add(conn);
        } else {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }

    public synchronized void removeAll() {
        for (Connection conn : connectionList) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
            connectionList.clear();
        }
    }
}
