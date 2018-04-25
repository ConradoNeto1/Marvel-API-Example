package br.eti.wagnermessias.marvelexample.entities;

/**
 * Created by Wagner on 23/04/2018.
 */

public class Character {

    private String name;
    private String imagem;

    public Character(String name, String imagem) {
        this.name = name;
        this.imagem = imagem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
