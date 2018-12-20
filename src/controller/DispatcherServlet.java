package controller;

import utils.Connector;
import utils.MainServiceFactoryImpl;
import utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            Connector.init();
        }
        catch (ClassNotFoundException e){
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public ServiceFactory getServiceFactory() {
        return new MainServiceFactoryImpl();
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String url = req.getRequestURI();
        String context = req.getContextPath();

        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        Action action = ActionFactory.getAction(url);
        Forward forward = null;
        if(action != null) {
            try(ServiceFactory factory = getServiceFactory()) {
                action.setServiceFactory(factory);
                forward = action.execute(req, resp);
            } catch(Exception e) {
                throw new ServletException(e);
            }
        }


        if(forward != null && forward.isRedirect()) {
            resp.sendRedirect(context + forward.getUrl());
        } else {
            if(forward != null && forward.getUrl() != null) {
                url = forward.getUrl();
            }
            req.getRequestDispatcher("/WEB-INF" + url + ".jsp").forward(req, resp);
        }
    }
}
