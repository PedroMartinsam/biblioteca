package com.atividade.Livro.domains;



import com.atividade.Livro.domains.dtos.AutorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_autor")
    private Integer id;

    @NotNull @NotBlank
    private String nome;

    @NotNull @NotBlank
    private String documentoPessoal;

    @JsonIgnore
    @OneToMany(mappedBy = "autor")
    private List<Livro> livros = new ArrayList<>();

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Autor() {
    }

    public Autor(Integer id, String nome, String documentoPessoal) {
        this.id = id;
        this.nome = nome;
        this.documentoPessoal = documentoPessoal;
    }

    public Autor(AutorDTO dto){
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.documentoPessoal = dto.getDocumentoPessoal();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotNull @NotBlank String nome) {
        this.nome = nome;
    }

    public @NotNull @NotBlank String getDocumentoPessoal() {
        return documentoPessoal;
    }

    public void setDocumentoPessoal(@NotNull @NotBlank String documentoPessoal) {
        this.documentoPessoal = documentoPessoal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return id == autor.id && Objects.equals(nome, autor.nome) && Objects.equals(documentoPessoal, autor.documentoPessoal);
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, nome, documentoPessoal);
    }
}
