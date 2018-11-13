/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import api.modelo.Imagem;
import api.modelo.Resultado;
import api.modelo.Usuario;
import api.servico.ServicoResultado;
import core.algoritmo.ListaResultados;
import core.servico.ServicoImagemMongoDBImp;
import core.servico.ServicoResultadoMongoDBImp;
import java.io.IOException;
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
public class SalvarResultado extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String endereco = "/dynamic/jsp/login.jsp";
        this.getRequest(endereco, request, response, false);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        testeSession(session, request, response);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        int imagem_id = Integer.parseInt(request.getParameter("imagem_id"));

        Imagem imagem = ServicoImagemMongoDBImp.getInstance().readById(usuario.getId(), imagem_id);

        List<Resultado> resultados = ServicoResultadoMongoDBImp.getInstance().readResultadoByUsuarioImagem(usuario.getId(), imagem.getId());

        Boolean isMulti = Boolean.parseBoolean(request.getParameter("isMulti"));

        ServicoResultado servicoResultado = ServicoResultadoMongoDBImp.getInstance();

        if (!isMulti) {
            Resultado result = (Resultado) session.getAttribute("resultado");
            servicoResultado.createResultado(result);
        } else {
            List<ListaResultados> lista = (List<ListaResultados>) session.getAttribute("resultados");
            lista.forEach(listaRes -> listaRes.stream().forEach(resultado -> servicoResultado.createResultado(resultado)));

        }
        
        request.setAttribute("isSalvar", false);
        request.setAttribute("resultados", resultados);
        request.setAttribute("imagem", imagem);

        session.setAttribute("imagem", imagem);
        String endereco = "/dynamic/jsp/info_img.jsp";
        getRequest(endereco, request, response, false);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

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
