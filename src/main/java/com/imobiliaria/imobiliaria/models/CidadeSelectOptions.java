package com.imobiliaria.imobiliaria.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CidadeSelectOptions {

    @JsonProperty("nomeCidade")
    List<String> nomeCidade;

    @JsonProperty("uf")
    List<String> uf;
}
