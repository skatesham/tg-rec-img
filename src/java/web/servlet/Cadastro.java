package web.servlet;

import api.modelo.Criptografia;
import api.modelo.EnumPapeis;
import api.modelo.Galeria;
import api.modelo.Imagem;
import api.modelo.Papel;
import api.modelo.Usuario;
import api.servico.ServicoImagem;
import api.servico.ServicoUsuario;
import core.servico.ServicoImagemMongoDBImp;
import core.servico.ServicoUsuarioMongoDBImp;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shan
 */
public class Cadastro extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String nomeUsuario = req.getParameter("usuario");

        ServicoUsuario su = ServicoUsuarioMongoDBImp.getInstance();
        ServicoImagem si = ServicoImagemMongoDBImp.getInstance();

        Galeria galeria = si.readByUsuario(new Usuario(1));

        //criptografar
        senha = Criptografia.criptografa(senha);

        Usuario usuario = new Usuario(nome, email, senha, nomeUsuario);
        List<Papel> papeis = new LinkedList<>();
        papeis.add(new Papel(1, EnumPapeis.VISITANTE));
        usuario.setPapeis(papeis);
        Usuario u = null;
        if (nome != null && senha != null) {
            u = su.create(usuario);
            for (Imagem img : galeria) {
                si.createImagem(u, img);
                usuario.incrementarNumeroFoto();
            }
        }
        String endereco;
        if (u != null) {
            endereco = "/dynamic/jsp/login.jsp";
            this.getRequest(endereco, req, resp, false);
        } else {
            endereco = "/dynamic/jsp/registro.jsp";
            this.getRequest(endereco, req, resp, true);
        }
    }

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
