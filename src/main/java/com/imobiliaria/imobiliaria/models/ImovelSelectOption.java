package com.imobiliaria.imobiliaria.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imobiliaria.imobiliaria.entities.Cidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImovelSelectOption {

    @JsonProperty("categoria")
    List<String> categoria;

    @JsonProperty("cidade")
    List<Cidade> cidade;

    @JsonProperty("status")
    List<String> status;
}
