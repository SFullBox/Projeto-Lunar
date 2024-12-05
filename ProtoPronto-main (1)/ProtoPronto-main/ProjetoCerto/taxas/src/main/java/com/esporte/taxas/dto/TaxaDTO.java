package com.esporte.taxas.dto;

public record TaxaDTO (
    Long id, 
    String resultado, 
    int round, 
    int abates, 
    int mortes, 
    int hs, 
    String imagem
) {
}
