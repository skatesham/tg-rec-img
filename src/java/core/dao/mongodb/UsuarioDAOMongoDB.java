/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao.mongodb;

import api.dao.UsuarioDAO;
import api.modelo.EnumPapeis;
import api.modelo.Papel;
import api.modelo.Usuario;
import com.mongodb.client.MongoCollection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author shan
 */
public class UsuarioDAOMongoDB implements UsuarioDAO {

    private MongoCollection<Document> collection;

    public UsuarioDAOMongoDB() {
        collection = ConexaoMongoDB.getInstance().getDatabase().getCollection("usuario");

    }

    private Document criarUsuarioDoc(Usuario usuario) {
        Document doc = new Document();
        //doc.append("usuario_id", usuario.getId());
        doc.append("usuario_id", this.countUsuarios());
        doc.append("nome", usuario.getNome());
        doc.append("email", usuario.getEmail());
        doc.append("nomeUsuario", usuario.getNomeUsuario());
        doc.append("senha", usuario.getSenha());
        doc.append("papeis", criarPapeisDoc(usuario.getPapeis()));
        doc.append("numeroFotos", 0);

        return doc;
    }

    private Usuario criarUsuarioObj(Document doc) {
        Usuario usuario = null;
        try {
            usuario = new Usuario();
            usuario.setId(doc.getInteger("usuario_id"));
            usuario.setNome(doc.getString("nome"));
            usuario.setEmail(doc.getString("email"));
            usuario.setNomeUsuario(doc.getString("nomeUsuario"));
            usuario.setSenha(doc.getString("senha"));
            usuario.setNumeroFotos(doc.getInteger("numeroFotos"));
            Document papeis = new Document();
            doc.get("papeis", papeis);
            List<Papel> papeisList = new LinkedList<>();
            for (int i = 0; i < papeis.size(); i++) {
                Papel papel = new Papel();
                papel.setDescricao(EnumPapeis.valueOf(papeis.getString(i + "")));
                papeisList.add(papel);
            }

            usuario.setPapeis(papeisList);

        } catch (Exception e) {
            System.out.println("Erro UsuarioDAOMongoDB - convertOBJ");
            e.printStackTrace();
        }

        return usuario;
    }

    private Document criarPapeisDoc(List<Papel> papeis) {
        Document doc = new Document();
        int count = 0;
        for (Papel papel : papeis) {
            doc.append(count + "", papel.getDescricao().getDescricao());
            count++;
        }
        return doc;
    }

    @Override
    public Usuario create(Usuario usuario) {
        Document doc = null;
        doc = criarUsuarioDoc(usuario);
        if (doc != null) {
            collection.insertOne(doc);
            Iterator<Document> userIter = collection.find(doc).iterator();
            return criarUsuarioObj(userIter.next());
        }
        return null;

    }

    @Override
    public Usuario readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario readByNomeUsuario(String nomeUsuario) {
        Document doc = new Document("nomeUsuario", nomeUsuario);
        Iterator<Document> userIter = collection.find(doc).iterator();
        Usuario u = null;
        if (userIter.hasNext()) {
            u = criarUsuarioObj(userIter.next());
        }
        return u;
    }

    @Override
    public Usuario readByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario update(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteByNomeUsuario(String nomeUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countUsuarios() {
        Long l = collection.count();
        return l.intValue();
    }

}
