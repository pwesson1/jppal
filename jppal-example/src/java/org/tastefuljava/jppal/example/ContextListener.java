/*
    jppal, a Paypal button generator in Java
    Copyright (C) 2011  Maurice Perry <maurice@perry.ch>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
