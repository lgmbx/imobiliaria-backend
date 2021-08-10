package com.imobiliaria.imobiliaria.controllers;

import com.imobiliaria.imobiliaria.entities.Imovel;
import com.imobiliaria.imobiliaria.models.ImovelSelectOption;
import com.imobiliaria.imobiliaria.services.ImovelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/imoveis")
public class ImovelController {

    private final ImovelService service;

    public ImovelController(ImovelService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<Page<Imovel>> getAllImoveisPageable(
            @PageableDefault(page = 0, size = 10, sort = "categoria", direction = Sort.Direction.ASC) Pageable pageable){

        Page<Imovel> imoveis = service.findAllPageable(pageable);

        return ResponseEntity.ok().body(imoveis);
    }

    @PostMapping("/create")
    public ResponseEntity<Imovel> createImovel(@Valid @RequestBody Imovel imovel){
        Imovel imovelEntity = service.create(imovel);
        return new ResponseEntity<>(imovelEntity, HttpStatus.CREATED);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Imovel> getImovelById(@PathVariable("codigo") Long codigo){
        Imovel imovel = service.getById(codigo);
        return new ResponseEntity<>(imovel, HttpStatus.OK);
    }

    @PutMapping("/create/{codigo}")
    public ResponseEntity<Imovel> updateImovel(@PathVariable("codigo") Long codigo,
                                               @Valid @RequestBody Imovel imovel){
        imovel = service.update(imovel);
        return new ResponseEntity<>(imovel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Boolean> deleteImovel(@PathVariable("codigo") Long codigo){
        service.delete(codigo);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Imovel>> getImoveisFilterPageable(
            @RequestParam(name = "categoria") String categoria,
            @RequestParam(name = "valor") String valor,
            @RequestParam(name = "cidade") String cidade,
            @RequestParam(name = "status") String status,
            @PageableDefault(page = 0, size = 10, sort = "categoria", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Imovel> imoveisFiltrados = service.findFilterPageable(categoria, valor, cidade, status, pageable);
        return new ResponseEntity<>(imoveisFiltrados, HttpStatus.OK);
    }

    @GetMapping("/getAllSelectOptions")
    public ResponseEntity<ImovelSelectOption> getAllSelectOptions(){
        ImovelSelectOption imovelOptions = service.getAllSelectOptions();

        return new ResponseEntity<>(imovelOptions, HttpStatus.OK);
    }
}
