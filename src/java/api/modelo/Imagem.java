/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.modelo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author sham
 */
public class Imagem extends Identificador {

    private String nome;
    private String tamanho;
    private String tipo;
    private BufferedImage image;
    private InputStream inputStream;
    private byte[] bytes;
    private Resultado resultado;
    private String stringBase64;
    private List<Resultado> resultadosImagem;

    public Imagem() {
    }

    public Imagem(String nome, File file) {
        this.nome = nome;
        this.setImage(file);
    }

    public Imagem(String nome, BufferedImage imagem) {
        this.nome = nome;
        this.setImage(imagem);
    }

    public Imagem(String nome, InputStream inputStream, String tipo) {
        this.tipo = this.getFileExtension(tipo);
        this.nome = nome;
        this.setImage(inputStream);
    }

    public Imagem(int id, String nome, InputStream inputStream) {
        super(id);
        this.nome = nome;
        this.setImage(inputStream);
    }

    public Imagem(int id, String nome, File file) {
        super(id);
        this.nome = nome;
        this.setImage(file);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BufferedImage getImage() {
        return image;
    }

    //READING FROM MONGODB FUNCTION ACTIVATED
    public void setImage(byte[] bytes) {

        //SETTING THE BUFFEREDIMAGE
        try {
            this.image = ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            System.out.println("Falha Leitura Imagem - Image : " + this.getNome());
        }

        //SETTING THE BYTES ARRAY
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, this.tipo, os);
            this.bytes = os.toByteArray();
            this.inputStream = new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            System.out.println("Falha Leitura Imagem - InputStream : " + this.getNome());
        }
        //this.bytes = ((DataBufferByte) this.image.getData().getDataBuffer()).getData();

        //CREATING STRING BASE64
        this.setStringBase64();
    }

    //File Local setImagem
    public final void setImage(File file) {
        try {
            this.image = ImageIO.read(file);
            this.setTamanho(this.image.getWidth() + "x" + this.image.getHeight());
            this.tipo = getFileExtension(file);
        } catch (IOException e) {
            System.out.println("Falha Leitura Imagem - Image : " + this.getNome());
            throw new UnsupportedOperationException("Not supported FOR NOW. Try Read File");
        }
        //SETTING THE BYTES ARRAY
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, this.tipo, os);
            this.bytes = os.toByteArray();
            this.inputStream = new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            System.out.println("Falha Leitura Imagem - InputStream : " + this.getNome());
            throw new UnsupportedOperationException("Not supported FOR NOW. Try Wrte Byte ");
        }
        //this.bytes = ((DataBufferByte) this.image.getData().getDataBuffer()).getData();

        //CREATING STRING BASE64
        this.setStringBase64();
    }

    //File Local setImagem
    public final void setImage(BufferedImage imagem) {
        this.image = imagem;
        this.tipo = "png";
        //SETTING THE BYTES ARRAY
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, this.tipo, os);
            this.bytes = os.toByteArray();
            this.inputStream = new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            System.out.println("Falha Leitura Imagem - InputStream : " + this.getNome());
            throw new UnsupportedOperationException("Not supported FOR NOW. Try Wrte Byte ");
        }
        //this.bytes = ((DataBufferByte) this.image.getData().getDataBuffer()).getData();

        //CREATING STRING BASE64
        this.setStringBase64();
    }

    public final void setImage(InputStream stream) {

        //SETTING THE BUFFEREDIMAGE
        try {
            this.image = ImageIO.read(stream);
            this.setTamanho(this.image.getWidth() + "x" + this.image.getHeight());

        } catch (IOException e) {
            System.out.println("Falha Leitura Imagem - Image : " + this.getNome());
        }

        //SETTING THE BYTES ARRAY
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, this.tipo, os);
            this.bytes = os.toByteArray();
            this.inputStream = new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            System.out.println("Falha Leitura Imagem - InputStream : " + this.getNome());
        }
        //this.bytes = ((DataBufferByte) this.image.getData().getDataBuffer()).getData();

        //CREATING STRING BASE64
        this.setStringBase64();
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setInputStream(File file) {
        try {

        } catch (Exception e) {
            System.out.println("Falha: setInputStream(File file) nomeObjeto: " + this.getNome());
        }

    }

    public String getLocalEquivalente() {
        switch (nome) {
            case "Imagem Estrela Baixo":
                return "/recimg/static/img/image_estrela_completa_baixo.jpg";
            case "Imagem Estrela Meio":
                return "/recimg/static/img/image_estrela_completa_meio.jpg";
            case "Imagem Estrela Cima":
                return "/recimg/static/img/image_estrela_completa_cima.jpg";
        }
        return "/recimg/static/img/image_estrela_completa.jpg";

    }

    public byte[] getBytes() {
        return bytes;
    }

    private void setBytes(byte[] bytes) {
        this.bytes = bytes;

    }

    public String getStringBase64() {
        return stringBase64;
    }

    public void setStringBase64(String stringBase64) {
        this.stringBase64 = stringBase64;
    }

    public void setStringBase64() {
        this.stringBase64 = "data:image/" + this.getTipo() + ";base64, " + Base64.getEncoder().encodeToString(bytes);
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    private String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public List<Resultado> getResultadosImagem() {
        return resultadosImagem;
    }

    public void setResultadosImagem(List<Resultado> resultadosImagem) {
        this.resultadosImagem = resultadosImagem;
    }

    
}
