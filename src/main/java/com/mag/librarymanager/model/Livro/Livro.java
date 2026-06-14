package com.mag.librarymanager.model.Livro;

import java.util.Map;

import com.mag.librarymanager.model.Autor.Autor;
import com.mag.librarymanager.model.Autor.AutorId;
import com.mag.librarymanager.model.Autor.AutorNome;
import com.mag.librarymanager.model.Editora.Editora;
import com.mag.librarymanager.model.Editora.EditoraId;
import com.mag.librarymanager.model.Editora.EditoraNome;
 
public class Livro {
    private LivroId id;
    private LivroNome nome;
    private LivroIsbn isbn;
    private LivroSinopse sinopse;
    private Autor autor;
    private Editora editora;
    private int idAutor;
    private int idEditora;
    
    public Livro() {}

    public Livro(LivroId id, LivroNome nome, LivroIsbn isbn, LivroSinopse sinopse, Autor autor, Editora editora) {
        this.id = id;
        this.nome = nome;
        this.isbn = isbn;
        this.sinopse = sinopse;
        this.autor = autor;
        this.editora = editora;
    }
    
    public Livro(LivroNome nome, LivroIsbn isbn, LivroSinopse sinopse, Autor autor, Editora editora) {
        this.nome = nome;
        this.isbn = isbn;
        this.sinopse = sinopse;
        this.autor = autor;
        this.editora = editora;
    }

    public LivroId getId() {
        return id;
    }

    public LivroNome getNome() {
        return nome;
    }

    public LivroIsbn getIsbn() {
        return isbn;
    }

    public LivroSinopse getSinopse() {
        return sinopse;
    }

    public Autor getAutor() {
        return autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setId(LivroId id) {
        this.id = id;
    }

    public void setNome(LivroNome nome) {
        this.nome = nome;
    }

    public void setIsbn(LivroIsbn isbn) {
        this.isbn = isbn;
    }

    public void setSinopse(LivroSinopse sinopse) {
        this.sinopse = sinopse;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public int getIdEditora() {
        return idEditora;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setIdEditora(int idEditora) {
        this.idEditora = idEditora;
    }

    public static Livro converteLivro(Map<String, Object> registros) {
        int id = (int) registros.get("id_livro");
        String nome = (String) registros.get("nm_livro");
        String isbn = (String) registros.get("nr_isbn");
        String sinopse = (String) registros.get("ds_sinopse");
        Autor autor = new Autor(new AutorId((int) registros.get("id_autor")), new AutorNome((String) registros.get("nm_autor")));
        Editora editora = new Editora(new EditoraId((int) registros.get("id_editora")), new EditoraNome((String) registros.get("nm_editora")));

        return new Livro(new LivroId(id), new LivroNome(nome), new LivroIsbn(isbn), new LivroSinopse(sinopse), autor, editora);
    }
}
