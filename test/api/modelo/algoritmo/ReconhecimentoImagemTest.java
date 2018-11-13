/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.modelo.algoritmo;

import api.modelo.Imagem;
import api.servico.ServicoImagem;
import core.algoritmo.ListaResultados;
import core.algoritmo.Padrao;
import core.algoritmo.ReconhecimentoImagem;
import core.servico.ServicoImagemMongoDBImp;
import core.servico.ServicoPadraoMongoDBImp;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shan
 */
public class ReconhecimentoImagemTest {

    private ReconhecimentoImagem ri;
    private Padrao objeto;
    private List<Padrao> amostras;

    public ReconhecimentoImagemTest() {
    }

    @Before
    public void setUp() {
        //iniciar modulo de reconhecimento
        ri = new ReconhecimentoImagem();

        //Iniciar padrões
        ServicoPadraoMongoDBImp servicoPadrao = ServicoPadraoMongoDBImp.getInstance();
        ServicoImagem servicoImagem = ServicoImagemMongoDBImp.getInstance();

        List<Imagem> padroes = servicoPadrao.readAll();

        List<Padrao> amostras = new LinkedList<>();

        //id 6 ao 15...
        int count = 0;
        for (Imagem img : padroes) {
            if (img.getId() > 25 && img.getId() < 36) {
                Padrao pad = new Padrao(count + "", img.getImage());
                amostras.add(pad);
                count++;
            }
        }

        Imagem alvo = servicoImagem.readById(0, 8);
        System.out.println(alvo.getNome());
        Padrao objeto = new Padrao(alvo.getImage());

        //System.out.println("Objeto Cena Imagem: " + OBJETO_FILE.getName() + " | Tamanho: " + objeto.getLargura() + "x" + objeto.getAltura());
        this.objeto = objeto;
        this.amostras = amostras;
    }

    @After
    public void tearDown() {
        this.amostras = null;
        this.objeto = null;
        this.ri = null;
    }

    @Test
    public void reconhecerImagemTest() {
        
        ListaResultados lr = ri.reconhecerImagem(0.8, amostras, objeto).get(0);
        
        String expected = "854325";
        
        String actual = lr.getStringResultado();
        
        
        assertEquals(expected, actual);
    }

}
