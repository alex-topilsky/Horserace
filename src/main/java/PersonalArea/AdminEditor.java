package PersonalArea;

import DAO.FactoryDao;
import DAO.Race.RaceBean;
import DAO.Race.RaceDao;
import DAO.Races.RacesBean;
import DAO.Races.RacesDao;
import DAO.Users.UserBean;
import login.CheckUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet("/adminEditor")
public class AdminEditor  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminEditor() {
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

            if (request.getParameter("dateEdit") != null) {
                RacesDao racesDao = new RacesDao(new FactoryDao().getConnectionPool());
                RacesBean races = (RacesBean) racesDao.getItem(Integer.parseInt(request.getParameter("dateEdit")));

                races.setDateRace(request.getParameter("dateValue"));
                racesDao.edit(races);
            }
        if (request.getParameter("nameRaceEdit") != null) {
            RacesDao racesDao = new RacesDao(new FactoryDao().getConnectionPool());
            RacesBean races = (RacesBean) racesDao.getItem(Integer.parseInt(request.getParameter("nameRaceEdit")));

            races.setNameRaces(request.getParameter("nameRace"));
            racesDao.edit(races);
        }
//        if (request.getParameter("winRateEdit") != null) {
//            RacesDao racesDao = new RacesDao(new FactoryDao().getConnectionPool());
//            RacesBean races = (RacesBean) racesDao.getItem(Integer.parseInt(request.getParameter("winRateEdit")));
//
//            races.setWinRate(Double.parseDouble(request.getParameter("winRate")));
//            racesDao.edit(races);
//        }

        if (request.getParameter("NewRace") != null) {
            RacesDao racesDao = new RacesDao(new FactoryDao().getConnectionPool());
            RacesBean races = new RacesBean(0, 0,
                    request.getParameter("AddDateValue"),
                    request.getParameter("AddNameRace"), "expected");
            racesDao.add(races);
        }

        if (request.getParameter("DeleteHorserace") != null) {
            RacesDao racesDao = new RacesDao(new FactoryDao().getConnectionPool());
            RacesBean races = (RacesBean) racesDao.getItem(Integer.parseInt(request.getParameter("DeleteHorserace")));

            RaceDao raceDao = new RaceDao(new FactoryDao().getConnectionPool());
            ArrayList<RaceBean> race = (ArrayList) raceDao.getAll(Integer.parseInt(request.getParameter("DeleteHorserace")));
            for(RaceBean raceBean: race) {
                raceDao.delete(raceBean);
            }

            racesDao.delete(races);
        }

            request.getRequestDispatcher("/horseList").forward(request, response);
    }
}
