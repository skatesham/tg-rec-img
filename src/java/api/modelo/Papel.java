/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.modelo;

/**
 *
 * @author sham
 */
public class Papel extends Identificador{    
    private EnumPapeis descricao;

    public Papel() {}

    public Papel(int id, EnumPapeis descricao) {
        super(id);
        this.descricao = descricao;
    }

    public EnumPapeis getDescricao() {
        return descricao;
    }

    public void setDescricao(EnumPapeis descricao) {
        this.descricao = descricao;
    }
    
    
    
}

