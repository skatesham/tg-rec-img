/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.modelo.Usuario;
import java.util.List;

/**
 *
 * @author sham
 */
public interface UsuarioDAO {
    
    public Usuario create(Usuario usuario);
    
    public Usuario readById(int id);
    
    public Usuario readByNomeUsuario(String nomeUsuario);
    
    public Usuario readByEmail(String email);
    
    public List<Usuario> readAll();
    
    public Usuario update(Usuario usuario);
    
    public boolean deleteByNomeUsuario(String nomeUsuario);
    
    public boolean deleteById(int id);
    
    public int countUsuarios();

}
