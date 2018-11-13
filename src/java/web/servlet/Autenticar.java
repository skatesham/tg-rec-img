/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import api.modelo.Criptografia;
import api.modelo.Usuario;
import api.servico.ServicoUsuario;
import core.servico.ServicoImagemMongoDBImp;
import core.servico.ServicoPadraoMongoDBImp;
import core.servico.ServicoResultadoMongoDBImp;
import core.servico.ServicoUsuarioMongoDBImp;
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
public class Autenticar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
        }

        HttpSession session = request.getSession();

        //Buscando Atribustos Inseridos no form Login
        String nomeUsuario = (String) request.getParameter("usuario");
        String senha = (String) request.getParameter("senha");
        //Critografia
        senha = Criptografia.criptografa(senha);

        Usuario usuarioBD;
        //INICIANDO SERVICO BD e BUSCANDO USUARIO
        ServicoUsuario su = ServicoUsuarioMongoDBImp.getInstance();
        usuarioBD = su.readByNomeUsuario(nomeUsuario);

        //placehold para endereco
        String endereco;
        if (usuarioBD != null && senha.equals(usuarioBD.getSenha())) {

            int nResultados = ServicoResultadoMongoDBImp.getInstance().countResultadoUsuario(usuarioBD.getId());
            int nImagens = ServicoImagemMongoDBImp.getInstance().countImagemUsuario(usuarioBD.getId());
            int nPadroes = ServicoPadraoMongoDBImp.getInstance().countPadrao();

            request.setAttribute("nResultados", nResultados);
            request.setAttribute("nImagens", nImagens);
            request.setAttribute("nPadroes", nPadroes);

            //Uso alternativo de UniqueInstance para salvar Usuario Atual
            //AcessoBanco.getInstance().setUsuario(usuarioBD);
            synchronized (session) {
                session.setAttribute("usuario", usuarioBD);
            }
            //Set Usuario Logado para requisição
            request.setAttribute("usuarioLogado", usuarioBD);
            endereco = "/dynamic/jsp/principal.jsp";
            this.getRequest(endereco, request, response, false);

        } else {
            endereco = "/dynamic/jsp/login.jsp";
            request.setAttribute("falhaAutenticacao", true);
            this.getRequest(endereco, request, response, true);

        }
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
            e.printStackTrace();
        }
    }

}
