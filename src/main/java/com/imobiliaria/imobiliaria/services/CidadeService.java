package com.imobiliaria.imobiliaria.services;

import com.imobiliaria.imobiliaria.entities.Cidade;
import com.imobiliaria.imobiliaria.exceptions.DuplicatedDataException;
import com.imobiliaria.imobiliaria.exceptions.NotFoundException;
import com.imobiliaria.imobiliaria.repository.CidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class CidadeService {


    private final CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    public Page<Cidade> findAllPageable(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Cidade getById(Long codigo){
        return repository.findById(codigo)
                .orElseThrow(() ->
                        new NotFoundException("Cidade codigo " +codigo+": não encontrada"));
    }

    public Cidade create(Cidade cidade){
        isCidadeJaCadastrada(cidade);
        return repository.save(cidade);
    }

    public Cidade update(Cidade cidade){
        Cidade updateCidade = getById(cidade.getCodigo());

        if(!cidade.equals(updateCidade)){
            isCidadeJaCadastrada(cidade);
        }

        updateCidade.setNomeCidade(cidade.getNomeCidade());
        updateCidade.setUf(cidade.getUf());

        return updateCidade;
    }

    public void delete(Long codigo){
        Cidade cidade = getById(codigo);
        repository.deleteById(cidade.getCodigo());
    }

    public Page<Cidade> findFilterPageable(String nomeCidade, String uf, Pageable pageable){
       nomeCidade = nomeCidade.isBlank() ? null : nomeCidade;
       uf = uf.isBlank() ? null : uf;

       return repository.findCidadeByFilter(nomeCidade, uf, pageable);
    }

    private void isCidadeJaCadastrada(Cidade cidade){
        Optional<Cidade> c = repository.findCidadeByName(cidade.getNomeCidade());
        if(c.isPresent()){
            throw new DuplicatedDataException("Cidade já cadastrada!");
        }
    }










}
