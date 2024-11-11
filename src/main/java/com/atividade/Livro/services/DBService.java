package com.atividade.Livro.services;



import com.atividade.Livro.domains.Autor;
import com.atividade.Livro.domains.Editora;
import com.atividade.Livro.domains.Livro;
import com.atividade.Livro.domains.enums.Conservacao;
import com.atividade.Livro.domains.enums.Status;
import com.atividade.Livro.repositories.AutorRepository;
import com.atividade.Livro.repositories.EditoraRepository;
import com.atividade.Livro.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.LocalTime.now;

@Service
public class DBService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private LivroRepository livroRepository;

    public void initDB (){
        Autor autor01 = new Autor(null, "Autor1", "RG");
        Editora editora01 = new Editora(null, "11.111.1111111-11", "Editora1");

        Autor autor02 = new Autor(null, "Autor2", "CPF");
        Editora editora02 = new Editora(null, "22-222-2222222-22", "Editora2");

        Livro livro01 = new Livro(null, "Livro1", "1111111111111", 320,
                LocalDate.now(), new BigDecimal("99.90").setScale(2), Status.NAOLIDO, Conservacao.BOM, autor01, editora01);

        Livro livro02 = new Livro(null, "Livro2", "1231232131232", 52, LocalDate.now(), new BigDecimal("50.90").setScale(2),
                Status.NAOLIDO, Conservacao.BOM, autor02, editora02);

        Livro livro03 = new Livro(null, "Livr3",  "1234567890987", 5, LocalDate.now(), new BigDecimal("90.00").setScale(2),
                Status.NAOLIDO, Conservacao.BOM, autor02, editora01);

        autorRepository.save(autor01);
        autorRepository.save(autor02);
        editoraRepository.save(editora01);
        editoraRepository.save(editora02);
        livroRepository.save(livro01);
        livroRepository.save(livro02);
        livroRepository.save(livro03);
    }

}
