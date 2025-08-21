public class usuario {
    String nombre;
    String idUsuario;
    String[] librosPrestados;
    int cantidadPrestados;
    
    public usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.librosPrestados = new String[3]; 
        this.cantidadPrestados = 0;
        
        for (int i = 0; i < 3; i++) {
            librosPrestados[i] = "";
        }
    }
    
    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("ID Usuario: " + idUsuario);
        System.out.println("Libros prestados: " + cantidadPrestados + "/3");
        
        if (cantidadPrestados > 0) {
            System.out.print("Códigos de libros: ");
            for (int i = 0; i < 3; i++) {
                if (!librosPrestados[i].equals("")) {
                    System.out.print(librosPrestados[i] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }
    
    public void agregarPrestamo(libro libro) {
        if (cantidadPrestados < 3) {
            for (int i = 0; i < 3; i++) {
                if (librosPrestados[i].equals("")) {
                    librosPrestados[i] = libro.codigo;
                    cantidadPrestados++;
                    System.out.println("Libro agregado al usuario " + nombre);
                    return;
                }
            }
        } else {
            System.out.println("El usuario ya tiene 3 libros prestados");
        }
    }
    
    public void devolverLibro(libro libro) {
        for (int i = 0; i < 3; i++) {
            if (librosPrestados[i].equals(libro.codigo)) {
                librosPrestados[i] = "";
                cantidadPrestados--;
                System.out.println("Libro removido del usuario " + nombre);
                return;
            }
        }
    }
    
    public boolean tieneLibro(String codigo) {
        for (int i = 0; i < 3; i++) {
            if (librosPrestados[i].equals(codigo)) {
                return true;
            }
        }
        return false;
    }
}