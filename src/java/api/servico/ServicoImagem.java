/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.servico;

import api.modelo.Galeria;
import api.modelo.Imagem;
import api.modelo.Usuario;

/**
 *
 * @author sham
 */
public interface ServicoImagem {
    public Imagem createImagem(Usuario usuario, Imagem imagem);
    public Galeria readAll();
    public Imagem readById(int usuario_id, int imagem_id);
    public Galeria readByUsuario(Usuario usuario);
    public int deleteById(Usuario usuario, int imagem_id);
    public int countImagemUsuario(int usuario_id);
}
