package Table;

import DAO.Bet.BetBean;
import DAO.Bet.BetDao;
import DAO.FactoryDao;
import DAO.Horses.HorsesBean;
import DAO.Horses.HorsesDao;
import DAO.Race.RaceBean;
import DAO.Race.RaceDao;
import DAO.Races.RacesBean;
import DAO.Races.RacesDao;
import DAO.Users.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/myBets")
public class BetTable extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BetTable() {
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
        if(request.getSession().getAttribute("user")!=null) {
            UserBean user = (UserBean) request.getSession().getAttribute("user");

            BetDao betDao = new BetDao(new FactoryDao().getConnectionPool());
            ArrayList<BetBean> bets = (ArrayList<BetBean>) betDao.getAll(user.getIdUser());

            HorsesDao horsesDao = new HorsesDao(new FactoryDao().getConnectionPool());
            ArrayList<HorsesBean> horsesBeans = (ArrayList<HorsesBean>) horsesDao.getAll();

            RacesDao racesDao = new RacesDao(new FactoryDao().getConnectionPool());
            ArrayList<RacesBean> racesBeans = (ArrayList<RacesBean>) racesDao.getAll();

            RaceDao raceDao = new RaceDao(new FactoryDao().getConnectionPool());
            ArrayList<RaceBean> raceBeans = (ArrayList<RaceBean>) raceDao.getAll();

            request.getSession().setAttribute("raceList", raceBeans);
            request.getSession().setAttribute("horseList", horsesBeans);
            request.getSession().setAttribute("racesList", racesBeans);
            request.getSession().setAttribute("betsList", bets);
            request.getRequestDispatcher("table/myBets.jsp").include(request, response);
        }else
        {
           PrintWriter pw = response.getWriter();
            pw.write("<html>");
            pw.write("<head>");
            pw.write("<title>My bets</title>");
            pw.write(" </head>");
            pw.write("<body>");
            pw.write("You not have bet <br/>");
            pw.write("<a href=\"/allHorserace\"> You can see horserace list and do bet</a> <br/>");
            pw.write("</body>");
            pw.write("</html>");
            request.getRequestDispatcher("table/myBets.jsp").include(request,response);
        }
    }
}