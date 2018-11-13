/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.modelo.Galeria;
import api.modelo.Imagem;
import api.modelo.Usuario;

/**
 *
 * @author sham
 */
public interface ImagemDAO {
    
    public Imagem createImagem(Usuario usuario, Imagem imagem);
    public Imagem readById(int usuario_id, int imagem_id);
    public Galeria readAll();
    public Galeria readByUsuario(Usuario usuario);
    public Imagem updateById(int usuario_id, Imagem imagem);
    public int deleteById(int usuario_id, int imagem_id);
    public int countImagemUsuario(int usuario_id);
    
   
}
