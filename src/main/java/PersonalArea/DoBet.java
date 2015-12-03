package PersonalArea;

import DAO.Bet.BetBean;
import DAO.Bet.BetDao;
import DAO.FactoryDao;
import DAO.Race.RaceBean;
import DAO.Race.RaceDao;
import DAO.Users.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/doBet")
public class DoBet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DoBet() {
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
        int id_race = Integer.parseInt(request.getParameter("doBet"));
        double rate = Double.parseDouble(request.getParameter("ValueBet"));
        BetDao betDao = new BetDao(new FactoryDao().getConnectionPool());
        UserBean user =(UserBean)request.getSession().getAttribute("user");
        BetBean betBean = new BetBean(0, id_race, user.getIdUser(), rate);
        betDao.add(betBean);

        request.getRequestDispatcher("table/userRaceInfo.jsp").include(request, response);
    }
}