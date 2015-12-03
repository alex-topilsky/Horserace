package PersonalArea;

import DAO.FactoryDao;
import DAO.Horses.HorsesBean;
import DAO.Race.RaceBean;
import DAO.Race.RaceDao;
import DAO.Races.RacesBean;
import DAO.Races.RacesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/adminRaceInfo")
public class AdminRaceInfo  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminRaceInfo() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("HorseAdd") != null) {
            RaceDao raceDao = new RaceDao(new FactoryDao().getConnectionPool());
            int id_horse = Integer.parseInt(request.getParameter("HorseAdd"));
            int id_races = Integer.parseInt(request.getParameter("NumberRace"));

            RaceBean raceBean = new RaceBean(0, id_races,id_horse, null);
            raceDao.add(raceBean);
        }

        if (request.getParameter("doWin") != null) {
            RaceDao raceDao = new RaceDao(new FactoryDao().getConnectionPool());
            RaceBean race = raceDao.getItem(Integer.parseInt(request.getParameter("doWin")));
            race.setWinner("Win");
            raceDao.edit(race);

            int id_rases=race.getIdRaces();
            RacesDao racesDao = new RacesDao(new FactoryDao().getConnectionPool());
            RacesBean races = racesDao.getItem(id_rases);
            races.setDone("done");
            racesDao.edit(races);

            HashMap<String, String> map = new HashMap<String, String>();

        }

        request.getRequestDispatcher("/raceinfo").forward(request, response);
    }
}