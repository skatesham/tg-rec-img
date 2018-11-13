/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.servico;

import api.modelo.Imagem;
import api.servico.ServicoPadrao;
import core.dao.mongodb.PadraoDAOMongoDB;
import java.util.List;

/**
 *
 * @author shan
 */
public class ServicoPadraoMongoDBImp implements ServicoPadrao{

    private static ServicoPadraoMongoDBImp uniqueInstance;
    
    public static ServicoPadraoMongoDBImp getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new ServicoPadraoMongoDBImp();
        }
        return uniqueInstance;
    }
    
    private PadraoDAOMongoDB idao;

    private ServicoPadraoMongoDBImp() {
        this.idao = new PadraoDAOMongoDB();
    }
    
    
    @Override
    public Imagem create(Imagem padrao) {
        return idao.create(padrao);
    }

    @Override
    public Imagem readById(int id) {
        return idao.readById(id);
    }

    @Override
    public List<Imagem> readAll() {
        return idao.readAll();
    }

    @Override
    public int countPadrao() {
        return idao.countPadrao();
    }
    
    
    
    
}
