package com.empresa.suporte.controller;

import com.empresa.suporte.model.Academia;
import com.empresa.suporte.repository.AcademiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AcademiaController {

    @Autowired
    private AcademiaRepository academiaRepository;

    @GetMapping("/academia/list")
    public String listAcademia(Model model) {
        model.addAttribute("academia", academiaRepository.findAll(Sort.by("nome")));
        return "academia/list";
    }

    @GetMapping("/academia/add")
    public String addAcademia(Model model) {
        model.addAttribute("academia", new Academia());
        return "academia/add";
    }

    @PostMapping("/academia/save")
    public String saveAcademia(Academia academia) {
        try {
            if (academia != null) {
                academiaRepository.save(academia);
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
        return "redirect:/academia/view/" + academia.getId() + "/" + true;
    }

    @GetMapping("/academia/view/{id}/{salvo}")
    public String viewAcademia(@PathVariable long id, @PathVariable boolean salvo, Model model) {
        model.addAttribute("academia", academiaRepository.findById(id));
        model.addAttribute("salvo", salvo);
        return "academia/view";
    }

    @GetMapping("/academia/edit/{id}")
    public String editAcademia(@PathVariable long id, Model model) {

        model.addAttribute("academia", academiaRepository.findById(id));
        return "academia/edit";

    }

    @GetMapping("/academia/delete/{id}")
    public String deleteAcademia(@PathVariable long id) {
        try {
            academiaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return "redirect:/academia/list";
    }
}
