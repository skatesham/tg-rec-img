/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.modelo;

import java.util.LinkedList;

/**
 *
 * @author sham
 */
public class Galeria extends LinkedList<Imagem> {

    public Imagem getImagemById(int id) {
        for (Imagem img :this) {
            if (img.getId() == id) {
                return img;
            }
        }
        return null;
    }

}
