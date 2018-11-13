/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.modelo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author shan
 */
public class Criptografia {

    static public String criptografa(String original) {

        String senha = null;
        MessageDigest algoritmo;
        byte messageDigest[];
        StringBuilder hexString;
        try {
            algoritmo = MessageDigest.getInstance("SHA-256");// 64 letras
            //algoritmo = MessageDigest.getInstance("MD5");  // 32 letras
            messageDigest = algoritmo.digest(original.getBytes("UTF-8"));
            hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            senha = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //System.out.println("Senha normal: " + original + " - Senha criptografada: " + senha);
        return senha;

    }

}

