package com.imobiliaria.imobiliaria.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cidades")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "codigo")
    private Long codigo;

    @NotEmpty(message = "O nome da cidade é obrigatório.")
    @Size(min = 3, message = "O nome da cidade deve conter no minimo 3 caracteres")
    @Column(name = "nome_cidade")
    @JsonProperty("nomeCidade")
    private String nomeCidade;

    @NotEmpty(message = "A sigla do estado é obrigatória")
    @Size(min = 2, max = 2, message = "A sigla do estado deve ser composta por dois caracteres")
    @Column(name = "uf")
    private String uf;



}
