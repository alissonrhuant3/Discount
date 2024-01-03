package com.projeto.discount.repository;

import com.projeto.discount.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    List<Store> findByCategoria(String categoria);
}
