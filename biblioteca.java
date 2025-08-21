import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class biblioteca {
    libro[] libros;
    usuario[] usuarios;
    String[] prestamosLibro;
    String[] prestamosUsuario;
    String[] fechasInicio;
    String[] fechasLimite;
    int cantidadPrestamos;
    
    public biblioteca() {
        libros = new libro[10];
        usuarios = new usuario[5];
        prestamosLibro = new String[10]; 
        prestamosUsuario = new String[10];
        fechasInicio = new String[10];
        fechasLimite = new String[10];
        cantidadPrestamos = 0;

        libros[1] = new libro("Don Quijote", "Miguel de Cervantes", "02");
        libros[0] = new libro("Cien años de soledad", "Gabriel García Márquez", "01");
        libros[2] = new libro("El Principito", "Antoine de Saint-Exupéry", "03");
        libros[3] = new libro("1984", "George Orwell", "04");
        libros[4] = new libro("Crónica de una muerte anunciada", "Gabriel García Márquez", "05");
        
        for (int i = 5; i < 10; i++) {
            libros[i] = null;
        }
        
        usuarios[0] = new usuario("Diego Triana", "1");
        usuarios[1] = new usuario("Daniel Galvis", "2");
        usuarios[2] = new usuario("Deixy Garcia", "3");
        
        for (int i = 3; i < 5; i++) {
            usuarios[i] = null;
        }

        for (int i = 0; i < 10; i++) {
            prestamosLibro[i] = "";
            prestamosUsuario[i] = "";
            fechasInicio[i] = "";
            fechasLimite[i] = "";
        }
    }
    
    public void registrarLibro() {
        for (int i = 0; i < 10; i++) {
            if (libros[i] == null) {
                System.out.println("Espacio disponible encontrado en posición " + (i + 1));
                System.out.println("Función de registro manual no implementada");
                System.out.println("Se puede agregar libro en esta posición");
                return;
            }
        }
        System.out.println("No hay espacio para más libros");
    }
    
    public void registrarUsuario() {
        for (int i = 0; i < 5; i++) {
            if (usuarios[i] == null) {
                System.out.println("Espacio disponible encontrado en posición " + (i + 1));
                System.out.println("Función de registro manual no implementada");
                System.out.println("Se puede agregar usuario en esta posición");
                return;
            }
        }
        System.out.println("No hay espacio para más usuarios");
    }
    
    public void prestarLibro(String codigoLibro, String idUsuario) {
        libro libro = buscarlibro(codigoLibro);
        usuario usuario = buscarusuario(idUsuario);
        
        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }
        
        if (usuario == null) {
            System.out.println("Usuario no encontrado");
            return;
        }
        
        if (!libro.disponible) {
            System.out.println("El libro no está disponible");
            return;
        }
        
        if (usuario.cantidadPrestados >= 3) {
            System.out.println("El usuario ya tiene 3 libros prestados");
            return;
        }
        
        if (cantidadPrestamos >= 10) {
            System.out.println("No hay espacio para más préstamos");
            return;
        }

        prestamosLibro[cantidadPrestamos] = codigoLibro;
        prestamosUsuario[cantidadPrestamos] = idUsuario;
        fechasInicio[cantidadPrestamos] = LocalDate.now().toString();
        fechasLimite[cantidadPrestamos] = LocalDate.now().plusDays(14).toString();
        cantidadPrestamos++;
        
        libro.marcarPrestado();
        usuario.agregarPrestamo(libro);
        
        System.out.println("Préstamo realizado exitosamente");
        System.out.println("Fecha límite de devolución: " + LocalDate.now().plusDays(14));
    }
    
    public void devolverLibro(String codigoLibro, String idUsuario) {
        libro libro = buscarlibro(codigoLibro);
        usuario usuario = buscarusuario(idUsuario);
        
        if (libro == null || usuario == null) {
            System.out.println("Libro o usuario no encontrado");
            return;
        }
        
        if (!usuario.tieneLibro(codigoLibro)) {
            System.out.println("El usuario no tiene este libro prestado");
            return;
        }

        for (int i = 0; i < cantidadPrestamos; i++) {
            if (prestamosLibro[i].equals(codigoLibro) && prestamosUsuario[i].equals(idUsuario)) {
                LocalDate fechaActual = LocalDate.now();
                LocalDate limite = LocalDate.parse(fechasLimite[i]);
                long diasRetraso = ChronoUnit.DAYS.between(limite, fechaActual);
                
                if (diasRetraso > 0) {
                    double multa = diasRetraso * 500;
                    System.out.println("¡Libro devuelto con retraso!");
                    System.out.println("Días de retraso: " + diasRetraso);
                    System.out.println("Multa a pagar: $" + multa);
                } else {
                    System.out.println("Libro devuelto a tiempo");
                }

                for (int j = i; j < cantidadPrestamos - 1; j++) {
                    prestamosLibro[j] = prestamosLibro[j + 1];
                    prestamosUsuario[j] = prestamosUsuario[j + 1];
                    fechasInicio[j] = fechasInicio[j + 1];
                    fechasLimite[j] = fechasLimite[j + 1];
                }
                cantidadPrestamos--;
                prestamosLibro[cantidadPrestamos] = "";
                prestamosUsuario[cantidadPrestamos] = "";
                fechasInicio[cantidadPrestamos] = "";
                fechasLimite[cantidadPrestamos] = "";
                break;
            }
        }
        
        libro.marcarDisponible();
        usuario.devolverLibro(libro);
        System.out.println("Devolución completada exitosamente");
    }
    
    public void mostrarLibrosDisponibles() {
        System.out.println("\n=== LIBROS DISPONIBLES ===");
        boolean hayDisponibles = false;
        
        for (int i = 0; i < 10; i++) {
            if (libros[i] != null && libros[i].disponible) {
                libros[i].mostrarDatos();
                hayDisponibles = true;
            }
        }
        
        if (!hayDisponibles) {
            System.out.println("No hay libros disponibles");
        }
    }
    
    public void mostrarUsuarios() {
        System.out.println("\n=== USUARIOS REGISTRADOS ===");
        for (int i = 0; i < 5; i++) {
            if (usuarios[i] != null) {
                usuarios[i].mostrarDatos();
            }
        }
    }
    
    public void mostrarHistorialPrestamos() {
        System.out.println("\n=== PRÉSTAMOS ACTIVOS ===");
        
        if (cantidadPrestamos == 0) {
            System.out.println("No hay préstamos activos");
            return;
        }
        
        for (int i = 0; i < cantidadPrestamos; i++) {
            libro libro = buscarlibro(prestamosLibro[i]);
            usuario usuario = buscarusuario(prestamosUsuario[i]);
            
            if (libro != null && usuario != null) {
                System.out.println("Libro: " + libro.titulo);
                System.out.println("Usuario: " + usuario.nombre);
                System.out.println("Fecha de préstamo: " + fechasInicio[i]);
                System.out.println("Fecha límite: " + fechasLimite[i]);
                System.out.println("------------------------");
            }
        }
    }
    
    public void mostrarTodosLosLibros() {
        System.out.println("\n=== TODOS LOS LIBROS ===");
        for (int i = 0; i < 10; i++) {
            if (libros[i] != null) {
                libros[i].mostrarDatos();
            }
        }
    }
    
    private libro buscarlibro(String codigo) {
        for (int i = 0; i < 10; i++) {
            if (libros[i] != null && libros[i].codigo.equals(codigo)) {
                return libros[i];
            }
        }
        return null;
    }
    
    private usuario buscarusuario(String id) {
        for (int i = 0; i < 5; i++) {
            if (usuarios[i] != null && usuarios[i].idUsuario.equals(id)) {
                return usuarios[i];
            }
        }
        return null;
    }
}
