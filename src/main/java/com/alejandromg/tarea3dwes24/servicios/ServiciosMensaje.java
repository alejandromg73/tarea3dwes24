package com.alejandromg.tarea3dwes24.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandromg.tarea3dwes24.repositorios.MensajeRepository;

	@Service
	public class ServiciosMensaje {
		@Autowired
		MensajeRepository mensajeRepo;
		
		
		/**
		 * Método para ver validar un mensaje introducido por teclado
		 * 
		 * 
		 * @param Un mensaje que se quiere validar
		 * @return True si se ha validado, false si no se ha validado
		 *
		 */
		public boolean validarMensaje(String mensaje) {
			if (mensaje == null || mensaje.trim().isEmpty()) {
				System.out.println("El mensaje está vacio.");
				return false;
			}
			if (mensaje.length() > 500) {
				System.out.println("El mensaje es muy largo");
				return false;
			}
			return true;
		}
}
