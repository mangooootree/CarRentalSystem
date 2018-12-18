package filters;

import domain.Order;
import domain.Role;
import domain.User;
import service.OrderService;
import utils.FactoryException;
import utils.MainServiceFactoryImpl;
import utils.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class NewOrdersFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        User user = (User) req.getSession().getAttribute("currentUser");
        if (user != null && user.getRole() == Role.ADMIN) {
            try (ServiceFactory serviceFactory = new MainServiceFactoryImpl()) {
                OrderService orderService = serviceFactory.getOrderService();
                List<Order> orders = orderService.findAll();
                if (!orders.isEmpty()) {
                    Long newOrdersAmount = orders.stream().filter(order -> order.isReviewed() == false).count();
                    request.setAttribute("newOrdersAmount", newOrdersAmount);
                }
                else {
                    request.setAttribute("newOrdersAmount", 0);
                }
            } catch (FactoryException e) {
                throw new ServletException();
            } catch (Exception e) {
            }
        }

        chain.doFilter(request, response);
    }

}
