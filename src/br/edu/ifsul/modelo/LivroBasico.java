/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ROBSON
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "livro_basico")
public class LivroBasico implements Serializable{
    @Id
    @NotNull(message = "O ISBN não pode ser nulo")
    @Length(max = 13, message = "O ISBN não pode ter mais de {max} caracteres")
    @NotBlank(message = "O ISBN não pode ser em branco")
    @Column(name = "ISBN", length = 13, nullable = false)
    private String ISBN;
    @NotNull(message = "O Titulo não pode ser nulo")
    @Length(max = 50, message = "O Titulo não pode ter mais de {max} caracteres")
    @NotBlank(message = "O Titulo não pode ser em branco")
    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;
    @Column(name = "resumo", columnDefinition = "text")
    private String resumo;
    @NotNull(message = "A editora não pode ser nula")
    @Length(max = 30, message = "A editora não pode ter mais de {max} caracteres")
    @NotBlank(message = "A editora não pode ser em branco")
    @Column(name = "editora", length = 30, nullable = false)
    private String editora;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "A data deve ser informada")
    @Column(name = "data_publicacao", nullable = false)
    private Calendar dataPublicacao;
    @ManyToMany
    @JoinTable(name = "Autor_Livro", 
            joinColumns = 
                @JoinColumn(name = "livro_basico", referencedColumnName = "ISBN", 
                    nullable = false),
            inverseJoinColumns = 
                @JoinColumn(name = "autor", referencedColumnName = "id", 
                    nullable = false))    
    private List<Autor> autorLivro = new ArrayList<>();

    public LivroBasico() {
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Calendar getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Calendar dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.ISBN);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LivroBasico other = (LivroBasico) obj;
        if (!Objects.equals(this.ISBN, other.ISBN)) {
            return false;
        }
        return true;
    }

    public List<Autor> getAutorLivro() {
        return autorLivro;
    }

    public void setAutorLivro(List<Autor> autorLivro) {
        this.autorLivro = autorLivro;
    }
    
    
    
}
