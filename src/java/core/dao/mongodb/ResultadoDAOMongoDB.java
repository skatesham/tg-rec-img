/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao.mongodb;

import api.dao.ResultadoDAO;
import api.modelo.Resultado;
import com.mongodb.client.MongoCollection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author shan
 */
public class ResultadoDAOMongoDB implements ResultadoDAO{
    
    
    private MongoCollection<Document> collection;

    public ResultadoDAOMongoDB() {
        collection = ConexaoMongoDB.getInstance().getDatabase().getCollection("resultado");
    }
    
    public Document criarResultadoDoc(Resultado resultado){
        Document doc = new Document();
        doc.append("resultado_id", this.countResultadoUsuario(resultado.getUsuario_id()));
        doc.append("usuario_id", resultado.getUsuario_id());
        doc.append("imagem_id", resultado.getImage_id());
        doc.append("padrao_id", resultado.getPadrao_id());
        doc.append("x", resultado.getX());
        doc.append("y", resultado.getY());
        doc.append("caractere", resultado.getCaractere());
        doc.append("resultado", resultado.getResultado());
        return doc;
    }
    
    public Resultado criarResultadoObj(Document doc){
        Resultado resultado = new Resultado();
        int valor = doc.getInteger("resultado_id");
        resultado.setId(valor);
        valor = doc.getInteger("usuario_id");
        resultado.setImage_id(valor);
        valor = doc.getInteger("imagem_id");
        resultado.setUsuario_id(valor);
        valor = doc.getInteger("x");
        resultado.setX(valor);
        valor = doc.getInteger("y");
        resultado.setY(valor);
        valor = doc.getInteger("padrao_id");
        resultado.setPadrao_id(valor);
        resultado.setResultado(doc.getDouble("resultado"));
        resultado.setCaractere(doc.getString("caractere"));
        return resultado;
    }

    @Override
    public Resultado createResultado(Resultado resultado) {
        Document doc = criarResultadoDoc(resultado);
        collection.insertOne(doc);
        doc = new Document();
        Iterator<Document> it = collection.find(doc).iterator();
        return criarResultadoObj(it.next());
    }

    @Override
    public List<Resultado> readResultadoByImagem(int usuario_id, int imagem_id) {
        
        Document query = new Document();
        query.append("usuario_id", usuario_id);
        query.append("imagem_id", imagem_id);
        Iterator<Document> it = collection.find(query).iterator();
        List<Resultado> resultados = new LinkedList<>();
        while(it.hasNext()){
            resultados.add(criarResultadoObj(it.next()));
        }
        
        return resultados;
    }

    @Override
    public Resultado read(int imagem_id, int padrao_id) {
        Document query = new Document();
        query.append("imagem_id", imagem_id);
        query.append("padrao_id", padrao_id);
        Iterator<Document> it = collection.find(query).iterator();
        Resultado resultado = null;
        if(it.hasNext()){
            resultado = criarResultadoObj(it.next());
        }
        return resultado;
    }
    
    

    @Override
    public Resultado removeResultado(int usuario_id, int imagem_id, int resultado_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countResultadoUsuario(int usuario_id) {
        Long l = collection.count(new Document("usuario_id", usuario_id));
        return l.intValue();
    }
    
    
    
}
