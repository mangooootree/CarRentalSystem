package controller.car;

import controller.Action;
import controller.Forward;
import domain.Car;
import service.CarService;
import service.ServiceException;
import utils.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CarListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CarService carService = getServiceFactory().getCarService();
            List<Car> cars = carService.findAll();
            req.setAttribute("cars", cars);
            return null;
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
