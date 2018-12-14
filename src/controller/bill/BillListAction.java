package controller.bill;

import controller.Action;
import controller.Forward;
import domain.Bill;
import service.BillService;
import service.ServiceException;
import utils.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BillListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BillService billService = getServiceFactory().getBillService();
            List<Bill> bills = billService.findAll();
            if (!bills.isEmpty()) {
                req.setAttribute("bills", bills);
            }
                return null;
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
