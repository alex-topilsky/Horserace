package PersonalArea;

import DAO.Bet.BetBean;
import DAO.Bet.BetDao;
import DAO.FactoryDao;
import DAO.Race.RaceBean;
import DAO.Race.RaceDao;
import DAO.Users.UserBean;
import DAO.Users.UsersDao;

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
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        if(user.getBalance()>rate) {
            BetDao betDao = new BetDao(new FactoryDao().getConnectionPool());
            BetBean betBean = new BetBean(0, id_race, user.getIdUser(), rate);
            betDao.add(betBean);

            double balance = user.getBalance();
            user.setBalance(balance-rate);
            UsersDao usersDao = new UsersDao(new FactoryDao().getConnectionPool());
            usersDao.edit(user);
        }

        request.getRequestDispatcher("table/userRaceInfo.jsp").include(request, response);
    }
}