/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ROBSON
 */
@Entity
@Table(name = "catalogo")
public class Catalogo implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_catalogo", sequenceName = "seq_catalogo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_catalogo", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O Nome do catalogo não pode ser nulo")
    @Length(max = 30, message = "O Nome do catalogo não pode ter mais de {max} caracteres")
    @NotBlank(message = "O Nome do catalogo não pode ser em branco")
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;
    @NotNull(message = "A descrição não pode ser nula")
    @NotBlank(message = "A descriçaõ não pode ser em branco")
    @Column(name = "descricao", columnDefinition = "text", nullable = false)
    private String descricao;
    @OneToMany(mappedBy = "catalogo", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Livro> livros = new ArrayList<>();
    @NotNull(message = "A livraria deve ser informada")
    @ManyToOne
    @JoinColumn(name = "livraria_id", referencedColumnName = "id", nullable = false)
    private Livraria livraria;

    public void adicionarLivro(Livro l) {
        l.setCatalogo(this);
        this.livros.add(l);
    }

    public void removerLivro(int index) {
        this.livros.remove(index);
    }

    public Catalogo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Catalogo other = (Catalogo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Livraria getLivraria() {
        return livraria;
    }

    public void setLivraria(Livraria livraria) {
        this.livraria = livraria;
    }

}
