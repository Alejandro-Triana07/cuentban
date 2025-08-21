import java.util.Scanner;

public class admin {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        biblioteca biblioteca = new biblioteca();
        
        System.out.println("=== SISTEMA DE GESTIÓN DE BIBLIOTECA ===");
        System.out.println("Libros disponibles:");
        System.out.println("01 - Cien años de soledad");
        System.out.println("02 - Don Quijote");
        System.out.println("03 - El Principito");
        System.out.println("04 - 1984");
        System.out.println("05 - Crónica de una muerte anunciada");
        System.out.println("");
        System.out.println("Usuarios disponibles:");
        System.out.println("1 - Diego Triana");
        System.out.println("2 - Daniel Galvis");
        System.out.println("3 - Deixy Garcia");
        
        int opcion = 0;
        while (opcion != 7) {
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Ver libros disponibles");
            System.out.println("2. Ver usuarios");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Ver préstamos activos");
            System.out.println("6. Ver todos los libros");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            
            opcion = entrada.nextInt();
            entrada.nextLine();
            
            if (opcion == 1) {
                biblioteca.mostrarLibrosDisponibles();
                
            } else if (opcion == 2) {
                biblioteca.mostrarUsuarios();
                
            } else if (opcion == 3) {
                System.out.print("Código del libro a prestar (01, 02, 03, 04, 05): ");
                String codigoLibro = entrada.nextLine();
                System.out.print("ID del usuario (1, 2, 3): ");
                String idUsuario = entrada.nextLine();
                biblioteca.prestarLibro(codigoLibro, idUsuario);
                
            } else if (opcion == 4) {
                System.out.print("Código del libro a devolver: ");
                String codigoLibro = entrada.nextLine();
                System.out.print("ID del usuario: ");
                String idUsuario = entrada.nextLine();
                biblioteca.devolverLibro(codigoLibro, idUsuario);
                
            } else if (opcion == 5) {
                biblioteca.mostrarHistorialPrestamos();
                
            } else if (opcion == 6) {
                biblioteca.mostrarTodosLosLibros();
                
            } else if (opcion == 7) {
                System.out.println("¡Gracias por usar el sistema de biblioteca!");
                
            } else {
                System.out.println("Opción no válida");
            }
        }
        
        entrada.close();
    }
}
