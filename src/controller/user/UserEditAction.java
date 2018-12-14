package controller.user;

import controller.Action;
import controller.Forward;
import domain.User;
import service.ServiceException;
import service.UserService;
import utils.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserEditAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = service.findById(id);
                req.setAttribute("user", user);

            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return null;

    }
}
