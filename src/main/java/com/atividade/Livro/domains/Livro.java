package com.atividade.Livro.domains;

import com.atividade.Livro.domains.dtos.LivroDTO;
import com.atividade.Livro.domains.enums.Conservacao;
import com.atividade.Livro.domains.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_livro")
    private Long id;

    @NotNull
    @NotBlank
    private String titulo;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String isbn;

    @NotNull
    private int numeroPaginas;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra = LocalDate.now();

    @NotNull
    @Digits(integer = 15, fraction = 2)
    private BigDecimal valorCompra;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "status")
    private Status status;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "conservacao")
    private Conservacao conservacao;

    @ManyToOne
    @JoinColumn(name="idautor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name="ideditora")
    private Editora editora;

    public Livro() {
        this.valorCompra = BigDecimal.ZERO; //adotei que valorCompra deve ser precisamente zero
        this.status= Status.NAOLIDO;
        this.conservacao = Conservacao.BOM;
    }

    public Livro(Long id, String titulo, String isbn, int numeroPaginas, LocalDate dataCompra, BigDecimal valorCompra, Status status, Conservacao conservacao, Autor autor, Editora editora) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
        this.dataCompra = dataCompra;
        this.valorCompra = valorCompra;
        this.status = status;
        this.conservacao = conservacao;
        this.autor = autor;
        this.editora = editora;
    }

    public Livro(LivroDTO dto) {
        this.id = dto.getId();
        this.titulo = dto.getTitulo();
        this.isbn = dto.getIsbn();
        this.numeroPaginas = dto.getNumeroPaginas();
        this.dataCompra = dto.getDataCompra();
        this.valorCompra = dto.getValorCompra();

        this.autor = new Autor();
        this.autor.setId(dto.getAutor());
        this.editora = new Editora();
        this.editora.setId(dto.getEditora());
        this.status = Status.toEnum(dto.getStatus());
        this.conservacao = Conservacao.toEnum(dto.getConservacao());


    }

    public @NotNull @NotBlank String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotNull @NotBlank String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull @NotBlank String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotNull @NotBlank String isbn) {
        this.isbn = isbn;
    }

    @NotNull
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(@NotNull int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public @NotNull @Digits(integer = 15, fraction = 2) BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(@NotNull @Digits(integer = 15, fraction = 2) BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Conservacao getConservacao() {
        return conservacao;
    }

    public void setConservacao(Conservacao conservacao) {
        this.conservacao = conservacao;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return id == livro.id && numeroPaginas == livro.numeroPaginas && Objects.equals(titulo, livro.titulo) && Objects.equals(isbn, livro.isbn) && Objects.equals(dataCompra, livro.dataCompra) && Objects.equals(valorCompra, livro.valorCompra) && status == livro.status && conservacao == livro.conservacao && Objects.equals(autor, livro.autor) && Objects.equals(editora, livro.editora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, isbn, numeroPaginas, dataCompra, valorCompra, status, conservacao, autor, editora);
    }
}

