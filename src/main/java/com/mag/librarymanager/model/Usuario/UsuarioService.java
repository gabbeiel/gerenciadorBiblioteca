package com.mag.librarymanager.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioDAO udao;

    public void inserirUsuario(Usuario user) {
        udao.inserirUsuario(user);
    }

    public Usuario obterUsuario(int id) {
        return udao.buscarUsuarioPorId(String.valueOf(id));
    }

    public Usuario autenticarUsuario(String email, String senha) {
        return udao.autenticarUsuario(email, senha);
    }
}
