/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ROBSON
 */
@Entity
@Table(name = "livro")
public class Livro extends LivroBasico implements Serializable{
    @NotNull(message = "O codigo de barras não pode ser nulo")
    @Length(max = 50, message = "O codigo de barras não pode ter mais de {max} caracteres")
    @NotBlank(message = "O Codigo de barras não pode ser em branco")
    @Column(name = "codigo_barras", length = 50, nullable = false)
    private String codigoBarras;
    @NotNull(message = "O numero de paginas não pode ser nulo")
    @Column(name = "numero_paginas", length = 50, nullable = false)
    private Integer numeroPaginas;
    @NotNull(message = "É necessario informar se o livro esta ativo")
    @Column(name = "ativo", length = 50, nullable = false)
    private Boolean ativo;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "A data de cadastor deve ser informada")
    @Column(name = "data_cadastro", nullable = false)
    private Calendar dataCadastro;
    @NotNull(message = "O valor não pode ser nulo")
    @Min(value = 0, message = "O valor não pode ser negativo")
    @Column(name = "valor", nullable = false, columnDefinition = "numeric(12,2)")
    private Double valor;
    @NotNull(message = "O formato não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "formato", referencedColumnName = "id", nullable = false)
    private Formato formato;
    @NotNull(message = "O idioma não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "idioma", referencedColumnName = "id", nullable = false)
    private Idioma idioma;
    @NotNull(message = "O catalogo deve ser informado")
    @ManyToOne
    @JoinColumn(name = "catalogo_id", referencedColumnName = "id", nullable = false)
    private Catalogo catalogo;
 
    public Livro() {
    }
    
    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }


}
