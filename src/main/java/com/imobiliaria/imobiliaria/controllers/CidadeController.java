package com.imobiliaria.imobiliaria.controllers;

import com.imobiliaria.imobiliaria.entities.Cidade;
import com.imobiliaria.imobiliaria.services.CidadeService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cidades")
public class CidadeController {

    private final CidadeService service;

    public CidadeController(CidadeService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Cidade>> getCidades(){
        List<Cidade> cidades = service.findAll();
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
        System.out.println("aaa");
        service.delete(codigo);
        return ResponseEntity.ok().body(true);
    }



}
