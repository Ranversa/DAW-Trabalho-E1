/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.LivroBasico;
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
public class TestePersistirLivroBasico {
    EntityManager em;
    public TestePersistirLivroBasico() {
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
            LivroBasico lb = new LivroBasico();
            lb.setISBN("123123123");
            lb.setTitulo("Persistencia testes");
            lb.setResumo("Testanndo persistencia com JPA");
            lb.setEditora("Nuova");
            lb.setDataPublicacao(Calendar.getInstance());
            Autor a = em.find(Autor.class, 1);
            Validator validador
                    = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<LivroBasico>> erros = validador.validate(lb);
            if (erros.size() > 0) {
                for (ConstraintViolation<LivroBasico> erro : erros) {
                    System.out.println("Erro: " + erro.getMessage());
                }
                exception = true;
            } else {
            lb.getAutorLivro().add(a);
            em.getTransaction().begin();
            em.persist(lb);
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