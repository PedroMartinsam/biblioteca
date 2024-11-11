package com.atividade.Livro.services;



import com.atividade.Livro.domains.Autor;
import com.atividade.Livro.domains.Editora;
import com.atividade.Livro.domains.Livro;
import com.atividade.Livro.domains.dtos.LivroDTO;
import com.atividade.Livro.repositories.AutorRepository;
import com.atividade.Livro.repositories.EditoraRepository;
import com.atividade.Livro.repositories.LivroRepository;
import com.atividade.Livro.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepo;

    @Autowired
    private AutorRepository autorRepo;

    @Autowired
    private EditoraRepository editoraRepo;

    public List<LivroDTO> findAll() {
        //retorna uma lista de LivroDTO
        return livroRepo.findAll().stream().map(obj -> new LivroDTO(obj)).
                collect(Collectors.toList());
    }

    public Livro findbyId(Long id) {
        Optional<Livro> obj = livroRepo.findById(id); //por que ta dando problema nessa merdaaa
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado! Id: " + id));
    }

    public Livro findbyIsbn(String isbn) {
        Optional<Livro> obj = livroRepo.findByIsbn(isbn);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado! Isbn: " + isbn));
    }


    public Livro create(LivroDTO dto) {
        dto.setId(null);
        validaLivro(dto);
        Livro obj = new Livro(dto);
        return livroRepo.save(obj);
    }

    private void validaLivro(LivroDTO dto) {
        Optional<Livro> obj = livroRepo.findByIsbn(dto.getIsbn());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("ISBN já cadastrado.");
        }

        Optional<Autor> autor = autorRepo.findById(dto.getAutor());
        if(!autor.isPresent()){
            throw new DataIntegrityViolationException("Autor - " + dto.getAutor() + " não está cadastrado.");
        }

        Optional<Editora> editora = editoraRepo.findById(dto.getEditora());
        if(!editora.isPresent()){
            throw new DataIntegrityViolationException("Editora - " + dto.getEditora() + " não está cadastrada.");
        }
    }
    public Livro update(Long id, LivroDTO objDto){
        objDto.setId(id);
        Livro oldObj = findbyId(id);
        validaLivro(objDto);
        oldObj = new Livro(objDto);
        return livroRepo.save(oldObj);
    }

    public void delete(Long id){
        Livro obj = findbyId(id);
        livroRepo.deleteById(id);
    }




}

