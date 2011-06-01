package org.tastefuljava.jppal.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.tastefuljava.pay.PaymentService;

public class ContextListener implements ServletContextListener {
    private static final Logger LOG
            = Logger.getLogger(ContextListener.class.getName());

    public void contextInitialized(ServletContextEvent sce) {
        try {
            PaymentService.initialize();
            File dir = new File(System.getProperty("catalina.base"), "conf");
            File file = new File(dir, "myppal.properties");
            URL url = file.toURI().toURL();
            ServletContext context = sce.getServletContext();
            PaymentService service = PaymentService.newInstance(url);
            context.setAttribute("service", service);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.removeAttribute("service");
        PaymentService.terminate();
    }
}
