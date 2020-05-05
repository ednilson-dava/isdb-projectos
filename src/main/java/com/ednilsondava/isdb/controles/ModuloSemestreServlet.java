package com.ednilsondava.isdb.controles;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ModSem")
public class ModuloSemestreServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long modulo = Long.valueOf(req.getParameter("idmodulo"));
        resp.sendRedirect(getServletContext().getContextPath()+"/ficha.jsp");
    }
}
