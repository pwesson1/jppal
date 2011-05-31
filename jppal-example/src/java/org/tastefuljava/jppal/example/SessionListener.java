package org.tastefuljava.jppal.example;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.tastefuljava.pay.PaymentService;

public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        PaymentService service
                = (PaymentService)context.getAttribute("service");
        session.setAttribute("button", service.createButton());
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.removeAttribute("button");
    }
}
