/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import api.modelo.Imagem;
import api.modelo.Usuario;
import api.servico.ServicoImagem;
import core.algoritmo.ListaResultados;
import core.algoritmo.ReconhecimentoFachada;
import core.servico.ServicoImagemMongoDBImp;
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
public class ResultadoNumeros extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Trabalhando com a Sessão
        HttpSession session = req.getSession(false);
        testeSession(session, req, resp);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        int usuario_id = usuario.getId();
        Imagem imagem = (Imagem) session.getAttribute("imagem");
        List<Imagem> padroes = (List<Imagem>) session.getAttribute("padroes");

        int mode = Integer.parseInt((String) req.getParameter("padrao_id"));

        ServicoImagem servicoImagem = ServicoImagemMongoDBImp.getInstance();
        Imagem imgPadrao = servicoImagem.readById(3, mode);

        long tempoInicio = System.currentTimeMillis();
        //1.Algoritmo de Reconhecimento

        List<ListaResultados> lista = ReconhecimentoFachada.reconhecerImagem(mode, imagem, padroes, usuario_id);
        
        ListaResultados maiores = ReconhecimentoFachada.getMaiores();
        
        long tempoAlgoritmo = System.currentTimeMillis() - tempoInicio;
        //System.out.println("1. Algoritmo Completo: Tempo de Execução: " + tempoAlgoritmo + " Milisegundos.");

        
        session.setAttribute("resultados", lista);
        session.setAttribute("maiores", maiores);
        
        req.setAttribute("tempo", tempoAlgoritmo);
        req.setAttribute("maiores", maiores);
        req.setAttribute("imgPadrao", imgPadrao);
        req.setAttribute("resultados", lista);
        req.setAttribute("reconhecimento", true);
        req.setAttribute("usuarioLogado", usuario);
        req.setAttribute("imagem", imagem);

        String endereco = "/dynamic/jsp/resultadonumeros.jsp";
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
            getRequest(endereco, req, resp);
        }
    }

}
