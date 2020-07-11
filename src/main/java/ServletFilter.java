import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/ServletFilter")
public class ServletFilter implements Filter {
    @Override
    public void init(FilterConfig config)
            throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String requestPath = request.getRequestURI();

        if (needsAuthentication(requestPath) ||
                session == null ||
                session.getAttribute("user_id") == null) {

            response.sendRedirect(request.getContextPath() + "/login/login.jsp");
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean needsAuthentication(String url) {
        String[] validNonAuthenticationUrls =
                { "login/login.jsp", "register/register.jsp" };
        for(String validUrl : validNonAuthenticationUrls) {
            if (url.endsWith(validUrl)) {
                return false;
            }
        }
        return true;
    }
}
