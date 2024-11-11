package com.atividade.Livro.domains.dtos;



import com.atividade.Livro.domains.Autor;
import com.atividade.Livro.domains.Editora;
import com.atividade.Livro.domains.Livro;
import com.atividade.Livro.domains.enums.Conservacao;
import com.atividade.Livro.domains.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroDTO {

    private Long id;

    @NotNull(message = "O campo Titulo não pode ser nulo")
    @NotBlank (message = "O campo Titulo não pode estar vazio")
    private String titulo;

    @NotNull(message = "O campo ISBN não pode ser nulo")
    @NotBlank (message = "O campo ISBN não pode estar vazio")
    private String isbn;

    @NotNull (message = "O campo Número de Páginas não pode ser nulo")
    private int numeroPaginas;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra = LocalDate.now();

    @NotNull (message = "O campo Valor de Compra não pode ser nulo")
    @Digits(integer = 15, fraction = 2)
    private BigDecimal valorCompra;

    private int status;
    private int conservacao;

    @NotNull(message = "O campo Autor é requerido")
    private int Autor;

    @NotNull(message = "O campo Editora é requerido")
    private int Editora;

    public LivroDTO() {
    }

    public LivroDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.isbn = livro.getIsbn();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.dataCompra = livro.getDataCompra();
        this.valorCompra = livro.getValorCompra();
        this.status = livro.getStatus().getId();
        this.conservacao = livro.getConservacao().getId();
        Autor = livro.getAutor().getId();
        Editora = livro.getEditora().getId();
    }

    @NotNull(message = "O campo Editora é requerido")
    public int getEditora() {
        return Editora;
    }

    public void setEditora(@NotNull(message = "O campo Editora é requerido") int editora) {
        Editora = editora;
    }

    @NotNull(message = "O campo Autor é requerido")
    public int getAutor() {
        return Autor;
    }

    public void setAutor(@NotNull(message = "O campo Autor é requerido") int autor) {
        Autor = autor;
    }

    public int getConservacao() {
        return conservacao;
    }

    public void setConservacao(int conservacao) {
        this.conservacao = conservacao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public @NotNull(message = "O campo Valor de Compra não pode ser nulo") @Digits(integer = 15, fraction = 2) BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(@NotNull(message = "O campo Valor de Compra não pode ser nulo") @Digits(integer = 15, fraction = 2) BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    @NotNull(message = "O campo Número de Páginas não pode ser nulo")
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(@NotNull(message = "O campo Número de Páginas não pode ser nulo") int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public @NotNull(message = "O campo ISBN não pode ser nulo") @NotBlank(message = "O campo ISBN não pode estar vazio") String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotNull(message = "O campo ISBN não pode ser nulo") @NotBlank(message = "O campo ISBN não pode estar vazio") String isbn) {
        this.isbn = isbn;
    }

    public @NotNull(message = "O campo Titulo não pode ser nulo") @NotBlank(message = "O campo Titulo não pode estar vazio") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotNull(message = "O campo Titulo não pode ser nulo") @NotBlank(message = "O campo Titulo não pode estar vazio") String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
