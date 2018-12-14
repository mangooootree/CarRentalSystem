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

public class UserUpdateAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder message = new StringBuilder();

        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String passport = req.getParameter("passport");
        String role = req.getParameter("role");

        Role newRole = null;
        if (role != null) {
            newRole = Role.values()[Integer.parseInt(role)];
        }

        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }

        User user = null;
        UserService userService = null;
        try {

            userService = getServiceFactory().getUserService();
            user = userService.findById(id);
            req.setAttribute("user", user);

        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }

        if (!firstName.equals("") && !lastName.equals("") && !passport.equals("")) {

            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setPassport(passport);

            if (newRole != null) {
                user.setRole(newRole);
            }

            try {
                userService.update(user);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            message.append("Профиль обновлен.");

            //if user is updating his password

            String oldPassword = req.getParameter("oldPassword");
            String newPassword = req.getParameter("newPassword");
            if (!oldPassword.equals("") && !newPassword.equals("")) {
                if (oldPassword.equals(user.getPassword())) {
                    user.setPassword(newPassword);
                    try {
                        userService.update(user);
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
                    message.append(" Пароль обновлен.");
                } else {
                    message.append(" Неправильный пароль.");
                }
            }

        }
        else {
           message.append("Проверьте правильность заполнения формы");
        }
        req.setAttribute("message", message.toString());
        return new Forward("/user/edit", false);
    }
}
