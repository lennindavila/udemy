package com.bolsadeideas.springboot.backend.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.bolsadeideas.springboot.backend.chat.models.documents.Mensaje;
import com.bolsadeideas.springboot.backend.chat.models.service.ChatService;

@Controller
public class ChatController {
	
	private String[] colores = {"red","green","blue","magenta","purple","orange"};
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
 
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("Nuevo Usuario");
		}else {
			chatService.guardar(mensaje);
		}
		
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String recibeMensaje(String username) {		
		return username.concat(" esta escribiendo ...");
	}
	
	@MessageMapping("/historial")
	//@SendTo("/chat/historial")
	public void historial(String clienteId){
		webSocket.convertAndSend("/chat/historial/" + clienteId, chatService.obtenerUltimos10Mensajes());
	}
}
