package controller.bill;

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

public class BillCreateAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            try {
                OrderService orderService = getServiceFactory().getOrderService();
                Order order = orderService.findById(id);
                req.setAttribute("order", order);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/bill/new", false);
    }
}
