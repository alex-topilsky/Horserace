package Table;

import DAO.FactoryDao;
import DAO.Horses.HorsesBean;
import DAO.Horses.HorsesDao;
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
import java.util.ArrayList;

@WebServlet("/raceinfo")
public class RaceTable extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RaceTable() {
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
        int id_races = Integer.parseInt(request.getParameter("NumberRace"));

        RaceDao raceDao = new RaceDao(new FactoryDao().getConnectionPool());
        ArrayList<RaceBean> race = (ArrayList<RaceBean>)raceDao.getAll(id_races);

        HorsesDao horsesDao = new HorsesDao(new FactoryDao().getConnectionPool());
        ArrayList<HorsesBean> horses = (ArrayList<HorsesBean>)horsesDao.getAll();


        RacesDao racesDao = new RacesDao(new FactoryDao().getConnectionPool());
        RacesBean races = racesDao.getItem(id_races);

        request.getSession().setAttribute("raceListHorses", horses);
        request.getSession().setAttribute("raceListRaces", races);
        request.getSession().setAttribute("raceList", race);
        if(request.getSession().getAttribute("user")!=null) {
            UserBean user = (UserBean) request.getSession().getAttribute("user");
            if (CheckUser.isUser(user.getLogin(), user.getPassword())) {
                request.getRequestDispatcher("table/userRaceInfo.jsp").include(request, response);
            }else
            {
                request.getRequestDispatcher("table/raceinfo.jsp").include(request, response);
            }
        }else
        {
            request.getRequestDispatcher("table/raceinfo.jsp").include(request, response);
        }
    }
}