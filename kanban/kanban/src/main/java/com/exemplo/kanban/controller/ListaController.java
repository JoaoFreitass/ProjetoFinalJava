package com.exemplo.kanban.controller;

import com.exemplo.kanban.model.Lista;
import com.exemplo.kanban.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/kanban")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("listas", listaService.listarTodas());
        return "listas";
    }

    @PostMapping("/lista")
    public String criar(@ModelAttribute Lista lista) {
        listaService.salvar(lista);
        return "redirect:/kanban/lista";
    }

    @PostMapping("/lista/delete/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            listaService.deletar(id);
            redirectAttributes.addFlashAttribute("successMessage", "Lista deletada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao deletar a lista.");
        }
        return "redirect:/kanban/lista";
    }

    @GetMapping("/lista/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Lista lista = listaService.buscarPorId(id);
        if (lista != null) {
            model.addAttribute("lista", lista);
            return "editarLista";
        }
        return "redirect:/kanban/lista";
    }

    @PostMapping("/lista/editar")
    public String atualizar(@ModelAttribute Lista lista) {
        listaService.salvar(lista);
        return "redirect:/kanban/lista";
    }
}
