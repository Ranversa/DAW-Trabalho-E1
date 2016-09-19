/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Livraria;
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
public class TestePersistirLivraria {

    EntityManager em;

    public TestePersistirLivraria() {
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
            Livraria lv = new Livraria();
            lv.setNome("Papel e Arte");
            lv.setSite("www.papelearte.com.br");
            Validator validador
                    = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Livraria>> erros = validador.validate(lv);
            if (erros.size() > 0) {
                for (ConstraintViolation<Livraria> erro : erros) {
                    System.out.println("Erro: " + erro.getMessage());
                }
                exception = true;
            } else {
                em.getTransaction().begin();
                em.persist(lv);
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
