package PersonalArea;

import DAO.Users.UserBean;

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
        //request.setAttribute("user", request.getSession().getAttribute("user"));
        request.getRequestDispatcher("personalArea.jsp").include(request, response);
    }
}