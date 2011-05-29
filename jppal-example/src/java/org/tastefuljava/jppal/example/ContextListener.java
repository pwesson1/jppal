package org.tastefuljava.jppal.example;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.tastefuljava.pay.PaymentService;

public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        PaymentService.initialize();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        PaymentService.terminate();
    }
}
