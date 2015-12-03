package PersonalArea;

import DAO.FactoryDao;
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
import java.util.ArrayList;

@WebServlet("/bookmakerHorseraceEditor")
public class BookmakerHorseraceEditor  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BookmakerHorseraceEditor() {
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

        if (request.getParameter("winRateEdit") != null) {
            RacesDao racesDao = new RacesDao(new FactoryDao().getConnectionPool());
            RacesBean races = (RacesBean) racesDao.getItem(Integer.parseInt(request.getParameter("winRateEdit")));

            races.setWinRate(Double.parseDouble(request.getParameter("winRate")));
            racesDao.edit(races);
        }
        request.getSession().setAttribute("NumberRace", request.getParameter("winRateEdit"));

        request.getRequestDispatcher("/allHorserace").forward(request, response);
    }
}