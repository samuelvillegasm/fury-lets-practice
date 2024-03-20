package com.mercadolibre.bootcamp_demo_java_app.config;

public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException(String message){
        super(message);
    }
}
