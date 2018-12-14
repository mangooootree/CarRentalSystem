package controller.order;

import controller.Action;
import controller.Forward;
import domain.Order;
import domain.Role;
import domain.User;
import service.OrderService;
import service.ServiceException;
import utils.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OrderService orderService = getServiceFactory().getOrderService();
            User user = (User) req.getSession().getAttribute("currentUser");

            List<Order> orders = orderService.findAll();
            if (user.getRole() == Role.ADMIN) {
                req.setAttribute("orders", orders);
            }
            else {
                List<Order> userOrders = orders.stream().filter(o -> o.getUser().getId() == user.getId()).collect(Collectors.toList());
                req.setAttribute("orders", userOrders);
            }
            return null;
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
