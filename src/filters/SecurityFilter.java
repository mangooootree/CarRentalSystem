package filters;

import domain.Role;
import domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {
    private static final Map<String, Set<Role>> permissions = new HashMap<>();

    static {
        Set<Role> all = new HashSet<>();
        all.addAll(Arrays.asList(Role.values()));
        Set<Role> admin = new HashSet<>();
        admin.add(Role.ADMIN);
        Set<Role> client = new HashSet<>();
        client.add(Role.CLIENT);
        client.add(Role.ADMIN);

        permissions.put("/main", all);
        permissions.put("/login", all);
        permissions.put("/logout", all);
        permissions.put("/registration", all);
        permissions.put("/user/list", admin);
        permissions.put("/user/delete", admin);
        permissions.put("/user/edit", client);
        permissions.put("/user/update", client);

        permissions.put("/car/list", admin);
        permissions.put("/car/delete", admin);
        permissions.put("/car/save", admin);

        permissions.put("/order/list", client);
        permissions.put("/order/delete", admin);
        permissions.put("/order/accept", admin);
        permissions.put("/order/reject", admin);
        permissions.put("/order/setPaid", admin);
        permissions.put("/order/setUnPaid", admin);
        permissions.put("/order/setComment", admin);
        permissions.put("/order/new", client);
        permissions.put("/order/save", client);
        permissions.put("/order/close", admin);

        permissions.put("/bill/new", admin);
        permissions.put("/bill/save", admin);
        permissions.put("/bill/list", client);
        permissions.put("/bill/delete", admin);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)req;
        HttpServletResponse httpResp = (HttpServletResponse)resp;
        String url = httpReq.getRequestURI();
        String context = httpReq.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }

        User user = (User) httpReq.getSession().getAttribute("currentUser");
        if (user == null) {
            user = new User();
            user.setRole(Role.GUEST);
            httpReq.getSession().setAttribute("currentUser", user);
        }

        Set<Role> roles = permissions.get(url);
        if(roles != null) {
            HttpSession session = httpReq.getSession(false);
            if(session != null) {
                user = (User)session.getAttribute("currentUser");
                if(user != null && roles.contains(user.getRole())) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        } else {
            chain.doFilter(req, resp);
            return;
        }
        httpResp.sendRedirect(context + "/login.html");
    }

    @Override
    public void destroy() {}
}
