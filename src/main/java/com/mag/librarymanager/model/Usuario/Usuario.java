package com.mag.librarymanager.model.Usuario;

import java.util.Map;

public class Usuario {
    private UsuarioId id;
    private UsuarioNome nome;
    private UsuarioEmail email;
    private UsuarioSenha senha;

    public Usuario() {}

    public Usuario(UsuarioNome nome, UsuarioEmail email, UsuarioSenha senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(UsuarioId id, UsuarioNome nome, UsuarioEmail email, UsuarioSenha senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UsuarioId getId() {
        return id;
    }

    public UsuarioNome getNome() {
        return nome;
    }

    public UsuarioEmail getEmail() {
        return email;
    }

    public UsuarioSenha getSenha() {
        return senha;
    }

    public void setId(UsuarioId id) {
        this.id = id;
    }

    public void setNome(UsuarioNome nome) {
        this.nome = nome;
    }

    public void setEmail(UsuarioEmail email) {
        this.email = email;
    }

    public void setSenha(UsuarioSenha senha) {
        this.senha = senha;
    }

    public static Usuario converteUsuario(Map<String, Object> registros) {
        int id = (int) registros.get("id_usuario");
        String nome = (String) registros.get("nm_usuario");
        String email = (String) registros.get("ds_email");
        String senha = (String) registros.get("ds_senha");
        UsuarioId usuarioId = new UsuarioId(id);
        return new Usuario(usuarioId, new UsuarioNome(nome), new UsuarioEmail(email), new UsuarioSenha(senha));
    }
}
