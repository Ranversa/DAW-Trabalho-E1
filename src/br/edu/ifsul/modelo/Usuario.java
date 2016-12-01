package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "gen_usuario", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    private Integer id;
    @NotNull(message = "O apelido não pode ser nulo")
    @NotBlank(message = "O apelido deve ser informado")
    @Length(max = 20, message = "O apelido não pode ter mais de {max} caracteres")
    @Column(name = "apelido", length = 20, nullable = false, unique = true)
    private String apelido;
    @NotNull(message = "A senha não pode ser nulo")
    @NotBlank(message = "A senha deve ser informado")
    @Length(max = 20, message = "A senha não pode ter mais de {max} caracteres")
    @Column(name = "senha", length = 20, nullable = false)
    private String senha;
    @NotNull(message = "Administrador não pode ser nulo")
    @Column(name = "administrador", nullable = false)
    private Boolean administrador;
    @NotNull(message = "Ativo não pode ser nulo")
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AcessoUsuario> acessos = new ArrayList<>();

    public Usuario() {
    }
    
    public void adicionarAcesso(AcessoUsuario acesso){
        acesso.setUsuario(this);
        this.acessos.add(acesso);
    }


    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<AcessoUsuario> getAcessos() {
        return acessos;
    }

    public void setAcessos(List<AcessoUsuario> acessos) {
        this.acessos = acessos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
