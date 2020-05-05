package com.ednilsondava.isdb.controles;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet("/ErroPanel")
public class ErroPanelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    private void processError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        String message = (String) req.getAttribute("javax.servlet.error.message");
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
        if(servletName == null) servletName = "Unknown";
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if(requestUri == null) requestUri = "Unknown";

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");

        StringBuilder builder = new StringBuilder();
        builder.append("@").append(dateFormat.format(Calendar.getInstance().getTime())).append("@").append("<br/>");
        builder.append("@"+message+"@").append("<br/>");
        builder.append("@"+servletName+"@").append("<br/>");
        builder.append("@"+requestUri+"@").append("<br/>");

        if(throwable != null) {
            builder.append("===================================================================").append("<br/>");
            for (StackTraceElement element : throwable.getStackTrace()) {
                String pck = element.getClassName();
                if(pck.startsWith("com.ednilsondava.isdb")) {
                    String b = element.getFileName() + "(" + element.getMethodName() + ":" + element.getLineNumber() + ")";
                    builder.append(b).append("<br/>");
                }
            }
        }

        req.setAttribute("stack", builder.toString());
        req.getRequestDispatcher("/WEB-INF/pages/500.jsp").forward(req, resp);
        req.logout();
    }
}
