/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.modelo.algoritmo;

import core.algoritmo.ReconhecerPadroesIdeais;
import core.algoritmo.Padrao;
import api.modelo.Imagem;
import api.modelo.Resultado;
import api.servico.ServicoImagem;
import core.servico.ServicoImagemMongoDBImp;
import core.servico.ServicoPadraoMongoDBImp;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shan
 */
public class ReconhecerPadraoIgualTest {

    private Padrao objeto;
    private List<Padrao> amostras;

    @Before
    public void setUp() {

        //Iniciar padrões
        ServicoPadraoMongoDBImp servicoPadrao = ServicoPadraoMongoDBImp.getInstance();
        ServicoImagem servicoImagem = ServicoImagemMongoDBImp.getInstance();
        
        
        List<Imagem> padroes = servicoPadrao.readAll();

        List<Padrao> amostras = new LinkedList<>();

        //id 6 ao 15...
        int count = 0;
        for (Imagem img : padroes) {
            if (img.getId() > 5 && img.getId() < 16) {
                Padrao pad = new Padrao(count+"", img.getImage());
                amostras.add(pad);
                count++;
            }
        }


        Imagem alvo = servicoImagem.readById(0, 19);
        Padrao objeto = new Padrao(alvo.getImage());

        //System.out.println("Objeto Cena Imagem: " + OBJETO_FILE.getName() + " | Tamanho: " + objeto.getLargura() + "x" + objeto.getAltura());
        this.objeto = objeto;
        this.amostras = amostras;
    }

    @After
    public void tearDown() {
        this.amostras = null;
        this.objeto = null;
    }

    /**
     * Test of ReconhecerImagemMutiplos method, of class ReconhecerPadraoIgual.
     */
    @Test
    public void testReconhecerImagemMutiplos() {
        System.out.println("ReconhecerImagemMutiplos");
        ReconhecerPadroesIdeais instance = new ReconhecerPadroesIdeais();

        List<String> expResult = new LinkedList<>();
        
        expResult.add("2018");

        List<String> result = instance.ReconhecerImagemMutiplos(amostras, objeto);
        assertEquals(expResult, result);
    }



}
