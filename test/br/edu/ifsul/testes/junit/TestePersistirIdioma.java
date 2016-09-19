/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Idioma;
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
public class TestePersistirIdioma {

    EntityManager em;

    public TestePersistirIdioma() {
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
    public void teste() {
        boolean exception = false;
        try {
            Idioma i = new Idioma();
            i.setNome("Francês");
            i.setSigla("Fr");
            Validator validador
                    = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Idioma>> erros = validador.validate(i);
            if (erros.size() > 0) {
                for (ConstraintViolation<Idioma> erro : erros) {
                    System.out.println("Erro: " + erro.getMessage());
                }
                exception = true;
            } else {
                em.getTransaction().begin();
                em.persist(i);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado (false) com o que ocorreu (valor de exception)
        Assert.assertEquals(false, exception);
    }

}
