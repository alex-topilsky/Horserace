package DAO.Bet;

import Controller.ConnectionPool;
import DAO.AbstractDao;
import DAO.Horses.HorsesBean;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BetDao extends AbstractDao<BetBean> {
    private static final Logger log = Logger.getLogger(BetDao.class);

    public BetDao(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public void delete(BetBean bet) {
        String sql = "DELETE * FROM bet WHERE id_bet=" + bet.getIdBet();
        executeQuery(sql);
    }

    @Override
    public BetBean getItem(int idBet) {
        BetBean bet = new BetBean();
        String sql = "SELECT * FROM bet WHERE id_bet=" + idBet;

        Connection connection = connectionPool.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            if (rs.next()) {
                bet.setIdBet(rs.getInt("id_bet"));
                bet.setIdRace(rs.getInt("id_race"));
                bet.setIdUser(rs.getInt("id_user"));
                bet.setRate(rs.getDouble("rate"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            connectionPool.freeConnection(connection);
        }
        return bet;
    }

    @Override
    public List<BetBean> getAll() {
        ArrayList<BetBean> bets = new ArrayList<>();
        String sql = "SELECT * FROM bet";

        Connection connection = connectionPool.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            BetBean bet;
            while (rs.next()) {
                bet = new BetBean();
                bet.setIdBet(rs.getInt("id_bet"));
                bet.setIdRace(rs.getInt("id_race"));
                bet.setIdUser(rs.getInt("id_user"));
                bet.setRate(rs.getDouble("rate"));

                bets.add(bet);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return bets;
    }

    public List<BetBean> getAll(int idUser) {
        ArrayList<BetBean> bets = new ArrayList<>();
        String sql = "SELECT * FROM bet WHERE id_user="+idUser;

        Connection connection = connectionPool.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            BetBean bet;
            while (rs.next()) {
                bet = new BetBean();
                bet.setIdBet(rs.getInt("id_bet"));
                bet.setIdRace(rs.getInt("id_race"));
                bet.setIdUser(rs.getInt("id_user"));
                bet.setRate(rs.getDouble("rate"));

                bets.add(bet);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return bets;
    }

    @Override
    public void add(BetBean bet) {
        String sql = "INSERT INTO bet VALUES ("
                + bet.getIdRace() + ", "
                + bet.getIdUser() + ", "
                + bet.getRate() + ")";
        executeQuery(sql);
    }

    @Override
    public void edit(BetBean bet) {
        String sql = "UPDATE bet SET id_race=" + bet.getIdRace()
                + ", id_user=" + bet.getIdUser()
                + ", rate=" + bet.getRate()
                + "WHERE id_bet=" + bet.getIdBet();
        executeQuery(sql);
    }
}
