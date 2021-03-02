package com.empresa.suporte.controller;

import java.awt.*;
import java.util.List;
import java.util.Optional;

import com.empresa.suporte.config.SecurityWebConfig;
import com.empresa.suporte.model.Horario;
import com.empresa.suporte.model.Permissao;
import com.empresa.suporte.model.Usuario;
import com.empresa.suporte.repository.HorarioRepository;
import com.empresa.suporte.repository.PermissaoRepository;
import com.empresa.suporte.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    PermissaoRepository permissaoRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @GetMapping("/usuario/list")
    public String listUsuario(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll(Sort.by("nome")));
        return "usuario/list";
    }

    @GetMapping("/usuario/add")
    public String addUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("horarioSegunda", horarioRepository.findBySemanaAndLimite("Segunda-feira"));
        model.addAttribute("horarioTerca", horarioRepository.findBySemanaAndLimite("Terça-feira"));
        model.addAttribute("horarioQuarta", horarioRepository.findBySemanaAndLimite("Quarta-feira"));
        model.addAttribute("horarioQuinta", horarioRepository.findBySemanaAndLimite("Quinta-feira"));
        model.addAttribute("horarioSexta", horarioRepository.findBySemanaAndLimite("Sexta-feira"));
        model.addAttribute("horarioSabado", horarioRepository.findBySemanaAndLimite("Sábado"));

        return "usuario/add";
    }

    @PostMapping("/usuario/save")
    public String saveUsuario(Usuario usuario, Model model) {
        String url = "";
        try {
            if (usuario != null) {
                boolean erro = false;
                System.out.println(usuario.getId());

                SecurityWebConfig.geraSenha(usuario);
               if(usuario.getId() == null){
                   url = "/usuario/add";
                   model.addAttribute("usuario", usuario);
                   model.addAttribute("horarioSegunda", horarioRepository.findBySemanaAndLimite("Segunda-feira"));
                   model.addAttribute("horarioTerca", horarioRepository.findBySemanaAndLimite("Terça-feira"));
                   model.addAttribute("horarioQuarta", horarioRepository.findBySemanaAndLimite("Quarta-feira"));
                   model.addAttribute("horarioQuinta", horarioRepository.findBySemanaAndLimite("Quinta-feira"));
                   model.addAttribute("horarioSexta", horarioRepository.findBySemanaAndLimite("Sexta-feira"));
                   model.addAttribute("horarioSabado", horarioRepository.findBySemanaAndLimite("Sábado"));
                   if ( usuarioRepository.findByLogin(usuario.getLogin()) != null ){
                       erro = true;
                       model.addAttribute("erroLogin", erro);

                   }else if (usuarioRepository.findByCpf(usuario.getCpf()) != null){
                       erro = true;
                       model.addAttribute("erroCpf", erro);
                   }else if (usuarioRepository.findByTelefone(usuario.getTelefone()) != null) {
                       erro = true;
                       model.addAttribute("erroTelefone", erro);

                   }else if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
                       erro = true;
                       model.addAttribute("erroEmail", erro);
                   }

               if(!erro){
                   usuarioRepository.save(usuario);
                   url = "redirect:/usuario/view/" + usuario.getId() + "/" + true;
               }
            }
        }
        }catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
               // url = "redirect:/usuario/view/" + usuario.getId() + "/" + true;
        }

        return url;

    }

    @GetMapping("/usuario/view/{id}/{salvo}")
    public String viewUsuario(@PathVariable long id, @PathVariable boolean salvo, Model model) {
        model.addAttribute("usuario", usuarioRepository.findById(id));
        model.addAttribute("salvo", salvo);
        return "usuario/view";

    }

    //----------VIEW USER----------
    @GetMapping("/usuario/viewUser")
    public String viewUser(Model model) {

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        //long idUser =  usuarioRespository.findByLogin(login).getId();
        model.addAttribute("usuario", usuarioRepository.findByLogin(login));
        return "usuario/viewUser";

    }

    @GetMapping("/usuario/edit/{id}")
    public String editUsuario(@PathVariable long id, Model model) {

        model.addAttribute("horarioSegunda", horarioRepository.findBySemanaAndLimite("Segunda-feira", id));
        model.addAttribute("horarioTerca", horarioRepository.findBySemanaAndLimite("Terça-feira", id));
        model.addAttribute("horarioQuarta", horarioRepository.findBySemanaAndLimite("Quarta-feira", id));
        model.addAttribute("horarioQuinta", horarioRepository.findBySemanaAndLimite("Quinta-feira", id));
        model.addAttribute("horarioSexta", horarioRepository.findBySemanaAndLimite("Sexta-feira", id));
        model.addAttribute("horarioSabado", horarioRepository.findBySemanaAndLimite("Sábado", id));

        model.addAttribute("usuario", usuarioRepository.findById(id));
        return "usuario/edit";

    }

    @GetMapping("/usuario/delete/{id}")
    public String deleteUsuario(@PathVariable long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return "redirect:/usuario/list";
    }

}
