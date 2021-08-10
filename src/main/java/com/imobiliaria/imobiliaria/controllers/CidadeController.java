package com.imobiliaria.imobiliaria.controllers;

import com.imobiliaria.imobiliaria.entities.Cidade;
import com.imobiliaria.imobiliaria.models.CidadeSelectOptions;
import com.imobiliaria.imobiliaria.services.CidadeService;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Objects;

import static ch.qos.logback.core.joran.action.ActionConst.NULL;



@RestController
@RequestMapping(path = "api/v1/cidades")
public class CidadeController {

    private final CidadeService service;

    public CidadeController(CidadeService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<Page<Cidade>> getAllCidadesPageable(
            @PageableDefault(page = 0, size = 10, sort = "nomeCidade", direction = Sort.Direction.ASC) Pageable pageable){

        Page<Cidade> cidades = service.findAllPageable(pageable);

        return ResponseEntity.ok().body(cidades);
    }

    @PostMapping("/create")
    public ResponseEntity<Cidade> createCidade(@Valid @RequestBody Cidade cidade){
        cidade = service.create(cidade);
        return new ResponseEntity<>(cidade, HttpStatus.CREATED);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cidade> getCidadeById(@PathVariable("codigo") Long codigo){
        Cidade cidade = service.getById(codigo);
        return new ResponseEntity<>(cidade, HttpStatus.OK);
    }

    @PutMapping("/create/{codigo}")
    public ResponseEntity<Cidade> updateCidade(@PathVariable("codigo") Long codigo,
                                               @Valid @RequestBody Cidade cidade){
       cidade = service.update(cidade);
       return new ResponseEntity<>(cidade, HttpStatus.CREATED);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Boolean> deleteCidade(@PathVariable("codigo") Long codigo){
        service.delete(codigo);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Cidade>> getCidadesFilterPageable(
            @RequestParam(name = "nomeCidade") String nomeCidade,
            @RequestParam(name = "uf") String uf,
            @PageableDefault(page = 0, size = 10, sort = "nomeCidade", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Cidade> cidadesFiltradas = service.findFilterPageable(nomeCidade, uf, pageable);
        return new ResponseEntity<>(cidadesFiltradas, HttpStatus.OK);
    }

    @GetMapping("/getAllSelectOptions")
    public ResponseEntity<CidadeSelectOptions> getAllSelectOptions(){
        CidadeSelectOptions cidadeOptions = service.getAllSelectOptions();

        return new ResponseEntity<>(cidadeOptions, HttpStatus.OK);
    }



}
