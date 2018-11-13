/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmo;

import api.modelo.Resultado;
import java.util.Collections;
import java.util.LinkedList;
/**
 *
 * @author shan
 */
public class ListaResultados extends LinkedList<Resultado> {

    public void getResultadosJSON() {
        String jsonString = "[ ";
        int count = 0;
        for (Resultado r : this) {
            jsonString += r.getResultado();
            count++;
        }
        //jsonString += 
        //Object obt = JSON.parse(jsonString);
        //return null;//lista;
    }


    public void ordenarX() {
        Collections.sort(this);
    }

    public void imprimirResultados() {
        this.forEach((r) -> {
            r.imprimirResultado();
        });
    }

    public void imprimirStringResultado() {
        String str = "";
        int y = 0;
        for (Resultado resultado : this) {
            str += resultado.getCaractere();
            y = resultado.getY();
        }
        System.out.println("Y = " + y + ". Resultado: " + str);
    }

    public String getStringResultado() {
        String str = "";
        str = this.stream().map((resultado) -> resultado.getCaractere()).reduce(str, String::concat);
        return str;
    }
}
