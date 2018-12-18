package controller.order;

import controller.Action;
import controller.Forward;
import domain.Bill;
import domain.Car;
import domain.Order;
import service.BillService;
import service.CarService;
import service.OrderService;
import service.ServiceException;
import utils.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class OrderDeleteAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean canNotBeDeleted;

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

                BillService billService = getServiceFactory().getBillService();
                List<Bill> allBills = billService.findAll();

                if (!allBills.isEmpty()) {
                    canNotBeDeleted = allBills.stream().map(bill -> bill.getOrder().getId()).allMatch(id::equals);
                    if (!canNotBeDeleted) {
                        orderService.delete(id);
                    }
                } else {
                    orderService.delete(id);
                }
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/order/list.html");
    }
}
