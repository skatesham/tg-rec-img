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
public class ReconhecimentoImagem {

    final private ListaResultados resultadosMaiores;

    final private boolean debug = true;

    private List<List> resultadosTodos;
    private List<ListaResultados> listaResultadosFinais;

    /**
     * Construtor padrão
     */
    public ReconhecimentoImagem() {
        resultadosMaiores = new ListaResultados();

    }

    /**
     * Função de reconhecer padrões em imagens
     *
     * @param minimo
     * @param amostras
     * @param objeto
     * @return
     */
    public List<ListaResultados> reconhecerImagem(double minimo, List<Padrao> amostras, Padrao objeto) {

        //final double MINIMO = 0.85;
        final double MINIMO = minimo;
        final double MARGEM = 0.05;

        //calculo de tempo
        long tempoAlgoritmo;
        long tempoInicio;

        Padrao amostra;
        List<Segmento> subdivisoes;
        List<Resultado> resultadosEncontrados;
        List<ListaResultados> listaResultadoLinhas;

        //calculo tempo execução função Representação
        tempoInicio = System.currentTimeMillis();

        //buscar amostra modelo para representação
        amostra = amostras.get(0);

        /**
         * Representação
         */
        subdivisoes = representar(amostra, objeto);

        // Debug da Representação com tempo de execução
        if (debug) {
            tempoAlgoritmo = System.currentTimeMillis() - tempoInicio;
            System.out.println("Representação Finalizada: Tempo de Execução: " + tempoAlgoritmo + " Milisegundos.");
        }

        //calculo tempo execução função Classificação
        tempoInicio = System.currentTimeMillis();

        /**
         * Classificação
         */
        this.resultadosTodos = classificarPadroes(subdivisoes, amostras);

        resultadosEncontrados = classificarResultadosAlvos(resultadosTodos, MINIMO, MARGEM);

        listaResultadoLinhas = classificarLinhas(resultadosEncontrados, amostra, objeto);

        this.listaResultadosFinais = classificarResultadosPositvos(listaResultadoLinhas, amostra);

        // Debug da Classificação com tempo de execução
        if (debug) {
            tempoAlgoritmo = System.currentTimeMillis() - tempoInicio;
            System.out.println("Classificação Finalizada: Tempo de Execução: " + tempoAlgoritmo + " Milisegundos.");
        }

        return this.listaResultadosFinais;

    }

    /**
     * Função de preparar entradas para RepresentarLocais.
     *
     * @param amostra alvo de análise
     * @param objeto
     * @return Lista de segmentos
     */
    private List<Segmento> representar(Padrao amostra, Padrao objeto) {

        // Valores de limites
        int limiteY = objeto.getAltura() - amostra.getAltura();
        int limiteX = objeto.getLargura() - amostra.getLargura();

        //Valores de entrada de segmentação
        int altura = amostra.getAltura();
        int largura = amostra.getLargura();

        //Variaveis de entrada de Representação
        List<Segmento> listaObjetos = new LinkedList<>();

        for (int y = 0; y <= limiteY; y++) {
            for (int x = 0; x <= limiteX; x++) {

                // Etapa 2.1 Representar Local
                double[] atributos = representarLocal(objeto, y, x, altura, largura);
                Segmento segmento = new Segmento(x, y, atributos);

                listaObjetos.add(segmento);

            }
        }
        return listaObjetos;
    }

    /**
     * Função de representar os valores dos pixels de um local
     *
     * @param objeto
     * @param deltaX
     * @param deltaY
     * @param largura
     * @param altura
     * @return
     */
    private double[] representarLocal(Padrao objeto, int deltaX, int deltaY, int largura, int altura) {

        double[] list = new double[altura * largura];
        int count = 0;
        for (int x = deltaX; x < (deltaX + largura); x++) {
            for (int y = deltaY; y < (deltaY + altura); y++) {

                list[count] = objeto.getValorPixel(x, y);
                count++;
            }
        }
        return list;
    }

    /**
     * Função para calcular a correlação dos resultados
     *
     * @param listaObjeto
     * @param listaAmostra
     * @param x
     * @param y
     * @param nome
     * @return
     */
    private Resultado classificarCorrelacao(double[] listaObjeto, double[] listaAmostra, int x, int y, String nome) {

        Resultado resultado;
        //Pearson Correlation
        PearsonsCorrelation ps = new PearsonsCorrelation();

        double r = ps.correlation(listaObjeto, listaAmostra);

        if (r == Double.NaN) {
            r = 0;
        }
        resultado = new Resultado(x, y, r, nome);

        return resultado;
    }

    /**
     * Função de calcular correção de todos os padrões selecionados para
     * reconhecimento
     *
     * @param listaObjeto
     * @param amostras
     * @return
     */
    private List<List> classificarPadroes(List<Segmento> listaObjeto, List<Padrao> amostras) {

        List<List> resultadosBrutos = new LinkedList<>();

        amostras.stream().map((Padrao amostra) -> {
            double[] listaAmostra;
            List<Resultado> obtidos = new LinkedList<>();
            listaAmostra = amostra.buscarAtributos();
            listaObjeto.stream().map((objeto) -> {
                int x, y;
                double[] segmentos;
                String nome;
                Resultado obtido;
                x = objeto.getX();
                y = objeto.getY();
                segmentos = objeto.getSegmentos();
                obtido = classificarCorrelacao(segmentos, listaAmostra, x, y, amostra.getCaractere());
                return obtido;
            }).forEachOrdered((obtido) -> {
                obtidos.add(obtido);
            });
            return obtidos;
        }).forEachOrdered((obtidos) -> {
            resultadosBrutos.add(obtidos);
        });

        return resultadosBrutos;

    }

    /**
     * Função de busca e seleção do maior valor de resultado de correlação
     *
     * @param resultados
     * @return
     */
    public Resultado buscarMaiorResultado(List<Resultado> resultados) {

        Resultado resultado = null;
        double maior = -1;
        for (Resultado r : resultados) {
            if (r.getResultado() >= maior) {
                resultado = r;
                maior = r.getResultado();
            }
        }
        this.resultadosMaiores.add(resultado);

        return resultado;
    }

    /**
     * Função classificar resultados com valores de correção acima do
     * determinado
     *
     * @param resultados
     * @param minimo
     * @param margem
     * @return
     */
    private List<Resultado> classificarResultadosAlvos(List<List> resultados, double minimo, double margem) {

        List<Resultado> resultadosEncontrados = new LinkedList<>();

        for (List<Resultado> lista : resultados) {

            double melhorResultado;
            melhorResultado = buscarMaiorResultado(lista).getResultado();
            double maior = melhorResultado - margem;

            if (melhorResultado < minimo) {
                //maior = minimo;
                continue;
            }

            for (Resultado resultado : lista) {
                if (resultado.getResultado() >= maior) {
                    resultadosEncontrados.add(resultado);
                }
            }

        }

        return resultadosEncontrados;

    }

    /**
     * Função para classificar resultados por linha do valor de Y
     *
     * @param listaResultados
     * @param amostra
     * @param objeto
     * @return
     */
    private List<ListaResultados> classificarLinhas(List<Resultado> listaResultados, Padrao amostra, Padrao objeto) {
        int yO = objeto.getAltura();
        int yA = amostra.getAltura();
        int quantidade = yO / yA;

        yA += (yA / 2);
        
        List<ListaResultados> listaLinhas = new LinkedList<>();
        
        int ant = 0;
        for (int i = 1; i <= quantidade; i++) {

            ListaResultados listaLinha = new ListaResultados();

            for (Resultado resultado : listaResultados) {

                if (resultado.getY() <= i + (i * yA)) {

                    if (resultado.getY() >= ant) {
                        listaLinha.add(resultado);
                    }
                }
            }

            ant = i + (i * yA);
            if (!listaLinha.isEmpty()) {
                listaLinha.ordenarX();
                listaLinhas.add(listaLinha);
            }
        }

        return listaLinhas;
    }

    /**
     * Função para preparar extração dos valores positivos sem repetição
     *
     * @param listaResultadoLinhas
     * @param padrao
     * @return
     */
    public List<ListaResultados> classificarResultadosPositvos(List<ListaResultados> listaResultadoLinhas, Padrao padrao) {

        List<ListaResultados> listaDeLista = new LinkedList<>();                    
        for (ListaResultados listaRes : listaResultadoLinhas) {

            ListaResultados listaLimpa;

            //Função classificar Ondas 
            listaLimpa = classificarOndas(listaRes, padrao);

            if (!listaLimpa.isEmpty()) {
                listaDeLista.add(listaLimpa);
            }
        }
        return listaDeLista;
    }

    /**
     * Função extrair valores de resultados distintos
     *
     * @param resultadoEncontrados
     * @param padrao
     * @return
     */
    private ListaResultados classificarOndas(ListaResultados resultadoEncontrados, Padrao padrao) {
        ListaResultados listaLimpa = new ListaResultados();

        int larguraTamanho = padrao.getLargura();
        larguraTamanho -= larguraTamanho * 0.7;

        int proximo = 0;
        for (Resultado res : resultadoEncontrados) {
            if (res.getX() >= proximo) {
                listaLimpa.add(res);
                proximo = res.getX() + larguraTamanho;
            }
        }

        listaLimpa.ordenarX();

        return listaLimpa;

    }

    /**
     * Imprime todos os resultados obitidos
     */
    public void imprimirTodosResultados() {
        resultadosTodos.stream().forEach((r) -> {
            System.out.println(r.toString());
        });
    }

    /**
     * Imprimir resultados das listas em string
     */
    public void imprimirResultados() {
        listaResultadosFinais.forEach((l) -> {
            l.imprimirStringResultado();
        });
    }

    /**
     * Busca resultados maiores de cada padrão
     * @return lista de resultados
     */
    public ListaResultados getResultadosMaiores() {
        return resultadosMaiores;
    }

    /**
     * Get Todos resultado
     * @return  Lista de Lista
     */
    public List<List> getResultadosTodos() {
        return resultadosTodos;
    }

    /**
     * Get lista de lista de resultado finais do algoritmo
     * @return 
     */
    public List<ListaResultados> getListaResultadosFinais() {
        return listaResultadosFinais;
    }
    
    
    /**
     * Função de teste do objeto de correlação
     */
    public void testeThis() {
        double[] x = {0, 255};
        double[] y = {255, 0};
        PearsonsCorrelation ps = new PearsonsCorrelation();
        System.out.println(ps.correlation(x, y));
    }

}
