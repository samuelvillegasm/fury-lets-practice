package com.mercadolibre.bootcamp_demo_java_app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConvertedPriceResponseDto {
    @JsonProperty("price_usd")
    private Double price;
    @JsonProperty("item_id")
    private String itemId;
}
