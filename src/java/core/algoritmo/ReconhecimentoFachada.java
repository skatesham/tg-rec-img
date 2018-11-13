/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmo;

import api.modelo.Imagem;
import api.modelo.Resultado;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author shan
 */
public final class ReconhecimentoFachada {

    private static double minimo;
    private static Padrao imagem;
    private static List<Padrao> amostras;
    private static List<ListaResultados> resultados;
    private static ReconhecimentoImagem recimg;

    public static List<ListaResultados> reconhecerImagem(int mode, Imagem objeto, List<Imagem> padroes, int usuario_id) {

        final String NOME_IMAGEM = "small.png";

        iniciarEntradas(mode, objeto, padroes);

        recimg = new ReconhecimentoImagem();

        resultados = recimg.reconhecerImagem(minimo, amostras, imagem);
        
        resultados.forEach((lst) -> {
            for(Resultado r : lst){
                int index = Integer.parseInt(r.getCaractere());
                r.setPadrao_id(amostras.get(index).getId());
                r.setImage_id(objeto.getId());
                r.setUsuario_id(usuario_id);
            }
        }); //recimg.imprimirResultados();
        
        return resultados;
        
    }
    
    public static ListaResultados getMaiores(){
        return recimg.getResultadosMaiores();
    }

    private static void iniciarEntradas(int mode, Imagem objeto, List<Imagem> padroes) {

        int inicioSequencia;
        minimo = 0.95;
        switch (mode) {
            case 1:
                inicioSequencia = 6;
                break;
            case 2:
                inicioSequencia = 16;
                break;
            case 3:
                minimo = 0.75;
                inicioSequencia = 26;
                break;
            case 4:
                minimo = 0.60;
                inicioSequencia = 36;
                break;
            case 5:
                minimo = 0.56;
                inicioSequencia = 46;
                break;
            case 6:
                minimo = 0.60;
                inicioSequencia = 56;
                break;
            default:
                inicioSequencia = 6;
        }
        

        imagem = new Padrao(objeto.getImage());

        List<Padrao> listaPadroes = new LinkedList<>();
        int count = 0;
        for (Imagem img : padroes) {
            if (img.getId() >= inicioSequencia && img.getId() < (inicioSequencia + 10)) {
                Padrao pad = new Padrao(count + "", img.getImage());
                pad.setId(img.getId());
                listaPadroes.add(pad);
                count++;
            }
        }
        amostras = listaPadroes;
    }

    private static void iniciarEntradasConsole(String nomeImagem) {

        Padrao objeto;
        File OBJETO_FILE;
        List<Padrao> padroes = new LinkedList<>();
        //OBJETO_FILE = new File("numbers/numeros_1.png");
        //OBJETO_FILE = new File("placas_identificacao/small.png");
        OBJETO_FILE = new File("placas_identificacao/" + nomeImagem);

        //amostra
        objeto = new Padrao("", OBJETO_FILE);

        for (int valor = 0; valor < 10; valor++) {

            File AMOSTRA_FILE;
            Padrao amostra;

            AMOSTRA_FILE = new File("placas_identificacao/numeros/" + valor + ".png");
            amostra = new Padrao(valor + "", AMOSTRA_FILE);
            padroes.add(amostra);

        }

        imagem = objeto;
        amostras = padroes;
    }

    public static List<ListaResultados> getResultados() {
        return resultados;
    }

}
