package com.imobiliaria.imobiliaria.services;

import com.imobiliaria.imobiliaria.entities.Cidade;
import com.imobiliaria.imobiliaria.entities.Imovel;
import com.imobiliaria.imobiliaria.exceptions.NotFoundException;
import com.imobiliaria.imobiliaria.models.ImovelSelectOption;
import com.imobiliaria.imobiliaria.repository.CidadeRepository;
import com.imobiliaria.imobiliaria.repository.ImovelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ImovelService {


    private final ImovelRepository repository;
    private final CidadeRepository cidadeRepository;

    public ImovelService(
            ImovelRepository repository,
            CidadeRepository cidadeRepository) {
        this.repository = repository;
        this.cidadeRepository = cidadeRepository;
    }

    public Page<Imovel> findAllPageable(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Imovel getById(Long codigo){
        return repository.findById(codigo)
                .orElseThrow(() ->
                        new NotFoundException("Imovel codigo " +codigo+": não encontrada"));
    }

    public Imovel create(Imovel imovel){
        return repository.save(imovel);
    }

    public Imovel update(Imovel imovel){
        Imovel updateImovel = getById(imovel.getCodigo());

        updateImovel.setCategoria(imovel.getCategoria());
        updateImovel.setCidade(imovel.getCidade());
        updateImovel.setValor(imovel.getValor());
        updateImovel.setDescricao(imovel.getDescricao());
        updateImovel.setStatus(imovel.getStatus());

        return updateImovel;
    }

    public void delete(Long codigo){
        Imovel imovel = getById(codigo);
        repository.deleteById(imovel.getCodigo());
    }

    public Page<Imovel> findFilterPageable(String categoria, String valor, String cidade, String status, Pageable pageable){

        BigDecimal valorBig = null;
        Boolean disponibilidade = null;

        categoria = categoria.isBlank() ? null : categoria;

        cidade = cidade.isBlank() ? null : cidade;

        if(!valor.isBlank()){
            valorBig = new BigDecimal(valor);
        }

        if(!status.isBlank()){
            disponibilidade = status.toLowerCase().compareTo("disponível") == 0;
        }


        return repository.findImovelByFilter(categoria, valorBig, cidade, disponibilidade, pageable);
    }

    public ImovelSelectOption getAllSelectOptions(){
        List<String> categorias = repository.findAllSelectCategoriasOptions();

        List<Cidade> cidades = repository.findAllSelectCidadesOptions();

        List<String> status = Arrays.asList("Disponível", "Indisponível");

        return ImovelSelectOption.builder()
                .categoria(categorias)
                .cidade(cidades)
                .status(status)
                .build();

    }



}