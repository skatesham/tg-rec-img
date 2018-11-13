/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmo;

/**
 *
 * @author shan
 */
public class Segmento {

    private int x;
    private int y;
    private double[] segmentos;
    private String nome;

    public Segmento(int x, int y, double[] segmentos) {
        this.x = x;
        this.y = y;
        this.segmentos = segmentos;
    }

    public double[] getSegmentos() {
        return segmentos;
    }

    public void setSegmentos(double[] segmentos) {
        this.segmentos = segmentos;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
