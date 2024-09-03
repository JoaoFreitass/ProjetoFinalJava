package com.exemplo.kanban.controller;

import com.exemplo.kanban.model.Card;
import com.exemplo.kanban.model.Lista;
import com.exemplo.kanban.service.CardService;
import com.exemplo.kanban.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private ListaService listaService;

    @GetMapping("/criar")
    public String mostrarFormularioCriar(@RequestParam Long listaId, Model model) {
        model.addAttribute("listaId", listaId);
        model.addAttribute("card", new Card());
        return "criarCard";
    }

    @PostMapping("/criar")
    public String criar(@ModelAttribute Card card, @RequestParam Long listaId) {
        Lista lista = listaService.buscarPorId(listaId);
        if (lista != null) {
            card.setLista(lista);
            cardService.salvar(card);
        }
        return "redirect:/kanban/lista";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        cardService.deletar(id);
        return "redirect:/kanban/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Card card = cardService.buscarPorId(id);
        if (card != null) {
            model.addAttribute("card", card);
            model.addAttribute("listaId", card.getLista().getId()); // Para manter a referência da lista
            return "editarCard";
        }
        return "redirect:/kanban/lista";
    }

    @PostMapping("/editar")
    public String atualizar(@ModelAttribute Card card, @RequestParam Long listaId) {
        Lista lista = listaService.buscarPorId(listaId);
        if (lista != null) {
            card.setLista(lista);
            cardService.salvar(card);
        }
        return "redirect:/kanban/lista";
    }


    @GetMapping("/alterarPrioridade/{id}")
    public String mostrarFormularioAlterarPrioridade(@PathVariable Long id, Model model) {
        Card card = cardService.buscarPorId(id);
        if (card != null) {
            model.addAttribute("card", card);
            return "alterarPrioridade";
        }
        return "redirect:/kanban/lista";
    }


    @PostMapping("/alterarPrioridade")
    public String alterarPrioridade(@RequestParam Long id, @RequestParam String prioridade) {
        Card card = cardService.buscarPorId(id);
        if (card != null) {
            card.setPrioridade(prioridade);
            cardService.salvar(card);
        }
        return "redirect:/kanban/lista";
    }
    @PostMapping("/mover")
    public String moverCard(@RequestParam Long cardId, @RequestParam Long novaListaId) {
        Card card = cardService.buscarPorId(cardId);
        Lista novaLista = listaService.buscarPorId(novaListaId);
        if (card != null && novaLista != null) {
            card.setLista(novaLista);
            cardService.salvar(card);
        }
        return "redirect:/kanban/lista";
    }
    @GetMapping("/listas")
    public String listas() {
        return "listas";
    }

    @GetMapping("/sobreacademia")
    public String sobreAcademia() {
        return "sobreacademia";
    }

    @GetMapping("/config")
    public String config() {
        return "config";
    }

    @GetMapping("/local")
    public String local() {
        return "local";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }

}
