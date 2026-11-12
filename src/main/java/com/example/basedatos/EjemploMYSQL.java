package com.example.basedatos;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Scanner;

public class EjemploMYSQL {
    public static void main(String[] args) {
        try {
            LinkedList<Connection> conexiones = new LinkedList<>();

            System.out.println("=== Probando el modelo Singleton con DatosMYSQL ===");
            System.out.println("Se solicitarán 10 conexiones en un bucle for...\n");

            for (int i = 0; i < 10; i++) {
                // Obtenemos la conexión a través de la instancia Singleton
                Connection con = DatosMYSQL.getInstance().getConexion();
                conexiones.add(con);
                System.out.println("Iteración " + (i + 1) + " -> Referencia de conexión: " + con);
            }

            System.out.println("\n=== Resultados ===");
            System.out.println("Total de elementos agregados a la lista: " + conexiones.size());

            if (!conexiones.isEmpty() && conexiones.getFirst() != null) {
                boolean todasIguales = conexiones.stream().allMatch(c -> c == conexiones.getFirst());
                System.out.println("¿Todas las referencias son exactamente la misma instancia?: " + todasIguales);
            } else {
                System.out.println("Nota: La conexión no pudo establecerse. Asegúrate de tener el servidor MySQL (XAMPP) iniciado y la base de datos 'informacion_db' creada.");
            }

            System.out.println("\n=== Verificación en la Web (phpMyAdmin) ===");
            System.out.println("Abre en tu navegador la interfaz web de phpMyAdmin (ej. http://localhost/phpmyadmin),");
            System.out.println("dirígete a 'Estado del servidor' (Server Status) -> 'Procesos' (Processes).");
            System.out.println("Podrás comprobar que únicamente existe 1 proceso/conexión activa generada por esta aplicación.");
            System.out.println("\nEl programa permanecerá a la espera para que puedas revisarlo en la web.");
            System.out.println("Presiona ENTER en esta consola cuando termines para cerrar la conexión y salir...");

            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

            // Cerrar la conexión si está abierta
            if (DatosMYSQL.getInstance().getConexion() != null && !DatosMYSQL.getInstance().getConexion().isClosed()) {
                DatosMYSQL.getInstance().getConexion().close();
                System.out.println("Conexión cerrada exitosamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }
}
