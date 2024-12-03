package com.alejandromg.tarea3dwes24.vista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.alejandromg.tarea3dwes24.modelo.Planta;
import com.alejandromg.tarea3dwes24.servicios.Controlador;
import com.alejandromg.tarea3dwes24.servicios.ServiciosCredenciales;
import com.alejandromg.tarea3dwes24.servicios.ServiciosPlanta;

@Component
public class FachadaInvitado {

    @Autowired
    private ServiciosCredenciales servCred;

    @Autowired
    private ServiciosPlanta servPlanta;

    @Autowired
    private FachadaAdmin fachadaAdmin;

    @Autowired
    private FachadaPersonal fachadaPersonal;

    @Autowired
    private Controlador controlador; 

    private Scanner in = new Scanner(System.in);

    public void menuInvitado() {
        int opcion = 0;
        do {
            System.out.println("------GESTIÓN DEL VIVERO------");
            System.out.println();
            System.out.println("Selecciona una opción: ");
            System.out.println("───────────────────────────────");
            System.out.println("1. VER TODAS LAS PLANTAS");
            System.out.println("2. LOGUEARSE");
            System.out.println("3. SALIR DEL PROGRAMA");
            System.out.println("───────────────────────────────");
            try {
                opcion = in.nextInt();
                switch (opcion) {
                case 1:
                    verTodasPlantas();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opción incorrecta");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un número válido.");
                in.nextLine();
                opcion = 0;
            }
        } while (opcion != 3);
    }

    public void login() {
        in.nextLine();  
        System.out.print("Introduce usuario: ");
        String usuario = in.nextLine();
        System.out.print("Introduce contraseña: ");
        String contraseña = in.nextLine();
        try {
            boolean autenticar = servCred.autenticar(usuario, contraseña);  
            if (autenticar) {
                System.out.println("Has iniciado sesión como " + usuario);
                controlador.setUsuarioAutenticado(usuario);  
                if ("admin".equals(usuario) && "admin".equals(contraseña)) {
                    System.out.println("Eres el usuario administrador");
                    fachadaAdmin.menuAdmin(); 
                } else {
                    System.out.println("Eres un usuario del personal del vivero");
                    fachadaPersonal.menuPersonal(); 
                }
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
            }
        } catch (Exception e) {
            System.out.println("No se ha podido iniciar sesión: " + e.getMessage());
        }
    }

    public void verTodasPlantas() {
        ArrayList<Planta> plantas = (ArrayList<Planta>) servPlanta.verTodas(); 
        if (plantas == null || plantas.isEmpty()) {
            System.out.println("Lo siento, no hay plantas para mostrar en la base de datos");
            return;
        }
        System.out.println("Todas las plantas: ");
        System.out.println();
        for (Planta p : plantas) {
            System.out.println(p);
            System.out.println();
        }
    }
}
