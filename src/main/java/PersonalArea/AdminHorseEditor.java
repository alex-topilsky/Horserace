package PersonalArea;

import DAO.FactoryDao;
import DAO.Horses.HorsesBean;
import DAO.Horses.HorsesDao;
import DAO.Race.RaceBean;
import DAO.Race.RaceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/adminHorseTable")
public class AdminHorseEditor   extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminHorseEditor() {
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
        HorsesDao horsesDao = new HorsesDao(new FactoryDao().getConnectionPool());
        ArrayList<HorsesBean> horses = (ArrayList<HorsesBean>)horsesDao.getAll();

        request.getSession().setAttribute("horseList", horses);


        if (request.getParameter("ageHorseEdit") != null) {

            HorsesBean horse = (HorsesBean) horsesDao.getItem(Integer.parseInt(request.getParameter("ageHorseEdit")));

            horse.setAge(Integer.parseInt(request.getParameter("ageHorse")));
            horsesDao.edit(horse);
        }
        if (request.getParameter("breadHorseEdit") != null) {

            HorsesBean horse = (HorsesBean) horsesDao.getItem(Integer.parseInt(request.getParameter("breadHorseEdit")));

            horse.setBread(request.getParameter("breadHorse"));
            horsesDao.edit(horse);
        }
        if (request.getParameter("nameHorseEdit") != null) {

            HorsesBean horse = (HorsesBean) horsesDao.getItem(Integer.parseInt(request.getParameter("nameHorseEdit")));

            horse.setName(request.getParameter("nameHorse"));
            horsesDao.edit(horse);
        }

        if (request.getParameter("NewHorse") != null) {
            HorsesBean horse = new HorsesBean(0,request.getParameter("nameHorse"),
                    Integer.parseInt(request.getParameter("ageHorse")), request.getParameter("breedHorse"));
            horsesDao.add(horse);
        }

        if (request.getParameter("DeleteHorse") != null) {
            RaceDao raceDao = new RaceDao(new FactoryDao().getConnectionPool());
            ArrayList<RaceBean> race = (ArrayList) raceDao.getAll(Integer.parseInt(request.getParameter("DeleteHorse")));
            for(RaceBean raceBean: race) {
                raceDao.delete(raceBean);
            }
            HorsesBean horse = (HorsesBean) horsesDao.getItem(Integer.parseInt(request.getParameter("DeleteHorse")));
            horsesDao.delete(horse);
        }

        request.getRequestDispatcher("/horseList").forward(request, response);
    }
}