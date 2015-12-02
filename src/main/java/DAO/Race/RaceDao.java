package DAO.Race;

import Controller.ConnectionPool;
import DAO.AbstractDao;
import DAO.Races.RacesBean;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaceDao extends AbstractDao<RaceBean> {
    private static final Logger log = Logger.getLogger(RaceDao.class);

    public RaceDao(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public void delete(RaceBean race) {
        String sql = "DELETE * FROM race WHERE id_race=" + race.getIdRace();
        executeQuery(sql);
    }

    @Override
    public RaceBean getItem(int idRace) {
        RaceBean race = new RaceBean();
        String sql = "SELECT * FROM race WHERE id_race=" + idRace;
        Connection connection = connectionPool.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            if (rs.next()) {
                race.setIdRace(rs.getInt("id_race"));
                race.setIdRaces(rs.getInt("id_races"));
                race.setIdHorse(rs.getInt("id_horse"));
                race.setWinner(rs.getBoolean("winner"));

            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            connectionPool.freeConnection(connection);
        }
        return race;
    }

    @Override
    public List<RaceBean> getAll() {
        ArrayList<RaceBean> race = new ArrayList<>();
        String sql = "SELECT * FROM race";

        Connection connection = connectionPool.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            RaceBean raceBean = new RaceBean();
            while (rs.next()) {
                raceBean.setIdRace(rs.getInt("id_race"));
                raceBean.setIdRaces(rs.getInt("id_races"));
                raceBean.setIdHorse(rs.getInt("id_horse"));
                raceBean.setWinner(rs.getBoolean("winner"));

                race.add(raceBean);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return race;
    }

    @Override
    public void add(RaceBean race) {
        String sql = "INSERT INTO race VALUES ("
                + race.getIdRaces() + ", "
                + race.getIdHorse() + ", "
                + race.getWinner() + ")";
        executeQuery(sql);
    }

    @Override
    public void edit(RaceBean race) {
        String sql = "UPDATE race SET id_races=" + race.getIdRaces()
                + ", id_horse=" + race.getIdHorse()
                + ", winner=" + race.getWinner()
                + "WHERE id_race=" + race.getIdRace();
        executeQuery(sql);
    }
}
