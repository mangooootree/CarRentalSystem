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

public class CarUpdateAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new ServletException();
        }

        String model = req.getParameter("model");
        String color = req.getParameter("color");
        String licencePlate = req.getParameter("licencePlate");
        boolean automaticTransmission = Boolean.getBoolean(req.getParameter("gearbox"));

        Long price = null;
        try {
            price = Long.parseLong(req.getParameter("price"));
        } catch (NumberFormatException e) {
        }

        if (id != null && model != null && !model.equals("") && color != null && !color.equals("")
                && licencePlate != null && !licencePlate.equals("") && price != null) {
            try {
                CarService carService = getServiceFactory().getCarService();
                Car car = carService.findById(id);
                car.setModel(model);
                car.setColor(color);
                car.setLicencePlate(licencePlate);
                car.setAutomaticTransmission(automaticTransmission);
                car.setPrice(price);
                carService.update(car);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }

        return new Forward("/car/list.html");
    }
}
