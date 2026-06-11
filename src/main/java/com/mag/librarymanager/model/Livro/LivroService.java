package com.mag.librarymanager.model.Livro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


public class LivroService {
    @Autowired
    LivroDAO cdao;

    public void inserirLivro(Livro livro) {
        cdao.inserirLivro(livro);
    }

    public Livro buscarLivroPorId(int id) {
        return cdao.listarLivroPorId(id);
    }

    public List<Livro> listarTodosLivros() {
        return cdao.listarTodosLivros();
    }
    
}
