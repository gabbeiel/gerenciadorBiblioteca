package com.mag.librarymanager.model.Usuario;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UsuarioDAO {
    @Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
    public void init() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirUsuario(Usuario user) {
        String sql = "INSERT INTO usuario (nm_usuario, ds_email, ds_senha) VALUES (?, ?, ?)";
        Object[] obj = new Object[3];
        obj[0] = (String) user.getNome().nome();
        obj[1] = (String) user.getEmail().email();
        obj[2] = (String) user.getSenha().senha();
        jdbc.update(sql,obj);
    }

    public Usuario buscarUsuarioPorId(String id) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        return Usuario.converteUsuario(jdbc.queryForMap(sql, id));
    }

    public Usuario autenticarUsuario(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE ds_email = ? AND ds_senha = ?";
        Object[] obj = new Object[2];
        obj[0] = email;
        obj[1] = senha;
        return Usuario.converteUsuario(jdbc.queryForMap(sql, obj));
    }
}
