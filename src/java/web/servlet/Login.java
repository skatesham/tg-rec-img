/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author sham
 */
public class Login extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        String endereco = "/dynamic/jsp/login.jsp";
        this.getRequest(endereco, req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String endereco = "/dynamic/jsp/login.jsp";
        this.getRequest(endereco, req, resp);
    }

    /**
     * Trocar Pagina JSP
     *
     * @param endereco - Endereço local .jsp
     * @param req - request
     * @param resp - response
     */
    public void getRequest(String endereco, HttpServletRequest req, HttpServletResponse resp) {
        ServletContext sc = req.getServletContext();
        try {
            req.setAttribute("falhaAutenticacao", false);
            sc.getRequestDispatcher(endereco).forward(req, resp);
        } catch (Exception e) {

        }
    }

}
