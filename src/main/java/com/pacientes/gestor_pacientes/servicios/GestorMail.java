/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pacientes.gestor_pacientes.servicios;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Address;

import java.util.Properties;

public class GestorMail {
    private static final String REMITENTE = "fiodor.gestorpacientes@gmail.com";
    private static final String CONTRASEÑA = "xzxb hgle duyp knuz";
    
    public static void enviarCodigo(String destinatario, String codigo){
       
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new Authenticator(){
            
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(REMITENTE, CONTRASEÑA);
            }
        
        });
        
        try{
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(REMITENTE));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject("Recuperar contraseña");
            mensaje.setText("Hola,\n Recibimos tu solicitud para recuperar tu contraseña. "
                    + "\n"
                    + "Tu código es: " + codigo
                    + "\n"
                    + "Este código vence en 10 minutos. "
                    + "\n"
                    + "Si no solicitaste el cambio ignora este correo.");
            Transport.send(mensaje);
        }catch(MessagingException e){
            e.printStackTrace();
        }   
        
        
    }
    
}
