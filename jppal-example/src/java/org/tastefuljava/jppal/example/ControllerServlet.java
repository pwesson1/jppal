package org.tastefuljava.jppal.example;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import javax.servlet.RequestDispatcher;
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
        HttpSession session = request.getSession();
        PaymentButton button = (PaymentButton)session.getAttribute("button");
        if (button == null) {
            button = createButton();
            session.setAttribute("button", button);
        }
        String path = request.getServletPath();
        if (path.equals("/start")) {
            RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
            disp.forward(request, response);
        } else if (path.equals("/build")) {
            populateButton(request, button);
            RequestDispatcher disp = request.getRequestDispatcher("/button.jsp");
            disp.forward(request, response);
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

    private PaymentButton createButton() throws IOException {
        File dir = new File(System.getProperty("catalina.base"), "conf");
        File file = new File(dir, "myppal.properties");
        URL url = file.toURI().toURL();
        PaymentService service = PaymentService.newInstance(url);
        return service.createButton();
    }

    private void populateButton(HttpServletRequest request, PaymentButton button) {
        button.setLanguage(request.getParameter("language"));
        button.setLabel(request.getParameter("label"));
        button.setCustomerId(request.getParameter("customerId"));
        button.setItemCode(request.getParameter("itemCode"));
        button.setItemLabel(request.getParameter("itemLabel"));
        button.setPrice(Util.parseDecimal(request.getParameter("price")));
        button.setCurrency(request.getParameter("currency"));
        button.setReturnUrl(request.getParameter("returnUrl"));
        button.setCancelUrl(request.getParameter("cancelUrl"));
        button.setNotifyUrl(request.getParameter("notifyUrl"));
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
}
