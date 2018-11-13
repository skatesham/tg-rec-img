/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import api.modelo.Imagem;
import api.modelo.Usuario;
import api.servico.ServicoImagem;
import core.servico.ServicoImagemMongoDBImp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author shan
 */
@MultipartConfig
public class UploadImagem extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        testeSession(session, req, resp);
        Usuario usuarioBD = (Usuario) session.getAttribute("usuario");

        //Upload File
        String nome = (String) req.getParameter("nome");
        Part partFile = req.getPart("file");
        String fileName = Paths.get(partFile.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = partFile.getInputStream();

        Imagem imagem = new Imagem(nome, fileContent, fileName);
        ServicoImagem servicoImg = ServicoImagemMongoDBImp.getInstance();

        usuarioBD.incrementarNumeroFoto();

        servicoImg.createImagem(usuarioBD, imagem);

        //req.setAttribute("Usuario", usuarioBD);
        String endereco = "/dynamic/jsp/enviarImagem.jsp";
        
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
