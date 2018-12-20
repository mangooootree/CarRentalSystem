package controller.user;

import controller.Action;
import controller.Forward;
import domain.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.ServiceException;
import service.UserService;
import utils.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



public class LoginAction extends Action {
    private static final Logger log = LogManager.getLogger(LoginAction.class.getName());

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(login != null && password != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = service.findByLoginAndPassword(login, password);
                if(user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("currentUser", user);
                    log.info("User " + user.getId() + " log in");
                    return new Forward("/main.html");
                } else {
                    return new Forward("/login.html");
                }
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
