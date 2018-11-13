/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.servico;

import api.dao.UsuarioDAO;
import api.modelo.Usuario;
import api.servico.ServicoUsuario;
import core.dao.mongodb.UsuarioDAOMongoDB;

/**
 *
 * @author shan
 */
public class ServicoUsuarioMongoDBImp implements ServicoUsuario{

    private static ServicoUsuarioMongoDBImp uniqueInstance;
    
    public static ServicoUsuarioMongoDBImp getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new ServicoUsuarioMongoDBImp();
        }
        return uniqueInstance;
    }
    
    private UsuarioDAO udao;

    private ServicoUsuarioMongoDBImp() {
        udao  = new UsuarioDAOMongoDB();
    }
    
    @Override
    public Usuario create(Usuario usuario) {
        Usuario u = null;
        try{
            u = udao.create(usuario);
        }catch(Exception e){
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public Usuario readByNomeUsuario(String nomeUsuario) {
        return udao.readByNomeUsuario(nomeUsuario);
    }

    @Override
    public Usuario updateByUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
