package filters;

import domain.Role;
import domain.User;
import service.OrderService;
import utils.FactoryException;
import utils.MainServiceFactoryImpl;
import utils.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class NewOrdersFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        User user = (User) req.getSession().getAttribute("currentUser");
        if (user != null && user.getRole() == Role.ADMIN) {
            try (ServiceFactory serviceFactory = new MainServiceFactoryImpl()) {
                OrderService orderService = serviceFactory.getOrderService();
                Long newOrdersAmount = orderService.findAll().stream().filter(order -> order.isReviewed() == false).count();
                request.setAttribute("newOrdersAmount", newOrdersAmount);
            } catch (FactoryException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        chain.doFilter(request, response);
    }

}
