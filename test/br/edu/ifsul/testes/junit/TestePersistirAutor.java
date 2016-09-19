/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Autor;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ROBSON
 */
public class TestePersistirAutor {
    EntityManager em;
    public TestePersistirAutor() {
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
            Autor a = new Autor();
            a.setNome("Afonso");
            a.setBibliografia("Nasceu em 1975, doutourado em");
            Validator validador
                    = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Autor>> erros = validador.validate(a);
            if (erros.size() > 0) {
                for (ConstraintViolation<Autor> erro : erros) {
                    System.out.println("Erro: " + erro.getMessage());
                }
                exception = true;
            } else {
            em.getTransaction().begin();
            em.persist(a);
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
