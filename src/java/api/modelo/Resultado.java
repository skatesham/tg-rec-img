/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.modelo;

/**
 *
 * @author shan
 */
public class Resultado extends Identificador implements Comparable<Resultado> {

    private int image_id;
    private int padrao_id;
    private int usuario_id;
    private int x;
    private int y;
    private double resultado;
    private String caractere;

    public Resultado() {
    }

    public Resultado(int usuario_id, int image_id, int padrao_id) {
        this.image_id = image_id;
        this.padrao_id = padrao_id;
        this.usuario_id = usuario_id;
    }

    public Resultado(int x, int y, double resultado) {
        this.x = x;
        this.y = y;
        this.resultado = resultado;
    }

    public Resultado(int x, int y, double resultado, String caractere) {
        this.x = x;
        this.y = y;
        this.resultado = resultado;
        this.caractere = caractere;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public int getPadrao_id() {
        return padrao_id;
    }

    public void setPadrao_id(int padrao_id) {
        this.padrao_id = padrao_id;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
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

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    @Override
    public String toString() {
        return "Resultado{" + "image_id=" + image_id + ", padrao_id=" + padrao_id + ", usuario_id=" + usuario_id + ", x=" + x + ", y=" + y + ", resultado=" + resultado + " - Caractere: " + caractere + '}';
    }

    public void imprimirResultado() {
        System.out.println("Indice X: " + x + " - Indice Y: " + y + " - Resultado: " + resultado + " - Caractere: " + caractere);
    }

    public String getCaractere() {
        return caractere;
    }

    public void setCaractere(String caractere) {
        this.caractere = caractere;
    }

    /*
    @Override
    public int compareTo(Resultado o) {
        if (this.getResultado() > o.getResultado()) {
            return 1;
        } else if (this.getResultado() < o.getResultado()) {
            return -1;
        }
        return 0;
    }
    */

    @Override
    public int compareTo(Resultado o) {
        if (this.getX() > o.getX()) {
            return 1;
        } else if (this.getX() < o.getX()) {
            return -1;
        } 
        return 0;
    }

}
