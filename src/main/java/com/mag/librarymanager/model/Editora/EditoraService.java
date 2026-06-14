package com.mag.librarymanager.model.Editora;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {
    @Autowired
    EditoraDAO edao;

    public List<Editora> listarTodasEditoras() {
        return edao.listarTodasEditoras();
    }

    public Editora buscarEditoraPorId(int id) {
        return edao.buscarEditoraPorId(id);
    }

    public void inserirEditora(Editora editora) {
        edao.inserirEditora(editora);
    }

    public void atualizarEditora(int id, Editora editora) {
        edao.atualizarEditora(id, editora);
    }

    public void excluirEditora(int id) {
        edao.excluirEditora(id);
    }
}
