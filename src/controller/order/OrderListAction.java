package controller.order;

import controller.Action;
import controller.Forward;
import domain.Order;
import service.OrderService;
import service.ServiceException;
import utils.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OrderService orderService = getServiceFactory().getOrderService();
            List<Order> orders = orderService.findAll();
            req.setAttribute("orders", orders);
            return null;
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
