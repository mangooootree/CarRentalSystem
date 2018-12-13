package controller;

import domain.Car;
import domain.Role;
import domain.User;
import service.CarService;
import service.ServiceException;
import utils.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("currentUser");
        if (user == null) {
            user = new User();
            user.setRole(Role.GUEST);
            req.getSession().setAttribute("currentUser", user);
        }
        try {
            CarService carService = getServiceFactory().getCarService();
            List<Car> cars = carService.findAll();
            req.setAttribute("cars", cars);
        } catch (FactoryException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
