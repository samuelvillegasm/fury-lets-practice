package com.mercadolibre.bootcamp_demo_java_app.config;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
