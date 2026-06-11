package com.mag.librarymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mag.librarymanager.model.Livro.Livro;
import com.mag.librarymanager.model.Usuario.Usuario;
import com.mag.librarymanager.model.Usuario.UsuarioService;

@Controller
public class MainController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/cadastro")
    public String formUsuario(Model model){
        model.addAttribute("usuario",new Usuario());
        return "formcadastro";
    }

    @GetMapping("/crialivro")
    public String formLivro(Model model){
        model.addAttribute("livro",new Livro());
        return "formcrialivro";
    }

    @PostMapping("/cadastro")
    public String formUsuario(@ModelAttribute Usuario user, Model model){
        UsuarioService us = context.getBean(UsuarioService.class);
        us.inserirUsuario(user);
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