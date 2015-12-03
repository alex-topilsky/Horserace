package login;

import DAO.Users.UserBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/adminHorseTable")
public class AdminSecurity implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (httpServletRequest.getSession().getAttribute("user") == null) {
            String username = httpServletRequest.getParameter("j_username");
            String password = httpServletRequest.getParameter("j_password");
            UserBean user = CheckUser.getUser(username, password);
            if (user != null) {
                if(CheckUser.isAdmin(user.getLogin(), user.getPassword()))
                {
                    httpServletRequest.getSession().invalidate();
                    httpServletRequest.getSession().setAttribute("user", user);
                    servletRequest = httpServletRequest;
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                else
                {
                    httpServletRequest.getRequestDispatcher("../error.jsp").forward(httpServletRequest, httpServletResponse);
                }
            } else
                httpServletRequest.getRequestDispatcher("../error.jsp").forward(httpServletRequest, httpServletResponse);
        }
        else {
            UserBean user = (UserBean) httpServletRequest.getSession().getAttribute("user");
            if (CheckUser.isAdmin(user.getLogin(), user.getPassword())) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else
            {
                httpServletRequest.getRequestDispatcher("../error.jsp").forward(httpServletRequest, httpServletResponse);
            }
        }
    }

    public void destroy() {
    }

}