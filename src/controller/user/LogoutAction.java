package controller.user;

import controller.Action;
import controller.Forward;
import domain.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutAction extends Action {
    private static final Logger log = LogManager.getLogger(LogoutAction.class.getName());

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user =(User)session.getAttribute("currentUser");
            session.invalidate();
            log.info("User " + user.getId() + " log out");
        }

        return new Forward("/main.html");
    }
}
