/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmo;

import api.modelo.Resultado;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

/**
 *
 * @author shan
 */
public class ReconhecimentoDeImagem {

    private List<Resultado> resultados;
    private Resultado resultado;
    private Padrao amostra;
    private Padrao objeto;

    public ReconhecimentoDeImagem() {
        this.resultados = new LinkedList<>();
    }

    
    
    
    /**
     * Classe de reconhecimento de padrão em imagem Entrada: Problema Saida:
     * Resposta
     *
     * Etapas de reconhecimento: Etapa 1 Aquisição Etapa 2 Segmentação Etapa 3
     * Representação Etapa 4 Classificação
     *
     * @param amostra
     * @param objeto
     * @return
     */
    public Resultado ReconhecerImagem(Padrao amostra, Padrao objeto) {
        /**
         * Etapa 1 - Aquisição de Imagem
         */
        this.amostra = amostra;
        this.objeto = objeto;

        // Valores de limites
        int limiteY = objeto.getAltura() - amostra.getAltura();
        int limiteX = objeto.getLargura() - amostra.getLargura();

        //Valores de entrada de segmentação
        int altura = amostra.getAltura();
        int largura = amostra.getLargura();

        //Variaveis de entrada de Representação
        double[] listaObjeto;
        double[] listaAmostra = amostra.getLista();

        /**
         * Etapa 2 - Segmentação
         */
        for (int y = 0; y <= limiteY; y++) {
            for (int x = 0; x <= limiteX; x++) {
                /**
                 * Etapa 2.1 - Segmentação Unitária
                 */
                listaObjeto = segmentarArea(y, x, altura, largura);

                /**
                 * Etapa 3 - Representação
                 */
                representar(listaObjeto, listaAmostra, x, y);

                /**
                 * Etapa 4 - Classificação
                 */
                classificar();

            }
        }
        /**
         * MELHORAMENTO: Mostrar Contador de Resultado
         */
        //System.out.println("Quantidade possibilidades: " + (limiteX * limiteY));

        /**
         * Resultado do Problema
         */
        if(resultado == null){
            listaObjeto = objeto.getLista();
            representar(listaObjeto, listaAmostra, 0, 0);
        }
        return this.resultado;
    }

    
    
    /**
     * Etapa 4 - Representação Função extrai os valores da área selecionada pela
     * segmentação. Retorna atributos da imagem.
     *
     * @param listaObjeto : double[] | Lista de valores da área segmentada
     * @param listaAmostra : double[] | lista de valores da área segmentada
     * @param x : int | Delta X
     * @param y : int | Delta Y
     * @return r : double | Resultado de correlação entre listas
     */
    private double representar(double[] listaObjeto, double[] listaAmostra, int x, int y) {

        PearsonsCorrelation ps = new PearsonsCorrelation();
        double r = ps.correlation(listaObjeto, listaAmostra);
        if (r == Double.NaN) {
            r = 0;
        }
        this.resultados.add(new Resultado(x, y, r));
        return r;
    }

    
    
    /**
     * Etapa 2 - Processo Unitário de Segmentação
     *
     * @param xP : Local indice de X
     * @param yP : Local Indice de Y
     * @param deltaX : int | amostra.getAltura()
     * @param deltaY : int | amostra.getLargura()
     * @return list : double[] | lista dos valores da área da segmentação
     * selecionada
     */
    private double[] segmentarArea(int xP, int yP, int deltaX, int deltaY) {

        double[] list = new double[deltaY * deltaX];
        int count = 0;
        for (int x = xP; x < (xP + deltaX); x++) {
            for (int y = yP; y < (yP + deltaY); y++) {

                list[count] = this.objeto.getValorPixel(x, y);
                count++;
            }
        }
        return list;
    }

    /**
     * Etapa 5 - Classificação Função de classificar objetos representados.
     * Utiliza a lista de resultados obitidos para responder o problema.
     *
     * @return resultado : Resultado
     */
    private Resultado classificar() {
        Resultado resultado = null;
        double maior = -1;
        for (Resultado r : this.resultados) {
            if (r.getResultado() >= maior) {
                resultado = r;
                maior = r.getResultado();
            }
        }
        this.resultado = resultado;
        return resultado;
    }

    /**
     * SEGMENTAÇÃO MELHORADA Classe de reconhecimento de padrão em imagem
     * Entrada: Problema Saida: Resposta
     *
     * Etapas de reconhecimento: Etapa 1 Aquisição Etapa 2 Segmentação Etapa 3
     * Representação Etapa 4 Classificação
     *
     * @param amostra
     * @param objeto
     * @return
     */
    public Resultado ReconhecerImagemMelhorado(Padrao amostra, Padrao objeto) {
        /**
         * Etapa 1 - Aquisição de Imagem
         */
        this.amostra = amostra;
        this.objeto = objeto;

        // Valores de limites
        int limiteY = objeto.getAltura() - amostra.getAltura();
        int limiteX = objeto.getLargura() - amostra.getLargura();

        //Valores de entrada de segmentação
        int altura = amostra.getAltura();
        int largura = amostra.getLargura();

        //Variaveis de entrada de Representação
        double[] listaObjeto;
        double[] listaAmostra = amostra.getLista();

        /**
         * MELHORAMENTO: Conta processo de segmentação
         */
        int contadorPassos = 0;

        /**
         * Etapa 2 - Segmentação
         */
        int y = 0;
        int puloX = 10;
        double distanciaCima = 0.15;
        double distanciaBaixo = -0.15;
        boolean flagPuloX = false;
        boolean flagPuloY = false;
        while (y < limiteY) {
            int x = 0;
            while (x < limiteX) {

                contadorPassos++;
                /**
                 * Etapa 2.1 - Segmentação Unitária
                 */
                listaObjeto = segmentarArea(y, x, altura, largura);

                /**
                 * Etapa 3 - Representação
                 */
                double r = representar(listaObjeto, listaAmostra, x, y);
                if (r < distanciaCima && r > distanciaBaixo) {
                    if (x + puloX < limiteX) {
                        x += puloX;
                        flagPuloX = true;
                    }

                } else if (x - puloX > -1 && flagPuloX) {
                    x -= puloX;
                    flagPuloX = false;
                }
                /**
                 * Etapa 4 - Classificação
                 */
                classificar();

                /**
                 * Contador do While X
                 */
                x++;
            }
            /**
             * Contador While Y
             */
            y++;
        }

        /* For do metodo continuo
        for (int y = 0; y < limiteY; y++) {
            for (int x = 0; x < limiteX; x++) {

            }
        }
         */
        /**
         * MELHORAMENTO: Mostrar Contador de Resultado
         */
        //System.out.println("Quantidade de passos na segmentação: " + contadorPassos);
        //System.out.println("Quantidade possibilidades: " + (limiteX * limiteY));

        /**
         * Resultado do Problema
         */
        return this.resultado;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public List<Resultado> getResultados() {
        return this.resultados;
    }

    public void addResultado(Resultado resultado) {
        resultados.add(resultado);
    }

    public void printResultados() {
        int count = 0;
        resultados.stream().forEach((r) -> {
            System.out.println(r.toString());
        });
    }

    public Resultado getMaior() {
        return null;
    }

    public Resultado getMenor() {
        return null;
    }

    public void testeThis() {

        double[] x = {0, 255};
        double[] y = {255, 0};
        PearsonsCorrelation ps = new PearsonsCorrelation();
        System.out.println(ps.correlation(x, y));
    }

}
