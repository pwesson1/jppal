package org.tastefuljava.jppal.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.tastefuljava.pay.PaymentButton;
import org.tastefuljava.pay.PaymentService;
import org.tastefuljava.pay.Util;

public class ControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PaymentButton button = getButton(request);
        String path = request.getServletPath();
        if (path.equals("/start")) {
            RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
            disp.forward(request, response);
        } else if (path.equals("/build")) {
            populateButton(request, button);
            RequestDispatcher disp = request.getRequestDispatcher("/button.jsp");
            disp.forward(request, response);
        } else if (path.equals("/return")) {
            Map<String,String> attrs = getParameters(request);
            request.setAttribute("payment", attrs);
            RequestDispatcher disp = request.getRequestDispatcher("/return.jsp");
            disp.forward(request, response);
        } else if (path.equals("/cancel")) {
            Map<String,String> attrs = getParameters(request);
            request.setAttribute("payment", attrs);
            RequestDispatcher disp = request.getRequestDispatcher("/cancel.jsp");
            disp.forward(request, response);
        } else if (path.equals("/notify")) {
            Map<String,String> attrs = getParameters(request);
            PaymentService service = getService();
            service.processNotification(attrs);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Button builder";
    }// </editor-fold>

    private Map<String,String> getParameters(HttpServletRequest request) {
        Map<String,String> attrs = new HashMap<String,String>();
        for (Object obj: request.getParameterMap().keySet()) {
            String name = (String)obj;
            String value = request.getParameter(name);
            attrs.put(name, value);
        }
        return attrs;
    }

    private void populateButton(HttpServletRequest request, PaymentButton button) {
        String base = baseURL(request);
        button.setReturnUrl(base + "/return");
        button.setCancelUrl(base + "/cancel");
        button.setNotifyUrl(base + "/notify");
        button.setLanguage(request.getParameter("language"));
        button.setLabel(request.getParameter("label"));
        button.setCustomerId(request.getParameter("customerId"));
        button.setItemCode(request.getParameter("itemCode"));
        button.setItemLabel(request.getParameter("itemLabel"));
        button.setPrice(Util.parseDecimal(request.getParameter("price")));
        button.setCurrency(request.getParameter("currency"));
        button.setEmail(request.getParameter("email"));
        button.setFirstName(request.getParameter("firstName"));
        button.setLastName(request.getParameter("lastName"));
        button.setAddress1(request.getParameter("address1"));
        button.setAddress2(request.getParameter("address2"));
        button.setZip(request.getParameter("zip"));
        button.setCity(request.getParameter("city"));
        button.setState(request.getParameter("state"));
        button.setCountry(request.getParameter("country"));
    }

    private PaymentService getService() throws IOException {
        ServletContext context = getServletContext();
        return (PaymentService)context.getAttribute("service");
    }

    private PaymentButton getButton(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (PaymentButton)session.getAttribute("button");
    }

    private String baseURL(HttpServletRequest request) {
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();
        StringBuilder buf = new StringBuilder();
        buf.append(scheme);
        buf.append("://");
        buf.append(host);
        if (port != defaultPort(scheme)) {
            buf.append(":");
            buf.append(port);
        }
        buf.append(request.getContextPath());
        return buf.toString();
    }

    private int defaultPort(String scheme) {
        if (scheme.equals("http")) {
            return 80;
        } else if (scheme.equals("https")) {
            return 443;
        } else {
            return -1;
        }
    }
}
