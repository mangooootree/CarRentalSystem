package controller;

import controller.car.CarDeleteAction;
import controller.car.CarListAction;
import controller.order.OrderDeleteAction;
import controller.order.OrderListAction;
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

        actions.put("/car/list", CarListAction.class);
        actions.put("/car/delete", CarDeleteAction.class);

        actions.put("/order/list", OrderListAction.class);
        actions.put("/order/delete", OrderDeleteAction.class);
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
