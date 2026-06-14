package com.mag.librarymanager.model.Editora;

import java.util.Map;

public class Editora {
    private EditoraId id;
    private EditoraNome nome;

    public Editora() {
    }

    public Editora(EditoraId id, EditoraNome nome) {
        this.id = id;
        this.nome = nome;
    }

    public Editora(EditoraNome nome) {
        this.nome = nome;
    }

    public EditoraId getId() {
        return id;
    }

    public EditoraNome getNome() {
        return nome;
    }

    public void setId(EditoraId id) {
        this.id = id;
    }

    public void setNome(EditoraNome nome) {
        this.nome = nome;
    }

    public static Editora converte(Map<String, Object> registro) {
        int idEditora = (int) registro.get("id_editora");
        String nomeEditora = (String) registro.get("nm_editora");
        return new Editora(new EditoraId(idEditora), new EditoraNome(nomeEditora));
    }
}
