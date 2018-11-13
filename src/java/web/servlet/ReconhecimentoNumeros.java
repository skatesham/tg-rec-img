/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import api.modelo.Galeria;
import api.modelo.Imagem;
import api.modelo.Usuario;
import api.servico.ServicoImagem;
import api.servico.ServicoPadrao;
import core.servico.ServicoImagemMongoDBImp;
import core.servico.ServicoPadraoMongoDBImp;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shan
 */
public class ReconhecimentoNumeros extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        testeSession(session, req, resp);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Imagem imagem = (Imagem) session.getAttribute("imagem");
        ServicoPadrao servicoPadrao = ServicoPadraoMongoDBImp.getInstance();
        ServicoImagem servicoImagem = ServicoImagemMongoDBImp.getInstance();

        List<Imagem> padroesModelo = new LinkedList<>();
        for (int i = 1; i <= 6; i++) {
            Imagem imgPadrao = servicoImagem.readById(3, i);
            padroesModelo.add(imgPadrao);
        }

        List<Imagem> padroes = servicoPadrao.readAll();
        session.setAttribute("padroes", padroes);

        Galeria galeria = new Galeria();
        galeria.addAll(padroesModelo);
        req.setAttribute("imagem", imagem);
        req.setAttribute("padroes", galeria);
        req.setAttribute("usuarioLogado", usuario);
        String endereco = "/dynamic/jsp/reconhecimento_numeros.jsp";
        this.getRequest(endereco, req, resp, false);

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
