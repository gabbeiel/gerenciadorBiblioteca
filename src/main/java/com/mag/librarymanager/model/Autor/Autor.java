package com.mag.librarymanager.model.Autor;

import java.util.Map;

public class Autor {
    public AutorId id;
    public AutorNome nome;

    public Autor(){}

    public Autor(AutorId id, AutorNome nome) {
        this.id = id;
        this.nome = nome;
    }

    public Autor(AutorNome nome) {
        this.nome = nome;
    }

    public AutorId getId() {
        return id;
    }

    public AutorNome getNome() {
        return nome;
    }

    public void setNome(AutorNome nome) {
        this.nome = nome;
    }

    public void setId(AutorId id) {
        this.id = id;
    }

    public static Autor converte(Map<String, Object> registro) {
        int idAutor = (int) registro.get("id_autor");
        String nomeAutor = (String) registro.get("nm_autor");
        return new Autor(new AutorId(idAutor), new AutorNome(nomeAutor));
    }
}
