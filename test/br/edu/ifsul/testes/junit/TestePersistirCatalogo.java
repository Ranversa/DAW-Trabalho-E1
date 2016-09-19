/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Catalogo;
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
public class TestePersistirCatalogo {
    EntityManager em;
    public TestePersistirCatalogo() {
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
            Catalogo c = new Catalogo();
            Livraria lv = em.find(Livraria.class, 1);
            c.setNome("Aventura");
            c.setDescricao("Livros das mais diversa formas de aventuras reais e ficticias");
            lv.adicionarCatalogo(c);
            Validator validador
                    = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Catalogo>> erros = validador.validate(c);
            if (erros.size() > 0) {
                for (ConstraintViolation<Catalogo> erro : erros) {
                    System.out.println("Erro: " + erro.getMessage());
                }
                exception = true;
            } else {
            em.getTransaction().begin();
            em.persist(c);
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
