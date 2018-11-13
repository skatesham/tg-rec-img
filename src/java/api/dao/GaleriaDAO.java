/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.modelo.Imagem;
import api.modelo.Usuario;
import java.util.List;

/**
 *
 * @author shan
 */
public interface GaleriaDAO {
    public Imagem create(Usuario usuario, Imagem imagem);
    public List<Integer> readByUsuario(Usuario usuario);
    public int readByImagem(Imagem imagem); 
}
