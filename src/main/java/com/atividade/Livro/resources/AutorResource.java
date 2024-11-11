package com.atividade.Livro.resources;



import com.atividade.Livro.domains.Autor;
import com.atividade.Livro.domains.dtos.AutorDTO;
import com.atividade.Livro.services.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/autor")
public class AutorResource {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorDTO>> findAll(){
        return ResponseEntity.ok().body(autorService.findAll());

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable Integer id){
        Autor obj = this.autorService.findbyId(id);
        return ResponseEntity.ok().body(new AutorDTO(obj));
    }


    @PostMapping
    public ResponseEntity<AutorDTO> create(@Valid @RequestBody AutorDTO dto){
        Autor autor = autorService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AutorDTO> update(@PathVariable Integer id, @Valid @RequestBody AutorDTO objDto){
        Autor Obj = autorService.update(id, objDto);
        return ResponseEntity.ok().body(new AutorDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AutorDTO> delete(@PathVariable Integer id){
        autorService.delete(id);
        return  ResponseEntity.noContent().build();
    }

}

