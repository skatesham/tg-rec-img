/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao.mongodb;

import api.dao.ImagemDAO;
import api.modelo.Galeria;
import api.modelo.Imagem;
import api.modelo.Usuario;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.Binary;
import java.util.Iterator;
import org.bson.Document;

/**
 *
 * @author shan
 */
public class ImagemDAOMongoDB implements ImagemDAO {

    private MongoCollection<Document> collection;
    private MongoCollection<Document> collectionU;

    public ImagemDAOMongoDB() {
        collection = ConexaoMongoDB.getInstance().getDatabase().getCollection("imagem");
        collectionU = ConexaoMongoDB.getInstance().getDatabase().getCollection("usuario");
    }

    private Document criarImagemDoc(Usuario u, Imagem imagem) {
        Document doc = new Document();
        doc.append("usuario_id", u.getId());
        doc.append("imagem_id", imagem.getId());
        doc.append("nome", imagem.getNome());
        doc.append("tamanho", imagem.getTamanho());
        doc.append("tipo", imagem.getTipo());
        doc.append("imagem", imagem.getBytes());
        return doc;
    }

    private Document criarImagemDocCriar(Usuario u, Imagem imagem) {
        Document doc = new Document();
        doc.append("usuario_id", u.getId());
        doc.append("imagem_id", u.getNumeroFotos());
        doc.append("nome", imagem.getNome());
        doc.append("tamanho", imagem.getTamanho());
        doc.append("tipo", imagem.getTipo());
        doc.append("imagem", imagem.getBytes());
        return doc;
    }

    public Imagem criarImagemObj(Document doc) {
        Imagem img = new Imagem();
        img.setId(doc.getInteger("imagem_id"));
        img.setNome(doc.getString("nome"));
        img.setTamanho(doc.getString("tamanho"));
        img.setTipo(doc.getString("tipo"));
        Binary binary = (Binary) doc.get("imagem");
        img.setImage(binary.getData());

        return img;
    }

    @Override
    public Imagem createImagem(Usuario u, Imagem imagem) {
        Document doc = criarImagemDocCriar(u, imagem);
        collection.insertOne(doc);
        incrementeNumeroFotos(u);
        Iterator<Document> userIter = collection.find(doc).iterator();
        return criarImagemObj(userIter.next());
    }

    @Override
    public Imagem readById(int usuario_id, int imagem_id) {
        Document doc = new Document();
        doc.put("usuario_id", usuario_id);
        doc.put("imagem_id", imagem_id);
        Iterator<Document> userIter = collection.find(doc).iterator();
        Imagem imagem = null;
        if (userIter.hasNext()) {
            imagem = criarImagemObj(userIter.next());
        }
        return imagem;
    }

    @Override
    public Galeria readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Galeria readByUsuario(Usuario usuario) {
        Document doc = new Document();
        doc.put("usuario_id", usuario.getId());
        Iterator<Document> userIter = collection.find(doc).iterator();
        Galeria galeria = new Galeria();
        while (userIter.hasNext()) {
            Imagem imagem = criarImagemObj(userIter.next());
            galeria.add(imagem);
        }
        return galeria;

    }

    @Override
    public Imagem updateById(int usuario_id, Imagem imagem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteById(int usuario_id, int imagem_id) {
        Document doc = new Document();
        doc.put("imagem_id", imagem_id);
        DeleteResult resultado = collection.deleteOne(doc);
        int qnt = (int) resultado.getDeletedCount();
        return qnt;

    }

    @Override
    public int countImagemUsuario(int usuario_id) {
        Long l = collection.count(new Document("usuario_id", usuario_id));
        return l.intValue();
    }

    private void incrementeNumeroFotos(Usuario u) {
        Document querry = new Document("usuario_id", u.getId());
        Document update = new Document("$inc", new Document("numeroFotos", 1));
        collectionU.updateOne(querry, update);
    }

}
