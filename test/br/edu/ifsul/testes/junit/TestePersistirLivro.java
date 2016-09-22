/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Formato;
import br.edu.ifsul.modelo.Idioma;
import br.edu.ifsul.modelo.Livro;
import java.util.Calendar;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ROBSON
 */
public class TestePersistirLivro {
    EntityManager em;
    public TestePersistirLivro() {
    }
    
    @Before
    public void setUp() {
        em = EntityManagerUtil.getEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
    }
    
    @Test
    public void teste(){
        boolean exception = false;
        try {
            Livro l = new Livro();
            Catalogo c = em.find(Catalogo.class, 4);
            l.setISBN("1231231");
            l.setTitulo("Persistencia testes");
            l.setResumo("Testanndo persistencia com JPA");
            l.setEditora("Nuova");
            l.setDataPublicacao(Calendar.getInstance());
            l.setAtivo(true);
            l.setFormato(em.find(Formato.class, 1));
            l.setIdioma(em.find(Idioma.class, 1));
            l.setDataCadastro(Calendar.getInstance());
            l.setNumeroPaginas(87);
            l.setValor(76.95);
            l.setCodigoBarras("798465132");
            c.adicionarLivro(l);
            Autor a = em.find(Autor.class, 2);
            l.getAutorLivro().add(a);
            Validator validador
                    = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Livro>> erros = validador.validate(l);
            if (erros.size() > 0) {
                for (ConstraintViolation<Livro> erro : erros) {
                    System.out.println("Erro: " + erro.getMessage());
                }
                exception = true;
            } else {
            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();
            }
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado (false) com o que ocorreu (valor de exception)
        Assert.assertEquals(false, exception);
    }
    
}