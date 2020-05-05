package com.ednilsondava.isdb.controles;

import com.ednilsondava.isdb.modelos.entidades.Acesso;
import com.ednilsondava.isdb.modelos.entidades.Usuario;
import com.ednilsondava.isdb.modelos.repositorios.Acessos;
import com.ednilsondava.isdb.modelos.repositorios.Usuarios;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/conta")
public class ContaServlet extends HttpServlet {
    @Inject
    private Usuarios usuarios;
    @Inject
    private Acessos acessos;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");

        if(codigo.startsWith("@")) {
            request.login(codigo, "@Admin1234");
            response.sendRedirect(getServletContext().getContextPath() + "/sistema.jsp");
            return;
        }

        Pattern pattern = Pattern.compile("(20[1-2][0-9])(\\d{3})");
        Matcher matcher = pattern.matcher(codigo);

        if(matcher.find() && codigo.length()<8) {
            String ano = matcher.group(1);
            String ref = matcher.group(2);

            Usuario managed = usuarios.encontrarByNomeUsuario(codigo);
            if(managed == null) {
                String tipo = "ESTUDANTE";
                Acesso acesso = acessos.encontrar(tipo);
                if(acesso == null) {
                    acesso = new Acesso();
                    acesso.setTipo(tipo);
                }
                Usuario usuario = new Usuario();
                usuario.setNomeUsuario(codigo);
                usuario.setSenhaUsuario(codigo);
                usuario.setAcesso(acesso);
                usuario.setCriadoEm(Calendar.getInstance());
                usuarios.salva(usuario);
            }
            request.login(codigo, codigo);
            response.sendRedirect(getServletContext().getContextPath() + "/Estudante?codigo="+codigo);
        } else {
            response.sendRedirect(getServletContext().getContextPath() + "/?invalid=true");
        }
    }
}
