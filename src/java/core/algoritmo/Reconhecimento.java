/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmo;

import api.modelo.Imagem;
import api.modelo.Resultado;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author shan
 */
public class Reconhecimento {

    private Padrao objeto;
    private List<Padrao> amostras;

    public Reconhecimento(Imagem imagem, List<Imagem> amostras) {
        this.amostras = new LinkedList<>();
        
        this.objeto = new Padrao(imagem.getImage());
        
        for(Imagem img : amostras){
            Padrao pad = new Padrao(img.getNome(), imagem.getImage());
            this.amostras.add(pad);
        }
        
    }

    public List<String> reconhecer() {

        ReconhecerPadroesIdeais recPadI = new ReconhecerPadroesIdeais();

        //iniciarEntradas();
        
        List<String> listaNumeros = recPadI.ReconhecerImagemMutiplos(amostras, objeto);
        
        //System.out.println("RESULTADOS: "+recPadI.getResultado().getResultado()+"!!!!!!!");
        return listaNumeros;
        
        
        
        
        /*Criar Gráficos
        boolean querGrafico = false;

        if (querGrafico) {

            for (int i = 0; i < 10; i++) {

                List<Resultado> resultados = recPadI.getResultados().get(i);

                Resultado maior = recPadI.buscarMaior(resultados);

                this.salvarGraficos(resultados, maior);

            }
        }
        */
    }
    

    public void salvarGraficos(List<Resultado> lR, Resultado maior) {

        Grafico graficoX = new Grafico(lR, maior, 'X');
        Grafico graficoY = new Grafico(lR, maior, 'Y');

        graficoX.salvarImagem("Graficos/X/Gráfico de Resultados do " + maior.getCaractere() + " para X");
        graficoY.salvarImagem("Graficos/Y/Gráfico de Resultados do " + maior.getCaractere() + " para Y");
    }

    public void iniciarEntradas() {

        Padrao objeto;
        File OBJETO_FILE;
        List<Padrao> padroes = new LinkedList<>();
        //OBJETO_FILE = new File("numbers/numeros_1.png");
        OBJETO_FILE = new File("static/img/numbers/pixels.png");

        //amostra
        objeto = new Padrao("", OBJETO_FILE);

        for (int opcao = 0; opcao < 10; opcao++) {
            File AMOSTRA_FILE;
            Padrao amostra;

            AMOSTRA_FILE = new File("static/img/numbers/" + opcao + ".png");

            amostra = new Padrao(opcao + "", AMOSTRA_FILE);


            padroes.add(amostra);
            //System.out.println("Padrão Imagem: " + AMOSTRA_FILE.getName() + " | Tamanho: " + amostra.getLargura() + "x" + amostra.getAltura());

        }

        //System.out.println("Objeto Cena Imagem: " + OBJETO_FILE.getName() + " | Tamanho: " + objeto.getLargura() + "x" + objeto.getAltura());
        this.objeto = objeto;
        this.amostras = padroes;
    }
}
