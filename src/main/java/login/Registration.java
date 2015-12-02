package login;

import DAO.FactoryDao;
import DAO.Users.UserBean;
import DAO.Users.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reg")
public class Registration extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Registration() {
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
        String username = request.getParameter("j_name");
        String userlogin = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        if(username.equals("") || userlogin.equals("") || password.equals(""))
        {
           request.setAttribute("registrationError", "Error registration! Field must not be empty");
            request.getRequestDispatcher("registration.jsp").include(request, response);
        }
        else
        {
            if(CheckUser.isAlreadyUse(userlogin))
            {
                request.setAttribute("registrationError", "Error registration! Login already use");
                request.getRequestDispatcher("registration.jsp").include(request, response);
            }
            else{
                UserBean user = new UserBean(username, userlogin, password, 0, "user");
                UsersDao userDao = new UsersDao(new FactoryDao().getConnectionPool());
                userDao.add(user);
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        }


    }
}