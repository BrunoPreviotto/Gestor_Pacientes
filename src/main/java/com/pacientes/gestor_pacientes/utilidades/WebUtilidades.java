/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.utilidades;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author bruno
 */
public class WebUtilidades {
   
    
    public static String deHTMLAString(String HTML){
           
                    String htmlTrabajo = HTML;

                    Document docTrab = Jsoup.parse(htmlTrabajo);

                    return docTrab.body().text();
                    
                     
                 
    }
}
