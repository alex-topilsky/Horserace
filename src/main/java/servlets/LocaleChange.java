package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

@WebServlet(name = "servlets.LocaleChange", urlPatterns = "/localeChange")
public class LocaleChange extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("buttonLocale");

        if(language!=null)
        {
            if(language.equals("RU"))
            {
                request.getSession().setAttribute("language", "ru_RU");
            }
            if(language.equals("EN"))
            {
                request.getSession().setAttribute("language", "en_US");
            }
        }
        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
    }
}