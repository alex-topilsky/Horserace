package DAO.Races;

import Controller.ConnectionPool;
import DAO.AbstractDao;
import DAO.Users.UserBean;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RacesDao extends AbstractDao<RacesBean>{
    private static final Logger log = Logger.getLogger(RacesDao.class);

    public RacesDao(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public void delete(RacesBean races) {
        String sql = "DELETE * FROM races WHERE id_races=" + races.getIdRaces();
        executeQuery(sql);
    }

    @Override
    public RacesBean getItem(int idRaces) {
        RacesBean races = new RacesBean();
        String sql = "SELECT * FROM races WHERE id_races=" + idRaces;
        Connection connection = connectionPool.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            if (rs.next()) {
                races.setIdRaces(rs.getInt("id_races"));
                races.setDateRace(rs.getDate("date"));
                races.setWinRate(rs.getDouble("win_rate"));

            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            connectionPool.freeConnection(connection);
        }
        return races;
    }

    @Override
    public List<RacesBean> getAll() {
        ArrayList<RacesBean> races = new ArrayList<>();
        String sql = "SELECT * FROM races";

        Connection connection = connectionPool.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            RacesBean race = new RacesBean();
            while (rs.next()) {
                race.setIdRaces(rs.getInt("id_races"));
                race.setDateRace(rs.getDate("date"));
                race.setWinRate(rs.getDouble("win_rate"));

                races.add(race);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return races;
    }

    @Override
    public void add(RacesBean races) {
        String sql = "INSERT INTO races VALUES ("
                + races.getDateRace() + ", "
                + races.getWinRate() + ")";
        executeQuery(sql);
    }

    @Override
    public void edit(RacesBean races) {
        String sql = "UPDATE races SET win_rate=" + races.getWinRate()+
                ", date=" + races.getDateRace()+
                "WHERE id_races=" + races.getIdRaces();
        executeQuery(sql);
    }
}
