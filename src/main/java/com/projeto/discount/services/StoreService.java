package com.projeto.discount.services;

import com.projeto.discount.entities.Store;
import com.projeto.discount.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository repository;

    //Retornar todos jogos
    public List<Store> findAll(){
        return repository.findAll();
    }

    //Retornar jogo por ID
    public Optional<Store> findByCodigogame(Long codigoGame){
        Optional<Store> obj = repository.findById(codigoGame);
        return obj;
    }

    //Deletar por ID
    public void deleteByCodigoGame(Long codigoGame){
        repository.deleteById(codigoGame);
    }
}
