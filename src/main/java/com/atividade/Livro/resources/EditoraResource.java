package com.atividade.Livro.resources;

import com.atividade.Livro.domains.Editora;
import com.atividade.Livro.domains.dtos.EditoraDTO;
import com.atividade.Livro.services.EditoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/editora")
public class EditoraResource {

    @Autowired
    private EditoraService editoraService;

    @GetMapping
    public ResponseEntity<List<EditoraDTO>> findAll(){
        return ResponseEntity.ok().body(editoraService.findAll());

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<EditoraDTO> findById(@PathVariable Integer id){
        Editora obj = this.editoraService.findbyId(id);
        return ResponseEntity.ok().body(new EditoraDTO(obj));
    }
    @GetMapping(value = "/cnpj/{cnpj}")
    public ResponseEntity<EditoraDTO> findByCnpj(@PathVariable String cnpj) {
        Editora obj = this.editoraService.findbyCnpj(cnpj);
        return ResponseEntity.ok().body(new EditoraDTO(obj));
    }

    @PostMapping
    public ResponseEntity<EditoraDTO> create(@Valid @RequestBody EditoraDTO dto){
        Editora editora = editoraService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(editora.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<EditoraDTO> update(@PathVariable Integer id, @Valid @RequestBody EditoraDTO objDto){
        Editora Obj = editoraService.update(id, objDto);
        return ResponseEntity.ok().body(new EditoraDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EditoraDTO> delete(@PathVariable Integer id){
        editoraService.delete(id);
        return  ResponseEntity.noContent().build();
    }

}
