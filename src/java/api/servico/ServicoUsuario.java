/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.servico;

import api.modelo.Usuario;

/**
 *
 * @author sham
 */
public interface ServicoUsuario {
    
    public Usuario create(Usuario usuario);
    
    public Usuario readByNomeUsuario(String nomeUsuario);
    
    public Usuario updateByUsuario(Usuario usuario);
    
}
