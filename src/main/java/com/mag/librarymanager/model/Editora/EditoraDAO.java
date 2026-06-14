package com.mag.librarymanager.model.Editora;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class EditoraDAO {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    public void init() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public List<Editora> listarTodasEditoras() {
        String sql = "SELECT * FROM editora";
        List<Map<String, Object>> listaRegistros = jdbc.queryForList(sql);
        List<Editora> aux = new ArrayList<>();
        for (Map<String, Object> registro : listaRegistros) {
            aux.add(Editora.converte(registro));
        }
        return aux;
    }

    public Editora buscarEditoraPorId(int id) {
        String sql = "SELECT * FROM editora WHERE id_editora = ?";
        Map<String, Object> resultado = jdbc.queryForMap(sql, id);
        return Editora.converte(resultado);
    }

    public void inserirEditora(Editora editora) {
        String sql = "INSERT INTO editora (nm_editora) VALUES (?)";
        jdbc.update(sql, editora.getNome().nome());
    }

    public void atualizarEditora(int id, Editora editora) {
        String sql = "UPDATE editora SET nm_editora = ? WHERE id_editora = ?";
        Object[] obj = new Object[2];
        obj[0] = editora.getNome().nome();
        obj[1] = id;
        jdbc.update(sql, obj);
    }

    public void excluirEditora(int id) {
        String sql = "DELETE FROM editora WHERE id_editora = ?";
        jdbc.update(sql, id);
    }

    public boolean existeLivroComEditora(int id) {
        String sql = "SELECT COUNT(*) FROM livro WHERE id_editora = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
