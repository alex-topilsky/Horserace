package DAO.Horses;

import Controller.ConnectionPool;
import DAO.AbstractDao;
import DAO.Race.RaceBean;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HorsesDao extends AbstractDao<HorsesBean> {
    private static final Logger log = Logger.getLogger(HorsesDao.class);

    public HorsesDao(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public void delete(HorsesBean horse) {
        String sql = "DELETE FROM horses WHERE id_horse=" + horse.getIdHorse();
        executeUpdate(sql);
    }

    @Override
    public HorsesBean getItem(int idHorse) {
        HorsesBean horse = new HorsesBean();
        String sql = "SELECT * FROM horses WHERE id_horse=" + idHorse;

        Connection connection = connectionPool.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            if (rs.next()) {
                horse.setIdHorse(rs.getInt("id_horse"));
                horse.setName(rs.getString("name"));
                horse.setAge(rs.getInt("age"));
                horse.setBread(rs.getString("breed"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            connectionPool.freeConnection(connection);
        }
        return horse;
    }

    @Override
    public List<HorsesBean> getAll() {
        ArrayList<HorsesBean> horses = new ArrayList<>();
        String sql = "SELECT * FROM horses";

        Connection connection = connectionPool.getConnection();
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            HorsesBean horse;
            while (rs.next()) {
                horse = new HorsesBean();
                horse.setIdHorse(rs.getInt("id_horse"));
                horse.setName(rs.getString("name"));
                horse.setAge(rs.getInt("age"));
                horse.setBread(rs.getString("breed"));

                horses.add(horse);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        } finally {
            connectionPool.freeConnection(connection);
        }
        return horses;
    }

    @Override
    public void add(HorsesBean horse) {
        String sql = "INSERT INTO horses(name, age, breed) VALUES ('"
                + horse.getName() + "', '"
                + horse.getAge() + "', '"
                + horse.getBread() + "')";
        execute(sql);
    }

    @Override
    public void edit(HorsesBean horse) {
        String sql = "UPDATE horses SET name='" + horse.getName()
                + "', age='" + horse.getAge()
                + "', breed='" + horse.getBread()
                + "' WHERE id_horse=" + horse.getIdHorse();
        executeUpdate(sql);
    }
}
