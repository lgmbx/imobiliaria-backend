package com.imobiliaria.imobiliaria.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "imoveis")
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "codigo")
    private Long codigo;

    @NotEmpty(message = "A categoria é obrigatória.")
    @Size(min = 3, message = "A categoria deve conter no minimo 3 caracteres")
    @Column(name = "categoria")
    private String categoria;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private BigDecimal valor;

    @NotNull
    @Column(name = "status")
    private Boolean status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_cidade", referencedColumnName = "codigo")
    private Cidade cidade;
}
