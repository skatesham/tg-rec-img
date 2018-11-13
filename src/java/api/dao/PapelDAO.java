/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;
import api.modelo.EnumPapeis;
import api.modelo.Papel;
import java.util.List;

/**
 *
 * @author sham
 */
public interface PapelDAO {
    
    public Papel create(Papel papel);
    public Papel readById(int id);
    public Papel readByDescricao(EnumPapeis descricao);
    public List<Papel> readAll();
    public Papel update(Papel papel, Papel papelNovo);
    public boolean delete(Papel papel);
    
}
