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
public class ReconhecerPadroesIdeais {

    private Resultado resultado;
    private List<List> resultados;

    public ReconhecerPadroesIdeais() {

    }

    public List<String> ReconhecerImagemMutiplos(List<Padrao> amostras, Padrao objeto) {

        double[] subdivisoesObjeto;
        List<Segmento> subdivisoes;
        List<List> todosResultados;
        List<Resultado> resultados;
        List<ListaResultados> listaResultadoLinhas;
        Padrao amostra;

        //1. Segmentação
        amostra = amostras.get(0);
        subdivisoes = segmentacao(amostra, objeto);
        //System.out.println("Resultado Segmentação: " +subdivisoes.size());

        //2. Respresentação
        subdivisoesObjeto = objeto.buscarAtributos();
        todosResultados = representarMutiplos(subdivisoes, amostras);
        //System.out.println("Resultado Representação: " + todosResultados.size());

        //3. Classificar Filtrando resultados
        resultados = filtrarResultados(todosResultados, 0.95, 0.05);

        //3.1 Classificar linha e ordens dos numeros
        listaResultadoLinhas = OrdenarResultado(resultados, amostra, objeto);

        //Print Resultados
        List<String> textos = mostrarResultado(listaResultadoLinhas);
        return textos;

    }

    private List<Segmento> segmentacao(Padrao amostra, Padrao objeto) {

        // Valores de limites
        int limiteY = objeto.getAltura() - amostra.getAltura();
        int limiteX = objeto.getLargura() - amostra.getLargura();

        //Valores de entrada de segmentação
        int altura = amostra.getAltura();
        int largura = amostra.getLargura();

        //Variaveis de entrada de Representação
        List<Segmento> listaObjetos = new LinkedList<>();

        /**
         * Etapa 2 - Segmentação
         */
        for (int y = 0; y <= limiteY; y++) {
            for (int x = 0; x <= limiteX; x++) {

                // Etapa 2.1 segmentação unitária
                double[] atributos = segmentarArea(objeto, y, x, altura, largura);
                Segmento segmento = new Segmento(x, y, atributos);

                listaObjetos.add(segmento);

            }
        }
        return listaObjetos;
    }

    // ETAPA 1.1 segmentar pequena area
    private double[] segmentarArea(Padrao objeto, int deltaX, int deltaY, int largura, int altura) {

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

    // Calculo correlação
    private Resultado representar(double[] listaObjeto, double[] listaAmostra, int x, int y, String nome) {

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

    // Etapa 3 Iterar varios PADROES
    private List<List> representarMutiplos(List<Segmento> listaObjeto, List<Padrao> amostras) {

        List<List> resultadosBrutos = new LinkedList<>();

        for (Padrao amostra : amostras) {
            double[] listaAmostra;
            List<Resultado> obtidos = new LinkedList<>();
            listaAmostra = amostra.buscarAtributos();

            for (Segmento objeto : listaObjeto) {

                int x, y;
                double[] segmentos;

                Resultado obtido = null;

                String nome;
                x = objeto.getX();
                y = objeto.getY();
                segmentos = objeto.getSegmentos();
                //representação unitaria
                obtido = representar(segmentos, listaAmostra, x, y, amostra.getCaractere());
                obtidos.add(obtido);
            }
            System.out.println("Resolvido: " + amostra.getCaractere() + "!!!");
            //System.out.println("Tamanho: "+obtidos.size());
            resultadosBrutos.add(obtidos);
        }

        this.resultados = resultadosBrutos;
        return resultadosBrutos;

    }

    // ETAPA 3.1 maior
    public Resultado buscarMaior(List<Resultado> resultados) {
        /*
        Resultado resultado;
        resultado = Collections.max(resultados, null);
        this.resultado = resultado;
        //resultado.imprimirResultado();
        return resultado;*/
        Resultado resultado = null;
        double maior = -1;
        for (Resultado r : resultados) {
            if (r.getResultado() >= maior) {
                resultado = r;
                maior = r.getResultado();
            }
        }
        this.resultado = resultado;
        //resultado.imprimirResultado();
        return resultado;
    }

    //ETAPA 3 classificar
    private List<Resultado> filtrarResultados(List<List> resultados, double minimo, double margem) {

        List<Resultado> resultadosEncontrados = new LinkedList<>();

        for (List<Resultado> lista : resultados) {

            double melhorResultado;
            melhorResultado = buscarMaior(lista).getResultado();
            double maior = melhorResultado - margem;

            if (melhorResultado < minimo) {
                maior = minimo;
                continue;
            }

            for (Resultado resultado : lista) {
                if (resultado.getResultado() >= maior) {
                    resultadosEncontrados.add(resultado);
                }
            }

            //final double valor = maior;
            //lista.removeIf(p -> p.getResultado() < valor);
            //resultadosEncontrados.addAll(lista);
        }
        return resultadosEncontrados;

    }

    //Classificação final ORDENACAO
    private List<ListaResultados> OrdenarResultado(List<Resultado> listaResultados, Padrao amostra, Padrao objeto) {
        //extraindo Y
        int yO = objeto.getAltura();
        int yA = amostra.getAltura();
        int exprecao = yA + (yA / 2);
        int quantidade = yO / exprecao;
        //System.out.println("Quantidade: " + quantidade);

        List<ListaResultados> lista = new LinkedList<>();

        int ant = 0;
        for (int i = 1; i <= quantidade; i++) {
            ListaResultados linha = new ListaResultados();
            for (Resultado resultado : listaResultados) {
                //System.out.println(i + (i * exprecao));

                if (resultado.getY() <= i + (i * exprecao)) {

                    if (resultado.getY() >= ant) {
                        linha.add(resultado);
                        //listaResultados.remove(resultado);
                    }
                }

            }
            ant = i + (i * exprecao);
            if (!linha.isEmpty()) {
                linha.ordenarX();
                lista.add(linha);
            }
        }
        //System.out.println("Quantidades de Resultados:  " + lista.size());

        return lista;
    }

    private List<String> mostrarResultado(List<ListaResultados> listaResultadoLinhas) {
        List<String> strings = new LinkedList<>();
        int i = 1;
        for (ListaResultados listaDividida : listaResultadoLinhas) {
            String numeros = "";
            int linha = -1;
            for (Resultado r : listaDividida) {
                numeros += r.getCaractere();
                linha = r.getY();
            }
            strings.add(numeros);
            System.out.println("Linha " + i + ": y=~" + linha + ": Resultado: " + numeros);
            i++;
        }
        return strings;
    }

    public Resultado getResultado() {
        return resultado;
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

    public List<List> getResultados() {
        return resultados;
    }

    public void testeThis() {

        double[] x = {0, 255};
        double[] y = {255, 0};
        PearsonsCorrelation ps = new PearsonsCorrelation();
        System.out.println(ps.correlation(x, y));
    }

}
