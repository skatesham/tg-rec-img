/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao.mongodb;

import api.dao.PadraoDAO;
import api.modelo.Imagem;
import com.mongodb.client.MongoCollection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.types.Binary;

/**
 *
 * @author shan
 */
public class PadraoDAOMongoDB implements PadraoDAO{

    private MongoCollection<Document> collection;

    public PadraoDAOMongoDB() {
        collection = ConexaoMongoDB.getInstance().getDatabase().getCollection("padrao");
    }

    private Document criarPadraoDoc(Imagem imagem) {
        Document doc = new Document();
        doc.append("padrao_id", imagem.getId());
        doc.append("nome", imagem.getNome());
        doc.append("tamanho", imagem.getTamanho());
        doc.append("tipo", imagem.getTipo());
        doc.append("imagem", imagem.getBytes());

        return doc;
    }
    
    private Imagem criarPadraoObj(Document doc){
        Imagem img = new Imagem();
        img.setId(doc.getInteger("padrao_id"));
        img.setNome(doc.getString("nome"));
        img.setTamanho(doc.getString("tamanho"));
        img.setTipo(doc.getString("tipo"));
        Binary binary  = (Binary) doc.get("imagem");
        img.setImage(binary.getData());
        return img;
    }
    
    @Override
    public Imagem create(Imagem padrao) {
        Document doc = criarPadraoDoc(padrao);
        collection.insertOne(doc);
        Iterator<Document> userIter = collection.find(doc).iterator();
        return criarPadraoObj(userIter.next());
    }

    @Override
    public Imagem readById(int id) {
        Document doc = new Document("padrao_id", id);
        Iterator<Document> userIter = collection.find(doc).iterator();
        return criarPadraoObj(userIter.next());
    }

    @Override
    public List<Imagem> readAll() {
        List<Imagem> padroes = new LinkedList();
        Iterator<Document> userIter = collection.find().iterator();
        while(userIter.hasNext()){
            padroes.add(criarPadraoObj(userIter.next()));
        }
        return padroes;
                
    }

    @Override
    public Imagem updateById(int id, Imagem padrao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Imagem deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countPadrao() {
        Long l = collection.count();
        return l.intValue();
    }
    
    
    
    
}
