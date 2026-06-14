package com.mag.librarymanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mag.librarymanager.model.Autor.Autor;
import com.mag.librarymanager.model.Autor.AutorNome;
import com.mag.librarymanager.model.Autor.AutorService;
import com.mag.librarymanager.model.Editora.Editora;
import com.mag.librarymanager.model.Editora.EditoraNome;
import com.mag.librarymanager.model.Editora.EditoraService;
import com.mag.librarymanager.model.Livro.Livro;
import com.mag.librarymanager.model.Livro.LivroId;
import com.mag.librarymanager.model.Livro.LivroIsbn;
import com.mag.librarymanager.model.Livro.LivroNome;
import com.mag.librarymanager.model.Livro.LivroService;
import com.mag.librarymanager.model.Livro.LivroSinopse;
import com.mag.librarymanager.model.Usuario.Usuario;
import com.mag.librarymanager.model.Usuario.UsuarioService;

@Controller
public class MainController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/")
    public String index(Model model){
        LivroService ls = context.getBean(LivroService.class);
        List<Livro> livros = ls.listarTodosLivros();
        model.addAttribute("livros", livros);
        return "index";
    }

    @GetMapping("/cadastro")
    public String formUsuario(Model model){
        model.addAttribute("usuario",new Usuario());
        return "formcadastro";
    }

    @PostMapping("/cadastro")
    public String formUsuario(@ModelAttribute Usuario user, Model model){
        UsuarioService us = context.getBean(UsuarioService.class);
        us.inserirUsuario(user);
        return "redirect:/";
    }

    @GetMapping("/crialivro")
    public String formLivro(Model model){
        AutorService as = context.getBean(AutorService.class);
        List<Autor> autores = as.listarTodosAutores();
        EditoraService es = context.getBean(EditoraService.class);
        List<Editora> editoras = es.listarTodasEditoras();

        model.addAttribute("autores", autores);
        model.addAttribute("editoras", editoras);
        model.addAttribute("livro",new Livro());
        
        return "formcrialivro";
    }

    @PostMapping("/crialivro")
    public String formLivro(@ModelAttribute Livro livro, Model model){
        AutorService as = context.getBean(AutorService.class);
        Autor autor = as.buscarAutorPorId(livro.getIdAutor());
        livro.setAutor(autor);
        
        EditoraService es = context.getBean(EditoraService.class);
        Editora editora = es.buscarEditoraPorId(livro.getIdEditora());
        livro.setEditora(editora);
        
        LivroService ls = context.getBean(LivroService.class);
        ls.inserirLivro(livro);
        return "redirect:/";
    }

    @GetMapping("/criarautor")
    public String formAutor(Model model){
        AutorService as = context.getBean(AutorService.class);
        model.addAttribute("autores", as.listarTodosAutores());
        model.addAttribute("autor", new Autor());
        return "formcriaautor";
    }

    @PostMapping("/criarautor")
    public String formAutor(@ModelAttribute Autor autor, Model model){
        AutorService as = context.getBean(AutorService.class);
        as.inserirAutor(autor);
        return "redirect:/criarautor";
    }

    @PostMapping("/criarautor/editar")
    public String selecionarAutor(@RequestParam int id) {
        return "redirect:/criarautor/" + id;
    }

    @GetMapping("/criarautor/{id}")
    public String formEditaAutor(Model model, @PathVariable int id){
        model.addAttribute("id", id);
        AutorService as = context.getBean(AutorService.class);
        Autor autorAntigo = as.buscarAutorPorId(id);
        model.addAttribute("autor", autorAntigo);
        return "formeditaautor";
    }

    @PostMapping("/criarautor/{id}")
    public String formEditaAutor(@RequestParam String nome, @PathVariable int id){
        AutorService as = context.getBean(AutorService.class);
        Autor autor = new Autor();
        autor.setNome(new AutorNome(nome));
        as.atualizarAutor(id, autor);
        return "redirect:/criarautor";
    }

    @PostMapping("/criarautor/{id}/deletar")
    public String deletarAutor(@PathVariable int id) {
        AutorService as = context.getBean(AutorService.class);
        if(as.existeLivroComAutor(id)) {
            return "redirect:/criarautor";
        }
        as.excluirAutor(id);
        return "redirect:/criarautor";
    }

    @GetMapping("/criareditora")
    public String formEditora(Model model){
        EditoraService es = context.getBean(EditoraService.class);
        model.addAttribute("editoras", es.listarTodasEditoras());
        model.addAttribute("editora", new Editora());
        return "formcriaeditora";
    }

    @PostMapping("/criareditora")
    public String formEditora(@ModelAttribute Editora editora, Model model){
        EditoraService es = context.getBean(EditoraService.class);
        es.inserirEditora(editora);
        return "redirect:/criareditora";
    }

    @PostMapping("/criareditora/editar")
    public String selecionarEditora(@RequestParam int id) {
        return "redirect:/criareditora/" + id;
    }

    @GetMapping("/criareditora/{id}")
    public String formEditaEditora(Model model, @PathVariable int id){
        model.addAttribute("id", id);
        EditoraService es = context.getBean(EditoraService.class);
        Editora editoraAntiga = es.buscarEditoraPorId(id);
        model.addAttribute("editora", editoraAntiga);
        return "formeditaeditora";
    }

    @PostMapping("/criareditora/{id}")
    public String formEditaEditora(@RequestParam String nome, @PathVariable int id){
        EditoraService es = context.getBean(EditoraService.class);
        Editora editora = new Editora();
        editora.setNome(new EditoraNome(nome));
        es.atualizarEditora(id, editora);
        return "redirect:/criareditora";
    }

    @PostMapping("/criareditora/{id}/deletar")
    public String deletarEditora(@PathVariable int id) {
        EditoraService es = context.getBean(EditoraService.class);
        if(es.existeLivroComEditora(id)) {
            return "redirect:/criareditora";
        }
        es.excluirEditora(id);
        return "redirect:/criareditora";
    }

    @GetMapping("/livro/{id}")
    public String formLivro(@PathVariable int id, Model model) {
        LivroService ls = context.getBean(LivroService.class);
        Livro livro = ls.buscarLivroPorId(id);
        model.addAttribute("livro", livro);
        return "formlivro";
    }

    @GetMapping("/livro/editar/{id}")
    public String formEditaLivro(@PathVariable int id, Model model) {
        LivroService ls = context.getBean(LivroService.class);
        Livro livro = ls.buscarLivroPorId(id);
        AutorService as = context.getBean(AutorService.class);
        List<Autor> autores = as.listarTodosAutores();
        EditoraService es = context.getBean(EditoraService.class);
        List<Editora> editoras = es.listarTodasEditoras();

        model.addAttribute("livro", livro);
        model.addAttribute("autores", autores);
        model.addAttribute("editoras", editoras);
        return "formeditalivro";
    }

    @PostMapping("/livro/editar/{id}")
    public String editarLivro(
            @PathVariable int id,
            @RequestParam String nome,
            @RequestParam String isbn,
            @RequestParam String sinopse,
            @RequestParam int idAutor,
            @RequestParam int idEditora) {
        Livro livro = new Livro();
        livro.setId(new LivroId(id));
        livro.setNome(new LivroNome(nome));
        livro.setIsbn(new LivroIsbn(isbn));
        livro.setSinopse(new LivroSinopse(sinopse));
        AutorService as = context.getBean(AutorService.class);
        livro.setAutor(as.buscarAutorPorId(idAutor));
        EditoraService es = context.getBean(EditoraService.class);
        livro.setEditora(es.buscarEditoraPorId(idEditora));

        LivroService ls = context.getBean(LivroService.class);
        ls.atualizarLivro(id, livro);
        return "redirect:/livro/" + id;
    }

    @PostMapping("/livro/editar/{id}/deletar")
    public String deletarLivro(@PathVariable int id) {
        LivroService ls = context.getBean(LivroService.class);
        ls.excluirLivro(id);
        return "redirect:/";
    }

    /* 
    @PostMapping("/login")
    public String login(Usuario user, HttpSession session) {
        UsuarioService us = context.getBean(UsuarioService.class);
        Usuario autenticado = us.autenticarUsuario(user.getEmail().email(), user.getSenha().senha());

        if (autenticado != null) {
            session.setAttribute("usuarioLogado", autenticado);
            return "redirect:/home";
        } else {
            return "redirect:/login?erro=credenciais-invalidas";
        }
    }*/

}