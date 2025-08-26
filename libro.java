public class libro {
    String titulo;
    String autor;
    String codigo;
    boolean disponible;
    
    public libro(String titulo, String autor, String codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.disponible = true;
    }
    
    public void mostrarDatos() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Código: " + codigo);
        System.out.println("Disponible: " + (disponible ? "Sí" : "No"));
    }
    
    public void marcarPrestado() {
        disponible = false;
        System.out.println("El libro '" + titulo + "' ha sido marcado como prestado");
    }
    
    public void marcarDisponible() {
        disponible = true;
        System.out.println("El libro '" + titulo + "' ha sido marcado como disponible");
    }
}
