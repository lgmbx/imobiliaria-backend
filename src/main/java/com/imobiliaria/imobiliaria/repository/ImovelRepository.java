package com.imobiliaria.imobiliaria.repository;

import com.imobiliaria.imobiliaria.entities.Cidade;
import com.imobiliaria.imobiliaria.entities.Imovel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    @Query(
            "select imv from Imovel imv " +
                "where " +
                "(:categoria is null or imv.categoria = :categoria) " +
                "and (:valor is null or imv.valor = :valor) " +
                "and (:cidade is null or imv.cidade.nomeCidade = :cidade) " +
                "and (:disponibilidade is null or imv.status = :disponibilidade) "

    )
    Page<Imovel> findImovelByFilter(@Param("categoria") String categoria,
                                    @Param("valor") BigDecimal valor,
                                    @Param("cidade") String cidade,
                                    @Param("disponibilidade") Boolean disponibilidade,
                                    Pageable pageable);


    @Query(
            " select distinct imv.categoria from Imovel imv "+
                    " order by imv.categoria asc"
    )
    List<String> findAllSelectCategoriasOptions();

    @Query(
            " select distinct imv.cidade from Imovel imv "+
                " order by imv.cidade.nomeCidade asc"
    )
    List<Cidade> findAllSelectCidadesOptions();


}
