package DAO;

import Controller.ConnectionPool;

public class FactoryDao {
    public ConnectionPool getConnectionPool() {
        return ConnectionPool.getInstance("jdbc:mysql://localhost:3306/horse_race", "root", "root", 20);
    }
}
