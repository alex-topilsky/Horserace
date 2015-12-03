package login;

import DAO.FactoryDao;
import DAO.Users.UserBean;
import DAO.Users.UsersDao;

import java.sql.*;

public class CheckUser {
    protected static UserBean getUser(String login, String password) {
        if (login == null || password == null)
            return null;
        UsersDao userDao = new UsersDao(new FactoryDao().getConnectionPool());
        UserBean user = userDao.getUser(login, password);
        if (user != null && user.getIdUser()!=0) {
            return user;
        } else return null;
    }

    protected static boolean isAlreadyUse(String login) {
        if (login == null)
            return false;
        UsersDao userDao = new UsersDao(new FactoryDao().getConnectionPool());
        UserBean user = userDao.getUser(login);
        if (user != null && user.getIdUser()!=0) {
            return true;
        } else return false;
    }

    public static boolean isAdmin(String login, String password) {
        if (login == null || password == null)
            return false;
        UsersDao userDao = new UsersDao(new FactoryDao().getConnectionPool());
        UserBean user = userDao.getUser(login, password);
        if (user != null && user.getRole().equals("admin")) {
            return true;
        } else return false;
    }


    public static boolean isUser(String login, String password) {
        if (login == null || password == null)
            return false;
        UsersDao userDao = new UsersDao(new FactoryDao().getConnectionPool());
        UserBean user = userDao.getUser(login, password);
        if (user != null && user.getRole().equals("user")) {
            return true;
        } else return false;
    }

    public static boolean isBookmaker(String login, String password) {
        if (login == null || password == null)
            return false;
        UsersDao userDao = new UsersDao(new FactoryDao().getConnectionPool());
        UserBean user = userDao.getUser(login, password);
        if (user != null && user.getRole().equals("bookmaker")) {
            return true;
        } else return false;
    }


}