/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.servico;

import api.modelo.Galeria;
import api.modelo.Imagem;
import api.modelo.Usuario;
import api.servico.ServicoImagem;
import core.dao.mongodb.ImagemDAOMongoDB;

/**
 *
 * @author shan
 */
public class ServicoImagemMongoDBImp implements ServicoImagem{
    
    private static ServicoImagemMongoDBImp uniqueInstance;
    
    public static ServicoImagemMongoDBImp getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new ServicoImagemMongoDBImp();
        }
        return uniqueInstance;
    }
    
    private ImagemDAOMongoDB idao;

    private ServicoImagemMongoDBImp() {
        this.idao = new ImagemDAOMongoDB();
    }

    @Override
    public Imagem createImagem(Usuario usuario, Imagem imagem) {
        return idao.createImagem(usuario, imagem);
    }

    @Override
    public Galeria readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Imagem readById(int usuario_id, int imagem_id) {
        return idao.readById(usuario_id, imagem_id);
    }

    @Override
    public Galeria readByUsuario(Usuario usuario) {
        return idao.readByUsuario(usuario);
    }

    @Override
    public int deleteById(Usuario usuario, int imagem_id) {
   
        return idao.deleteById(imagem_id, imagem_id);
    }

    @Override
    public int countImagemUsuario(int usuario_id)  {
        return idao.countImagemUsuario(usuario_id);
    }
    
    
    
    
    
}