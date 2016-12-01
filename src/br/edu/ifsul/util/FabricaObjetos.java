/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Livraria;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ROBSON
 */
public class FabricaObjetos {
    public static List<Livraria> carregaLivraria(){
        List<Livraria> lista = new ArrayList<>();
        Livraria l1 = new Livraria();
        l1.setId(1);
        l1.setNome("Agua Na Boca");
        l1.setSite("www.aguanaboca.com");
        Catalogo c1 = new Catalogo();
        Catalogo c2 = new Catalogo();
        c1.setId(1);
        c1.setNome("Culinaria");
        c1.setDescricao("Novidades na cozinha");
        c1.setLivraria(l1);
        c1.setId(2);
        c1.setNome("Progamação");
        c1.setDescricao("Java");
        c1.setLivraria(l1);
        l1.adicionarCatalogo(c1);
        l1.adicionarCatalogo(c2);
        lista.add(l1);
        return lista;
    }
    
}
