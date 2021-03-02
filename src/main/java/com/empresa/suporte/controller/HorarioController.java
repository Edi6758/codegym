package com.empresa.suporte.controller;

import com.empresa.suporte.model.Horario;
import com.empresa.suporte.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HorarioController {

    @Autowired
    private HorarioRepository horarioRepository;

    @GetMapping("/horario/list")
    public String listHorario(Model model) {
        model.addAttribute("horario", horarioRepository.findAll(Sort.by("id")));
        return "horario/list";
    }

    @GetMapping("/horario/add")
    public String addHorario(Model model) {
        model.addAttribute("horario", new Horario());
        return "horario/add";
    }

    @PostMapping("/horario/save")
    public String saveHorario(Horario horario) {
        try {
            if (horario != null && horario.getLimite() > 0) {
                horarioRepository.save(horario);
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
        return "redirect:/horario/view/" + horario.getId() + "/" + true;
    }

    @GetMapping("/horario/view/{id}/{salvo}")
    public String viewHorario(@PathVariable long id, @PathVariable boolean salvo, Model model) {
        model.addAttribute("horario", horarioRepository.findById(id));
        model.addAttribute("salvo", salvo);
        return "horario/view";
    }

    @GetMapping("/horario/edit/{id}")
    public String editHorario(@PathVariable long id, Model model) {

        model.addAttribute("horario", horarioRepository.findById(id));
        return "horario/edit";

    }

    @GetMapping("/horario/delete/{id}")
    public String deleteHorario(@PathVariable long id) {
        try {
            horarioRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //tratar o erro de deletar um horario cadastrado com o usuario
        }
        return "redirect:/horario/list";
    }
}
