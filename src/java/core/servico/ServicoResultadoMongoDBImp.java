/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.servico;

import api.modelo.Resultado;
import api.servico.ServicoResultado;
import core.dao.mongodb.ResultadoDAOMongoDB;
import java.util.List;

/**
 *
 * @author shan
 */
public class ServicoResultadoMongoDBImp implements ServicoResultado{
    
    private static ServicoResultadoMongoDBImp uniqueInstance;
    
    public static ServicoResultadoMongoDBImp getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new ServicoResultadoMongoDBImp();
        }
        return uniqueInstance;
    }
    
    private ResultadoDAOMongoDB rdao;

    public ServicoResultadoMongoDBImp() {
        rdao = new ResultadoDAOMongoDB();
    }  
    
    public Resultado readByImagemByPadrao(int imagem_id, int padrao_id){
        return  rdao.read(imagem_id, padrao_id);
    }
    
    @Override
    public Resultado createResultado(Resultado resultado) {
        return rdao.createResultado(resultado);
    }

    @Override
    public List<Resultado> readResultadoByUsuarioImagem(int usuario_id, int imagem_id) {
        return rdao.readResultadoByImagem(usuario_id, imagem_id);
    }

    @Override
    public Resultado removeResultado(int usuario_id, int imagem_id, int resultado_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countResultadoUsuario(int usuario_id) {
        return rdao.countResultadoUsuario(usuario_id);
    }
    
    
    
}
