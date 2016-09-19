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
@Table(name = "livraria")
public class Livraria implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_livraria", sequenceName = "seq_livraria_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_livraria", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O Nome da livrari não pode ser nula")
    @Length(max = 30, message = "O Nome da livraria não pode ter mais de {max} caracteres")
    @NotBlank(message = "O Nome da livraria não pode ser em branco")
    @Column(name = "nome", length = 30, nullable = false)    
    private String nome;
    @NotNull(message = "O Nome site da livraria não pode ser nulo")
    @Length(max = 30, message = "O sita da livraria não pode ter mais de {max} caracteres")
    @NotBlank(message = "O site da livraria não pode ser em branco")
    @Column(name = "site", length = 30, nullable = false)    
    private String site;
    @OneToMany(mappedBy = "livraria", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Catalogo> catalogos = new ArrayList<>();

    public Livraria() {
    }
public void adicionarCatalogo(Catalogo c) {
        c.setLivraria(this);
        this.catalogos.add(c);
    }

    public void removerCatalogo(int index) {
        this.catalogos.remove(index);
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Livraria other = (Livraria) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Catalogo> getCatalogos() {
        return catalogos;
    }

    public void setCatalogos(List<Catalogo> catalogos) {
        this.catalogos = catalogos;
    }
    
    
}
