package controller.user;

import controller.Action;
import controller.Forward;
import domain.Role;
import domain.User;
import service.ServiceException;
import service.UserService;
import utils.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String passport = req.getParameter("passport");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            if (firstName != null && lastName != null && passport != null && login != null && passport != null) {

                UserService userService = getServiceFactory().getUserService();
                if (userService.checkLogin(login)) {
                    return new Forward("/registration.html?message=Этот логин уже занят");
                }
                User user = new User();
                user.setFirstname(firstName);
                user.setLastname(lastName);
                user.setPassport(passport);
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(Role.CLIENT);
                user.setId(userService.save(user));
                req.getSession().setAttribute("currentUser", user);
                return new Forward("/main.html");

            }
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
