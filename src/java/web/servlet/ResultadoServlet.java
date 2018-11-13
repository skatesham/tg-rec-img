/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import api.modelo.Imagem;
import api.modelo.Resultado;
import api.modelo.Usuario;
import core.algoritmo.Grafico;
import core.algoritmo.ListaResultados;
import core.algoritmo.Padrao;
import core.algoritmo.ReconhecimentoDeImagem;
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
public class ResultadoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        testeSession(session, req, resp);

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Imagem> padroes = (List<Imagem>) session.getAttribute("padroes");

        int index = Integer.parseInt((String) req.getParameter("padrao_id"));
        int imagem_id = Integer.parseInt((String) req.getParameter("imagem_id"));
        Imagem padrao = padroes.get(index);
        Imagem imagem = (Imagem) session.getAttribute("imagem");
        
        //ALGORITMO DE RECONHECIMENTO
        Padrao imagemPadrao = new Padrao(imagem.getImage());
        Padrao amostraPadrao = new Padrao(padrao.getImage());
        
        long tempoInicio = System.currentTimeMillis();
        
        //1.Algoritmo de Reconhecimento
        ReconhecimentoDeImagem recimg = new ReconhecimentoDeImagem();
        Resultado resultado = recimg.ReconhecerImagem(amostraPadrao, imagemPadrao);
        
        long tempoAlgoritmo = System.currentTimeMillis() - tempoInicio;
        //System.out.println("1. Algoritmo Completo: Tempo de Execução: " + tempoAlgoritmo + " Milisegundos.");
       
        resultado.setUsuario_id(usuario.getId());
        resultado.setImage_id(imagem_id);
        resultado.setPadrao_id(padrao.getId());
        resultado.setCaractere(padrao.getNome());
        ListaResultados listaResultados = new ListaResultados();
        listaResultados.addAll(recimg.getResultados());
        resultado.setUsuario_id(usuario.getId());

        
        
        Grafico graphX = new Grafico(listaResultados, resultado, 'X');
        Grafico graphY = new Grafico(listaResultados, resultado, 'Y');

        Imagem graficoX = new Imagem("Gráfico X", graphX.createImagem(400, 600));
        Imagem graficoY = new Imagem("Gráfico Y", graphY.createImagem(400, 600));

        req.setAttribute("graficoX", graficoX);
        req.setAttribute("graficoY", graficoY);

        session.setAttribute("resultado", resultado);
        
        req.setAttribute("reconhecimento", true);
        boolean isVer = (boolean) session.getAttribute("reconhecimento");
        
        if(!isVer){
            req.setAttribute("reconhecimento", false);
        }
        req.setAttribute("tempo", tempoAlgoritmo);
        req.setAttribute("imagem", imagem);
        req.setAttribute("padrao", padrao);
        req.setAttribute("resultado", resultado);
        String endereco = "/dynamic/jsp/resultado.jsp";
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
