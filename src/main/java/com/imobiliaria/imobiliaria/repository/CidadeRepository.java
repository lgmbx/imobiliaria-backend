package com.imobiliaria.imobiliaria.repository;

import com.imobiliaria.imobiliaria.entities.Cidade;
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
                    "where c.nomeCidade like %:nomeCidade% "
    )
    List<Cidade> findCidadeByFilterName(@Param("nomeCidade") String nomeCidade);
}
