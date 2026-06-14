package com.mag.librarymanager.model.Autor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class AutorDAO {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    public void init() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public List<Autor> listarTodosAutores() {
        String sql = "SELECT * FROM autor";
        List<Map<String, Object>> listaRegistros = jdbc.queryForList(sql);
        List<Autor> aux = new ArrayList<>();
        for (Map<String, Object> registro : listaRegistros) {
            aux.add(Autor.converte(registro));
        }
        return aux;
    }

    public Autor buscarAutorPorId(int id) {
        String sql = "SELECT * FROM autor WHERE id_autor = ?";
        Map<String, Object> resultado = jdbc.queryForMap(sql, id);
        return Autor.converte(resultado);
    }

    public void inserirAutor(Autor autor) {
        String sql = "INSERT INTO autor (nm_autor) VALUES (?)";
        jdbc.update(sql, autor.getNome().nome());
    }

    public void atualizarAutor(int id, Autor autor) {
        String sql = "UPDATE autor SET nm_autor = ? WHERE id_autor = ?";
        Object[] obj = new Object[2];
        obj[0] = autor.getNome().nome();
        obj[1] = id;
        jdbc.update(sql, obj);
    }

    public void excluirAutor(int id) {
        String sql = "DELETE FROM autor WHERE id_autor = ?";
        jdbc.update(sql, id);
    }

    public boolean existeLivroComAutor(int id) {
        String sql = "SELECT COUNT(*) FROM livro WHERE id_autor = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
