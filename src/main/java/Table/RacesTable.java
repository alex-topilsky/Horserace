package Table;

import DAO.FactoryDao;
import DAO.Horses.HorsesBean;
import DAO.Horses.HorsesDao;
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
        ArrayList<RacesBean> races = (ArrayList<RacesBean>) racesDao.getAll();

        request.getSession().setAttribute("racesList", races);

        if (request.getSession().getAttribute("user") != null) {
            UserBean user = (UserBean) request.getSession().getAttribute("user");
            if (CheckUser.isAdmin(user.getLogin(), user.getPassword())) {
                response.sendRedirect("table/adminHorseraceTable.jsp");
            } else {
                if (CheckUser.isBookmaker(user.getLogin(), user.getPassword())) {
                    request.getRequestDispatcher("table/bookmakerHorseraceTable.jsp").forward(request, response);
                }else {
                    request.getRequestDispatcher("table/allHorserace.jsp").forward(request, response);
                }
            }
        } else {
            request.getRequestDispatcher("table/allHorserace.jsp").forward(request, response);
        }
    }
}