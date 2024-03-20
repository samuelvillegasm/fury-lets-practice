package com.mercadolibre.bootcamp_demo_java_app.services;

import com.mercadolibre.bootcamp_demo_java_app.dtos.ConvertedPriceResponseDto;
import com.mercadolibre.bootcamp_demo_java_app.dtos.StandardPriceResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.bootcamp_demo_java_app.api.services.ItemsApiService;
import com.mercadolibre.bootcamp_demo_java_app.dtos.ItemDTO;
import com.mercadolibre.restclient.exception.ParseException;
import com.mercadolibre.restclient.exception.RestException;

@Service
public class ItemsService {
	private static final Logger log = LoggerFactory.getLogger(ItemsService.class);
	private ItemsApiService itemsApiService;
	
	@Autowired
	public ItemsService(ItemsApiService itemsApiService) {
		this.itemsApiService = itemsApiService;
	}
	
	public StandardPriceResponseDto getItemPrice(String itemId) throws ParseException, RestException {
		ItemDTO itemInfo = itemsApiService.getItemInfo(itemId);
		if (log.isDebugEnabled()) {
			log.debug("Item info lookup: {}",itemInfo);
		}
		return new StandardPriceResponseDto(
				itemInfo.getPrice(),
				itemInfo.getItemId()
		);
	}

	public ConvertedPriceResponseDto getItemPriceUsd(String itemId) throws ParseException, RestException {
		ItemDTO itemInfo = itemsApiService.getItemInfo(itemId);
		if (log.isDebugEnabled()) {
			log.debug("Item info lookup: {}",itemInfo);
		}
		return new ConvertedPriceResponseDto(
				itemsApiService.convertPriceToUsd(itemInfo.getCurrencyId(),itemInfo.getPrice()),
				itemInfo.getItemId()
		);
	}
}
