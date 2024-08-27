package com.exemplo.kanban.service;

import com.exemplo.kanban.model.Card;
import com.exemplo.kanban.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> listarTodos() {
        return cardRepository.findAll();
    }

    public Card salvar(Card card) {
        return cardRepository.save(card);
    }

    public void deletar(Long id) {
        cardRepository.deleteById(id);
    }

    public Card buscarPorId(Long id) {
        return cardRepository.findById(id).orElse(null);
    }
}
