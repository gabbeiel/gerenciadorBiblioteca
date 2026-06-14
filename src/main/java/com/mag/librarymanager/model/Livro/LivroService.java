package com.mag.librarymanager.model.Livro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    LivroDAO cdao;

    public void inserirLivro(Livro livro) {
        cdao.inserirLivro(livro);
    }

    public void atualizarLivro(int id, Livro livro) {
        cdao.atualizarLivro(id, livro);
    }

    public Livro buscarLivroPorId(int id) {
        return cdao.listarLivroPorId(id);
    }

    public void excluirLivro(int id) {
        cdao.excluirLivro(id);
    }

    public List<Livro> listarTodosLivros() {
        return cdao.listarTodosLivros();
    }
    
}
