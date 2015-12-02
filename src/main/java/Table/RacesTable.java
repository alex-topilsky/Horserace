package Table;

import DAO.FactoryDao;
import DAO.Horses.HorsesBean;
import DAO.Horses.HorsesDao;
import DAO.Races.RacesBean;
import DAO.Races.RacesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/allHorserace")
public class RacesTable extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RacesTable() {
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
        RacesDao racesDao = new RacesDao(new FactoryDao().getConnectionPool());
        ArrayList<RacesBean> races = (ArrayList<RacesBean>)racesDao.getAll();

        request.getSession().setAttribute("racesList", races);
        request.getRequestDispatcher("table/allHorserace.jsp").include(request, response);
    }
}