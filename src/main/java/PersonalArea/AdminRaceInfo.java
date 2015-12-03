package PersonalArea;

import DAO.Bet.BetBean;
import DAO.Bet.BetDao;
import DAO.FactoryDao;
import DAO.Race.RaceBean;
import DAO.Race.RaceDao;
import DAO.Races.RacesBean;
import DAO.Races.RacesDao;
import DAO.Users.UserBean;
import DAO.Users.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

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
            int id_horse = race.getIdHorse();
            raceDao.edit(race);

            int id_rases=race.getIdRaces();
            RacesDao racesDao = new RacesDao(new FactoryDao().getConnectionPool());
            RacesBean races = racesDao.getItem(id_rases);
            double betRate= races.getWinRate();
                    races.setDone("done");
            racesDao.edit(races);
            ArrayList<Integer> win_rate=new ArrayList<>();
            ArrayList<Integer> fail_rate=new ArrayList<>();
            ArrayList<RaceBean> allrace = (ArrayList<RaceBean>) raceDao.getAll();
            for(RaceBean raceBean: allrace)
            {
                if(raceBean.getIdRaces()==id_rases && raceBean.getIdHorse()==id_horse)
                {
                    win_rate.add(raceBean.getIdRace());

                }else
                {
                    if(raceBean.getIdRaces()==id_rases)
                    {
                        fail_rate.add(raceBean.getIdRace());
                    }
                }
            }

            BetDao betDao = new BetDao(new FactoryDao().getConnectionPool());
            ArrayList<BetBean> betBeans = (ArrayList<BetBean>) betDao.getAll();
            ArrayList<Integer> id_user = new ArrayList<>();
            double bankValue=0;
            for(Integer win: win_rate) {
                for (BetBean bet : betBeans) {
                    if (bet.getIdRace() == win) {
                        id_user.add(bet.getIdUser());
                    }
                }
            }

            for(Integer fail: win_rate) {
                for (Integer win : win_rate) {
                    for (BetBean bet : betBeans) {
                        if (bet.getIdRace() == win || bet.getIdRace() == fail) {
                            bankValue+= bet.getRate();
                        }
                    }
                }
            }

            bankValue=bankValue-bankValue/betRate;
            double winValue =bankValue/win_rate.size();
            UsersDao userDao = new UsersDao(new FactoryDao().getConnectionPool());
            ArrayList<UserBean> users = (ArrayList<UserBean>) userDao.getAll();
            for(Integer win: win_rate) {
                for (UserBean user : users) {
                    if (user.getIdUser()==win)
                    {
                      double userbalance = user.getBalance();
                        user.setBalance(userbalance+winValue);
                        userDao.edit(user);
                    }
                }
            }
        }

        request.getRequestDispatcher("/horseList").forward(request, response);
    }
}