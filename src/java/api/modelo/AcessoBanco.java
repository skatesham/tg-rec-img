/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.modelo;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author shan
 */
public class AcessoBanco {

    private static AcessoBanco uniqueInstance = null;

    public static AcessoBanco getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new AcessoBanco();
        }
        return uniqueInstance;
    }
    
    public static void resetInstance(){
        uniqueInstance = new AcessoBanco();
    }

    private Usuario usuario = null;
    private List<Papel> papeis = null;
    private Imagem imagem = null;
    private List<Imagem> padroes = null;
    private Resultado resultado = null;

    private AcessoBanco() {
        papeis = new LinkedList<>();
        padroes = new LinkedList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean hasUsuario() {
        if (usuario == null) {
            return false;
        } else {
            return true;
        }
    }

    public List<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<Papel> papeis) {
        this.papeis = papeis;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public List<Imagem> getPadroes() {
        return padroes;
    }

    public void setPadroes(List<Imagem> padroes) {
        this.padroes = padroes;
    }
    public void addPadroes(Imagem padrao) {
        this.padroes.add(padrao);
    }

    public static AcessoBanco getUniqueInstance() {
        return uniqueInstance;
    }

    public static void setUniqueInstance(AcessoBanco uniqueInstance) {
        AcessoBanco.uniqueInstance = uniqueInstance;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    
    
}
