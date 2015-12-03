package Table;

import DAO.FactoryDao;
import DAO.Horses.HorsesBean;
import DAO.Horses.HorsesDao;
import DAO.Races.RacesBean;
import DAO.Races.RacesDao;
import DAO.Users.UserBean;
import DAO.Users.UsersDao;
import login.CheckUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/horseList")
public class HorseTable extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HorseTable() {
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
        if(request.getSession().getAttribute("user")!=null)
        {
            UserBean user = (UserBean) request.getSession().getAttribute("user");
            if(CheckUser.isAdmin(user.getLogin(), user.getPassword()))
            {
                response.sendRedirect("table/adminHorseTable.jsp");
            }
            else
            {

                request.getRequestDispatcher("table/horseList.jsp").forward(request, response);
            }
        }
        else {
            request.getRequestDispatcher("table/horseList.jsp").forward(request, response);
        }
    }
}