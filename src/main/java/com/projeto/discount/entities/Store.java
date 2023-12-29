package com.projeto.discount.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Store implements Serializable { //Classe entidade store!
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoGame;

    private String titulo;
    private String subTitulo;
    private String promocao;
    private String nomeImagem;
    private String url;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private Double preco;
    private Double precoAnterior;
    private Integer desconto;

}
