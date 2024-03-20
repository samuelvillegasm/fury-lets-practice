package com.mercadolibre.bootcamp_demo_java_app.api.services;

import java.io.IOException;

import javax.annotation.PostConstruct;

import com.mercadolibre.bootcamp_demo_java_app.config.InternalServerErrorException;
import com.mercadolibre.bootcamp_demo_java_app.config.NotFoundException;
import com.mercadolibre.bootcamp_demo_java_app.dtos.CurrencyConversionDto;
import com.mercadolibre.bootcamp_demo_java_app.dtos.CurrencyEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mercadolibre.bootcamp_demo_java_app.dtos.ItemDTO;
import com.mercadolibre.restclient.Response;
import com.mercadolibre.restclient.RestClient;
import com.mercadolibre.restclient.exception.ParseException;
import com.mercadolibre.restclient.exception.RestException;

@Service
public class ItemsApiService extends RestClientService{
    @Value("${meli.base.url}")
    private String baseUrl;

    private String itemsApiBaseUrl;

    private String currencyConversionApiBaseUrl;
    
    private RestClient itemsApiClient;

    public ItemsApiService() throws IOException{
    	super();
    }
    
    @PostConstruct
    public void init() throws IOException {
        itemsApiBaseUrl = String.format("%s/items/",baseUrl);
        currencyConversionApiBaseUrl = String.format("%s/currency_conversions/search",baseUrl);
        itemsApiClient = RestClient.builder()
        	    .withPool(restPool)
        	    .build();
    }

    public ItemDTO getItemInfo(String itemId) throws ParseException, RestException {
        String currentItemUrl = itemsApiBaseUrl + itemId;
        Response resp = itemsApiClient.get(currentItemUrl);
        if(resp.getStatus() == 200){
            return resp.getData(ItemDTO.class);
        }else if(resp.getStatus() == 404){
            throw new NotFoundException("Item not found");
        }else {
            throw new InternalServerErrorException("Error getting item");
        }
    }

    public Double convertPriceToUsd(CurrencyEnum currentCurrency, Double value) throws ParseException, RestException{
        String currencyConversionUrl = currencyConversionApiBaseUrl + String.format("?from=%s&to=USD", currentCurrency);
        Response resp = itemsApiClient.get(currencyConversionUrl);
        if(resp.getStatus() == 200){
            return resp.getData(CurrencyConversionDto.class).getRatio()*value;
        } else {
            throw new InternalServerErrorException("Error");
        }
    }
}
