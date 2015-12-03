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
                races.setDateRace(rs.getString("date"));
                races.setWinRate(rs.getDouble("win_rate"));
                races.setNameRaces(rs.getString("name_race"));
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
            RacesBean race;
            while (rs.next()) {
                race = new RacesBean();
                race.setIdRaces(rs.getInt("id_races"));
                race.setDateRace(rs.getString("date"));
                race.setWinRate(rs.getDouble("win_rate"));
                race.setNameRaces(rs.getString("name_race"));

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
        String sql = "INSERT INTO races(win_rate, date, name_race) VALUES ('"
                + races.getWinRate() + "', '"
                + races.getDateRace() + "', '"
                + races.getNameRaces() + "')";
        execute(sql);
    }

    @Override
    public void edit(RacesBean races) {
        String sql = "UPDATE races SET win_rate='" + races.getWinRate()+
                "', date='" + races.getDateRace()+
                "', name_race='" + races.getNameRaces() +
                "' WHERE id_races=" + races.getIdRaces();
        executeUpdate(sql);
    }
}
