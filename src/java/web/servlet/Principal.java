/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import api.modelo.Usuario;
import core.servico.ServicoImagemMongoDBImp;
import core.servico.ServicoPadraoMongoDBImp;
import core.servico.ServicoResultadoMongoDBImp;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sham
 */
public class Principal extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        testeSession(session, req, resp);
        Usuario usuarioBD = (Usuario) session.getAttribute("usuario");

        int nResultados = ServicoResultadoMongoDBImp.getInstance().countResultadoUsuario(usuarioBD.getId());
        int nImagens = ServicoImagemMongoDBImp.getInstance().countImagemUsuario(usuarioBD.getId());
        int nPadroes = ServicoPadraoMongoDBImp.getInstance().countPadrao();

        req.setAttribute("nResultados", nResultados);
        req.setAttribute("nImagens", nImagens);
        req.setAttribute("nPadroes", nPadroes);
        req.setAttribute("usuarioLogado", usuarioBD);

        String endereco = "/dynamic/jsp/principal.jsp";
        
        getRequest(endereco, req, resp, false);
    }

    /**
     * Trocar Pagina JSP
     *
     * @param endereco - Endereço local .jsp
     * @param req - request
     * @param resp - response
     * @param falha - valor boolean de falha
     */
    public void getRequest(String endereco, HttpServletRequest req, HttpServletResponse resp, boolean falha) {
        ServletContext sc = req.getServletContext();
        try {
            req.setAttribute("falhaAutenticacao", falha);
            sc.getRequestDispatcher(endereco).forward(req, resp);
        } catch (Exception e) {

        }
    }

    /**
     * Testar sessao ainda existe
     *
     * @param session
     * @param req
     * @param resp
     */
    public void testeSession(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
        if (session == null) {
            String endereco = "/dynamic/jsp/login.jsp";
            getRequest(endereco, req, resp, false);
        }
    }
}
