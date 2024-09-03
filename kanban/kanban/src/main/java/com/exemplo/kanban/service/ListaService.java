package com.exemplo.kanban.service;

import com.exemplo.kanban.model.Lista;
import com.exemplo.kanban.Repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;

    public List<Lista> listarTodas() {
        return listaRepository.findAll();
    }

    public Lista salvar(Lista lista) {
        return listaRepository.save(lista);
    }

    public void deletar(Long id) {
        listaRepository.deleteById(id);
    }

    public Lista buscarPorId(Long id) {
        Optional<Lista> optionalLista = listaRepository.findById(id);
        return optionalLista.orElse(null);
    }
}
