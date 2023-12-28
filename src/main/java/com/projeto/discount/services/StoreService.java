package com.projeto.discount.services;

import com.projeto.discount.entities.Store;
import com.projeto.discount.repository.StoreRepository;
import com.projeto.discount.services.exceptions.DataBaseException;
import com.projeto.discount.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository repository;

    //Retornar todos jogos!
    public List<Store> findAll(){
        return repository.findAll();
    }

    //Retornar jogo por ID!
    public Store findByCodigogame(Long codigoGame){
        Optional<Store> obj = repository.findById(codigoGame);
        return obj.orElseThrow(() -> new ResourceNotFoundException(codigoGame)); // Exceção caso não exista no banco de dados o codigogame informado!
    }

    //Deletar por ID!
    public void deleteByCodigoGame(Long codigoGame){
        try{
            if(!repository.existsById(codigoGame)) throw new ResourceNotFoundException(codigoGame);// Verifica se existe o codigogame informado acima, se existir continua, se não Lança exceção!
            repository.deleteById(codigoGame);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(codigoGame);
        }catch (DataIntegrityViolationException e){// Exceção caso ocorra algum conflito com o banco de dados!
            throw new DataBaseException(e.getMessage());
        }
    }

    //Método salvar!
    public Store save(Store store){
        return repository.save(store);
    }
}
