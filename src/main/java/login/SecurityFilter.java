package login;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/personalArea")
public class SecurityFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String username = httpServletRequest.getParameter("j_username");
        String password = httpServletRequest.getParameter("j_password");

        if (CheckUser.isUserCorrect(username, password))
            filterChain.doFilter(servletRequest, servletResponse);
        else
            httpServletRequest.getRequestDispatcher("error.jsp").forward(httpServletRequest, httpServletResponse);
    }

    public void destroy() {
    }

}
