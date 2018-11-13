/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmo;

import api.modelo.Identificador;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author shan
 */
public final class Padrao extends Identificador{

    private String caractere;
    private BufferedImage imagem = null;
    final private double[] lista;

    public Padrao(BufferedImage imagem) {
        this.imagem = imagem;
        this.lista = buscarAtributos();
    }

    public Padrao(String caratere, BufferedImage imagem) {
        this.caractere = caratere;
        this.imagem = imagem;
        this.lista = buscarAtributos();
    }

    public Padrao(String caratere, File file) {
        this.caractere = caratere;
        try {
            this.imagem = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Erro Leitura File");
        }

        this.lista = buscarAtributos();
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public Raster getRaster() {
        return imagem.getData();
    }

    public int getAltura() {
        return imagem.getData().getHeight();
    }

    public int getLargura() {
        return imagem.getData().getWidth();
    }

    public double[] buscarAtributos() {
        double[] resposta = new double[getAltura() * getLargura()];
        double[] z = null;
        int count = 0;
        for (int x = 0; x < getAltura(); x++) {
            for (int y = 0; y < getLargura(); y++) {
                double[] value = getRaster().getPixel(y, x, z);
                resposta[count] = value[0];
                count++;
            }
        }
        return resposta;
    }

    public double[] getLista() {
        return lista;
    }

    @Override
    public String toString() {
        double[] value;
        double[] z = null;
        String resposta = "";
        for (int x = 0; x < getAltura(); x++) {
            for (int y = 0; y < getLargura(); y++) {
                value = getRaster().getPixel(y, x, z);
                int valor = (int) value[0];
                resposta += valor + " ";
            }
            resposta += "\n";
        }
        return resposta;
    }

    public double getValorPixel(int x, int y) {
        double[] z = null;
        return imagem.getRaster().getPixel(y, x, z)[0];
    }

    public String getCaractere() {
        return caractere;
    }

}
