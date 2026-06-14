package com.mag.librarymanager.model.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class LivroDAO {
    @Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
    public void init() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO livro (nm_livro, nr_isbn, ds_sinopse, id_autor, id_editora) VALUES (?, ?, ?, ?, ?)";
        Object[] obj = new Object[5];
        obj[0] = livro.getNome().nome();
        obj[1] = livro.getIsbn().isbn();
        obj[2] = livro.getSinopse().sinopse();
        obj[3] = livro.getAutor().getId().id();
        obj[4] = livro.getEditora().getId().id();
        jdbc.update(sql, obj);
    }

    public void atualizarLivro(int id, Livro livro) {
        String sql = "UPDATE livro SET nm_livro = ?, nr_isbn = ?, ds_sinopse = ?, id_autor = ?, id_editora = ? WHERE id_livro = ?";
        Object[] obj = new Object[6];
        obj[0] = livro.getNome().nome();
        obj[1] = livro.getIsbn().isbn();
        obj[2] = livro.getSinopse().sinopse();
        obj[3] = livro.getAutor().getId().id();
        obj[4] = livro.getEditora().getId().id();
        obj[5] = id;
        jdbc.update(sql, obj);
    }

    public void excluirLivro(int id) {
        String sql = "DELETE FROM livro WHERE id_livro = ?";
        jdbc.update(sql, id);
    }

    public Livro listarLivroPorId(int id) {
        String sql = "SELECT l.id_livro, l.nm_livro, l.nr_isbn, l.ds_sinopse, a.id_autor, a.nm_autor, e.id_editora, e.nm_editora " +
                     "FROM livro l " +
                     "JOIN autor a ON l.id_autor = a.id_autor " +
                     "JOIN editora e ON l.id_editora = e.id_editora " +
                     "WHERE l.id_livro = ?";
        return Livro.converteLivro(jdbc.queryForMap(sql, id));
    }

    public List<Livro> listarTodosLivros() {
        String sql = "SELECT l.id_livro, l.nm_livro, l.nr_isbn, l.ds_sinopse, a.id_autor, a.nm_autor, e.id_editora, e.nm_editora " +
                     "FROM livro l " +
                     "JOIN autor a ON l.id_autor = a.id_autor " +
                     "JOIN editora e ON l.id_editora = e.id_editora";
        List<Map<String, Object>> listaRegistros = jdbc.queryForList(sql);
        ArrayList<Livro> aux = new ArrayList<>();
        for (Map<String, Object> registro : listaRegistros) {
            aux.add(Livro.converteLivro(registro));
        }
        return aux;
    }
}
