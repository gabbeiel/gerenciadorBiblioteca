package com.mag.librarymanager.model.Autor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    @Autowired
    AutorDAO adao;

    public List<Autor> listarTodosAutores() {
        return adao.listarTodosAutores();
    }

    public Autor buscarAutorPorId(int id) {
        return adao.buscarAutorPorId(id);
    }

    public void inserirAutor(Autor autor) {
        adao.inserirAutor(autor);
    }

    public void atualizarAutor(int id, Autor autor) {
        adao.atualizarAutor(id, autor);
    }

    public void excluirAutor(int id) {
        adao.excluirAutor(id);
    }
}
