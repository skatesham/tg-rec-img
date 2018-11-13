/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.modelo.Imagem;
import java.util.List;

/**
 *
 * @author sham
 */
public interface PadraoDAO {
    
    public Imagem create(Imagem padrao);
    public Imagem readById(int id);
    public List<Imagem> readAll();
    public Imagem updateById(int id, Imagem padrao);
    public Imagem deleteById(int id);
    public int countPadrao();
    
}
