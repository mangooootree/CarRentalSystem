package controller;

import controller.bill.BillCreateAction;
import controller.bill.BillDeleteAction;
import controller.bill.BillListAction;
import controller.bill.BillSaveAction;
import controller.car.*;
import controller.order.*;
import controller.user.*;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();
    static {
        actions.put("/main", MainAction.class);
        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);
        actions.put("/registration", RegistrationAction.class);
        actions.put("/user/list", UserListAction.class);
        actions.put("/user/delete", UserDeleteAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/update", UserUpdateAction.class);

        actions.put("/car/list", CarListAction.class);
        actions.put("/car/delete", CarDeleteAction.class);
        actions.put("/car/save", CarSaveAction.class);
        actions.put("/car/edit", CarEditAction.class);
        actions.put("/car/update", CarUpdateAction.class);

        actions.put("/order/list", OrderListAction.class);
        actions.put("/order/delete", OrderDeleteAction.class);
        actions.put("/order/accept", OrderAcceptAction.class);
        actions.put("/order/reject", OrderRejectAction.class);
        actions.put("/order/setPaid", OrderSetPaidAction.class);
        actions.put("/order/setUnPaid", OrderSetUnPaidAction.class);
        actions.put("/order/setComment", OrderSetCommentAction.class);
        actions.put("/order/new", OrderCreateAction.class);
        actions.put("/order/save", OrderSaveAction.class);
        actions.put("/order/close", OrderCloseAction.class);

        actions.put("/bill/new", BillCreateAction.class);
        actions.put("/bill/save", BillSaveAction.class);
        actions.put("/bill/list", BillListAction.class);
        actions.put("/bill/delete", BillDeleteAction.class);

    }


    public static Action getAction(String url) throws ServletException {
        Class<?> action = actions.get(url);
        if(action != null) {
            try {
                return (Action)action.newInstance();
            } catch(InstantiationException | IllegalAccessException | NullPointerException e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
