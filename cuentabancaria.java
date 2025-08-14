import java.util.Scanner;

public class cuentabancaria {
    String titular;
    String numeroCuenta;
    double saldo;
    
    public cuentabancaria(String titular, String numeroCuenta, double saldo) {
        this.titular = titular;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public void mostrarDatos() {
        System.out.println("Titular: " + titular);
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Saldo: $" + saldo);
        System.out.println("------------------------");
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo = saldo + cantidad;
            System.out.println("Depósito exitoso. Nuevo saldo: $" + saldo);
        } else {
            System.out.println("La cantidad debe ser mayor a cero");
        }
    }

    public void retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo = saldo - cantidad;
            System.out.println("Retiro exitoso. Nuevo saldo: $" + saldo);
        } else if (cantidad > saldo) {
            System.out.println("No tienes suficiente dinero. Saldo actual: $" + saldo);
        } else {
            System.out.println("La cantidad debe ser mayor a cero");
        }
    }

    public void transferir(cuentabancaria otraCuenta, double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            this.saldo = this.saldo - cantidad;
            otraCuenta.saldo = otraCuenta.saldo + cantidad;
            System.out.println("Transferencia exitosa de $" + cantidad);
            System.out.println("De: " + this.titular + " a " + otraCuenta.titular);
            System.out.println("Tu nuevo saldo: $" + this.saldo);
        } else if (cantidad > saldo) {
            System.out.println("No tienes suficiente dinero para transferir");
        } else {
            System.out.println("La cantidad debe ser mayor a cero");
        }
    }
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        cuentabancaria cuenta1 = new cuentabancaria("Juan Perez", "123456", 1500.0);
        cuentabancaria cuenta2 = new cuentabancaria("Maria Lopez", "789012", 2000.0);
        cuentabancaria cuenta3 = new cuentabancaria("Carlos Ruiz", "345678", 800.0);
        
        System.out.println("=== Sistema Bancario ===");
        System.out.println("Cuentas disponibles:");
        System.out.println("1. " + cuenta1.titular);
        System.out.println("2. " + cuenta2.titular);
        System.out.println("3. " + cuenta3.titular);
        
        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Ver datos de cuenta");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Transferir dinero");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            
            opcion = entrada.nextInt();
            
            if (opcion == 1) {
                System.out.print("¿De qué cuenta quieres ver los datos? (1, 2 o 3): ");
                int numCuenta = entrada.nextInt();
                if (numCuenta == 1) {
                    cuenta1.mostrarDatos();
                } else if (numCuenta == 2) {
                    cuenta2.mostrarDatos();
                } else if (numCuenta == 3) {
                    cuenta3.mostrarDatos();
                }
            }
            
            else if (opcion == 2) {
                System.out.print("¿A qué cuenta quieres depositar? (1, 2 o 3): ");
                int numCuenta = entrada.nextInt();
                System.out.print("¿Cuánto quieres depositar?: $");
                double cantidad = entrada.nextDouble();
                
                if (numCuenta == 1) {
                    cuenta1.depositar(cantidad);
                } else if (numCuenta == 2) {
                    cuenta2.depositar(cantidad);
                } else if (numCuenta == 3) {
                    cuenta3.depositar(cantidad);
                }
            }
            
            else if (opcion == 3) {
                System.out.print("¿De qué cuenta quieres retirar? (1, 2 o 3): ");
                int numCuenta = entrada.nextInt();
                System.out.print("¿Cuánto quieres retirar?: $");
                double cantidad = entrada.nextDouble();
                
                if (numCuenta == 1) {
                    cuenta1.retirar(cantidad);
                } else if (numCuenta == 2) {
                    cuenta2.retirar(cantidad);
                } else if (numCuenta == 3) {
                    cuenta3.retirar(cantidad);
                }
            }
            
            else if (opcion == 4) {
                System.out.print("¿De qué cuenta quieres transferir? (1, 2 o 3): ");
                int cuentaOrigen = entrada.nextInt();
                System.out.print("¿A qué cuenta quieres transferir? (1, 2 o 3): ");
                int cuentaDestino = entrada.nextInt();
                System.out.print("¿Cuánto quieres transferir?: $");
                double cantidad = entrada.nextDouble();
                
                if (cuentaOrigen == cuentaDestino) {
                    System.out.println("No puedes transferir a la misma cuenta");
                } else if (cuentaOrigen == 1 && cuentaDestino == 2) {
                    cuenta1.transferir(cuenta2, cantidad);
                } else if (cuentaOrigen == 1 && cuentaDestino == 3) {
                    cuenta1.transferir(cuenta3, cantidad);
                } else if (cuentaOrigen == 2 && cuentaDestino == 1) {
                    cuenta2.transferir(cuenta1, cantidad);
                } else if (cuentaOrigen == 2 && cuentaDestino == 3) {
                    cuenta2.transferir(cuenta3, cantidad);
                } else if (cuentaOrigen == 3 && cuentaDestino == 1) {
                    cuenta3.transferir(cuenta1, cantidad);
                } else if (cuentaOrigen == 3 && cuentaDestino == 2) {
                    cuenta3.transferir(cuenta2, cantidad);
                }
            }
            
            else if (opcion == 5) {
                System.out.println("¡Gracias por usar el sistema bancario!");
            }
            
            else {
                System.out.println("Opción no válida");
            }
        }
        
        entrada.close();
    }
}