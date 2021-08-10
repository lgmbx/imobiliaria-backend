package com.imobiliaria.imobiliaria.repository;

import com.imobiliaria.imobiliaria.entities.Cidade;
import com.imobiliaria.imobiliaria.models.CidadeSelectOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query(
            "   select c from Cidade c "+
                    " where c.nomeCidade = :nomeCidade "
    )
    Optional<Cidade> findCidadeByName(@Param("nomeCidade") String nomeCidade);


    @Query(
            "  select c from Cidade c " +
                    "where " +
                    "(:nomeCidade is null or c.nomeCidade = :nomeCidade) " +
                    "and (:uf is null or c.uf = :uf) "
    )
    Page<Cidade> findCidadeByFilter(
            @Param("nomeCidade") String nomeCidade,
            @Param("uf") String uf,
            Pageable pageable);

    @Query(
            " select distinct c.nomeCidade from Cidade c " +
                    "order by c.nomeCidade asc"
    )
    List<String> findAllSelectNomeCidadeOptions();

    @Query(
            " select distinct c.uf from Cidade c "+
                    "order by c.uf asc"
    )
    List<String> findAllSelectUfOptions();
}
