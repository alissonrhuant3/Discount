package com.projeto.discount.services.exceptions;
//Exceção para erro no banco de dados
public class DataBaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataBaseException(String msg){
        super(msg);
    }
}
