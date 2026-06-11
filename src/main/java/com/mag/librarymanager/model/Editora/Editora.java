package com.mag.librarymanager.model.Editora;

public class Editora {
    private EditoraId id;
    private EditoraNome nome;

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

}
