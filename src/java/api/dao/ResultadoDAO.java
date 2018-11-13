/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.modelo.Resultado;
import java.util.List;

/**
 *
 * @author shan
 */
public interface ResultadoDAO {
   
    public Resultado createResultado(Resultado resultado);
    public List<Resultado> readResultadoByImagem(int usuario_id, int imagem_id);
    public Resultado read(int imagem_id, int padrao_id);
    public Resultado removeResultado(int usuario_id, int imagem_id, int resultado_id);
    public int countResultadoUsuario(int usuario_id);
    
}
