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
import core.servico.ServicoPadraoMongoDBImp;
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
public class VerResultado extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        session.setAttribute("reconhecimento", false);
        
        
        int imagem_id = Integer.parseInt(req.getParameter("imagem_id"));
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Imagem imagem = (Imagem) session.getAttribute("imagem");
        int usuario_id = usuario.getId();
        ServicoResultado servicoResultado = ServicoResultadoMongoDBImp.getInstance();
        List<Imagem> padroes = ServicoPadraoMongoDBImp.getInstance().readAll();
        List<Resultado> resultados = servicoResultado.readResultadoByUsuarioImagem(usuario_id, imagem_id);
        // req.setAttribute("imagem", ServicoImagemMongoDBImp.getInstance().readById(usuario_id, imagem_id));
        // req.setAttribute("padrao", ServicoPadraoMongoDBImp.getInstance().readById(padrao_id));
        
        
        session.setAttribute("padroes", padroes);
        req.setAttribute("padroes", padroes);
        req.setAttribute("resultados", resultados);
        req.setAttribute("imagem", imagem);
        
        
        String endereco = "/dynamic/jsp/resultados_salvos.jsp";
        getRequest(endereco, req, resp);

    }
    
        /**
     * Trocar Pagina JSP
     * 
     * @param endereco - Endereço local .jsp
     * @param req - request
     * @param resp - response
     */
    public void getRequest(String endereco, HttpServletRequest req, HttpServletResponse resp){
            ServletContext sc = req.getServletContext();
        try {
            req.setAttribute("falhaAutenticacao", false);
            sc.getRequestDispatcher(endereco).forward(req, resp);
        } catch (Exception e) {

        }
    }
}
