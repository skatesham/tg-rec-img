/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.servico;

import api.modelo.Imagem;
import java.util.List;

/**
 *
 * @author sham
 */
public interface ServicoPadrao {
    
    public Imagem create(Imagem padrao);
    public Imagem readById(int id);
    public List<Imagem> readAll();
    public int countPadrao();
}
