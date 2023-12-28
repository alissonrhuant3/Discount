package com.projeto.discount.services.exceptions;
//Exceção para objeto não encontrado
public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object codigoGame){
        super("Resource not found. Id " + codigoGame);
    }
}
