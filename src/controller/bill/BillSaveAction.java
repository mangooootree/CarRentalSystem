package controller.bill;

import controller.Action;
import controller.Forward;
import domain.Bill;
import domain.Order;
import service.BillService;
import service.OrderService;
import service.ServiceException;
import utils.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BillSaveAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long orderId = null;
        Long cost = null;
        try {
            orderId = Long.parseLong(req.getParameter("orderId"));
            cost = Long.parseLong(req.getParameter("cost"));
        } catch (NumberFormatException e) {
        }

        String damage = req.getParameter("damage");
        try {
            OrderService orderService = getServiceFactory().getOrderService();
            Order order = orderService.findById(orderId);
            req.setAttribute("order", order);

            if (cost != null && damage != null) {
                BillService billService = getServiceFactory().getBillService();
                Bill bill = new Bill();
                bill.setOrder(order);
                bill.setDamageDescription(damage);
                bill.setPrice(cost);
                bill.setPaid(false);
                billService.save(bill);

                req.setAttribute("billSaved", "bill.saved");
                req.setAttribute("cost", req.getParameter("cost"));
                req.setAttribute("damage", damage);
                req.setAttribute("disabled", "disabled");
                return new Forward("/bill/new", false);
            } else {
                req.setAttribute("billSaved", "bill.unsaved");
                req.setAttribute("cost", req.getParameter("cost"));
                req.setAttribute("damage", damage);
                return new Forward("/bill/new", false);
            }

        } catch (FactoryException | ServiceException e) {
            throw new ServletException();
        } catch (Exception e) {
        }
        return null;
    }
}
