package com.mercadolibre.bootcamp_demo_java_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversionDto {
    private Double ratio;
}
