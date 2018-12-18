package controller.order;

import controller.Action;
import controller.Forward;
import domain.Car;
import domain.Order;
import service.CarService;
import service.OrderService;
import service.ServiceException;
import utils.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderCloseAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }

        if (id != null) {
            try {
                OrderService orderService = getServiceFactory().getOrderService();
                Order order = orderService.findById(id);
                CarService carService = getServiceFactory().getCarService();
                Car car = order.getCar();
                car.setOrdered(false);
                carService.update(car);

                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Date currentDate = new Date();
                order.setComments(order.getComments() + "<br> | Заказ закрыт " + sdf.format(currentDate));

                orderService.update(order);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/order/list.html");
    }

}
