public class Personaje {
    String nombre;
    String raza;
    Integer ki;
    Integer vida;
    Integer edad;

    public Personaje(String nombre, String raza, Integer ki, Integer vida, Integer edad) {
        this.nombre = nombre;
        this.raza = raza;
        this.ki = ki;
        this.vida = vida;
        this.edad = edad;
    }

    public void mostrarInfo() {
        System.out.println("El nombre es: " + nombre);
        System.out.println("La raza es: " + raza);
        System.out.println("El ki es: " + ki);
        System.out.println("La vida es: " + vida);
        System.out.println("La edad es: " + edad);
    }

    public void ataque(Personaje objetivo) {
        objetivo.vida -= ki;
        System.out.println(nombre + " atacó a " + objetivo.nombre + " y le quitó " + ki + " de vida, " + objetivo.nombre + " quedó con " + objetivo.vida + " de vida");
    }

    public void semilladeermitaño(Personaje objetivo) {
        objetivo.vida += ki;

        if (objetivo.vida > 100) {
            objetivo.vida = 100;
        }

        vida -= ki;

        System.out.println(nombre + " le dio vida a " + objetivo.nombre + " y le sumó " + ki + " de vida, " + objetivo.nombre + " quedó con " + objetivo.vida + " de vida y " + nombre + " quedó con " + vida + " de vida");
    }

    public static void main(String[] args) {
        Personaje goku = new Personaje("Gokú", "Sayayin", 80, 100, 38);
        Personaje vegetta = new Personaje("Vegetta", "Sayayin", 80, 100, 40);
        Personaje krillin = new Personaje("Krillin", "Sayayin", 50, 100, 30);

        goku.ataque(vegetta);
        System.out.println();
        vegetta.mostrarInfo();
        System.out.println();
        krillin.semilladeermitaño(vegetta);
        System.out.println();
        vegetta.mostrarInfo();
        System.out.println();
        krillin.mostrarInfo();
    }
}
