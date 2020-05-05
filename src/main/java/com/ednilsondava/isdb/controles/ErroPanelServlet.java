package com.ednilsondava.isdb.controles;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        req.getRequestDispatcher("/WEB-INF/pages/500.jsp").forward(req, resp);
        req.logout();
    }
}
