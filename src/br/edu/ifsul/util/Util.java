/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

/**
 *
 * @author ROBSON
 */
public class Util {
    
    public static String getMensagemErro(Exception e){
        while(e.getCause() !=null){
            e= (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        if(retorno.contains("chave estrangeira")){
            retorno = "registro não pode ser excluido por possuir referencia em outros objetos";
        }
        return retorno;
    }
    
}
