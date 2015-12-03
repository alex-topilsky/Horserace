package PersonalArea;

import DAO.FactoryDao;
import DAO.Users.UserBean;
import DAO.Users.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/personalArea")
public class PersonalInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PersonalInfo() {
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

        int value = Integer.parseInt(request.getParameter("replenishTheBalance"));
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        user.setBalance(user.getBalance() + value);
        UsersDao userDao = new UsersDao(new FactoryDao().getConnectionPool());
        userDao.edit(user);
        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher("personalArea.jsp").include(request, response);
    }
}