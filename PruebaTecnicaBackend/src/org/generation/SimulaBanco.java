package org.generation;

import java.util.Calendar;
import java.util.Scanner;

import org.generation.util.banco.Banco;
import org.generation.util.banco.Cuenta;
import org.generation.util.banco.CuentaCredito;
import org.generation.util.banco.CuentaDebito;
import org.generation.util.banco.ReciboSaldo;
import org.generation.util.extras.TarjetaRegalo;

public class SimulaBanco {

    private static void menuConsola(Banco banco, TarjetaRegalo regalo) {
        Scanner sc = new Scanner(System.in);
        int op = -1;

        while (op != 0) {
            System.out.println("\n===== MENÚ BANCO =====");
            System.out.println("1) Ver reporte del banco");
            System.out.println("2) Listar cuentas");
            System.out.println("3) Imprimir saldo de una cuenta (por número)");
            System.out.println("4) Depósito a una cuenta");
            System.out.println("5) Retiro de una cuenta");
            System.out.println("6) Imprimir tarjeta regalo");
            System.out.println("0) Salir");
            System.out.print("Elige una opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Opción inválida. Escribe un número.");
                sc.nextLine();
                continue;
            }
            op = sc.nextInt();
            sc.nextLine(); // limpia Enter

            switch (op) {
                case 1 -> System.out.println(banco.generarReporte());

                case 2 -> {
                    System.out.println("\n--- Cuentas registradas ---");
                    for (Cuenta c : banco.listarCuentas()) {
                        System.out.println(c);
                    }
                }

                case 3 -> {
                    int num = pedirEntero(sc, "Número de cuenta: ");
                    Cuenta c = banco.obtenerCuenta(num);
                    if (c == null) System.out.println("No existe esa cuenta.");
                    else ReciboSaldo.Imprimir(c);
                }

                case 4 -> {
                    int num = pedirEntero(sc, "Número de cuenta: ");
                    double monto = pedirDouble(sc, "Monto a depositar: ");
                    Cuenta c = banco.obtenerCuenta(num);

                    if (c == null) {
                        System.out.println("No existe esa cuenta.");
                    } else {
                        c.deposito(monto);
                        System.out.println("Depósito realizado.");
                        ReciboSaldo.Imprimir(c);
                    }
                }

                case 5 -> {
                    int num = pedirEntero(sc, "Número de cuenta: ");
                    double monto = pedirDouble(sc, "Monto a retirar: ");
                    Cuenta c = banco.obtenerCuenta(num);

                    if (c == null) {
                        System.out.println("No existe esa cuenta.");
                    } else {
                        c.retiro(monto);
                        System.out.println("Operación realizada.");
                        ReciboSaldo.Imprimir(c);
                    }
                }

                case 6 -> {
                    System.out.println("\n--- Tarjeta Regalo ---");
                    ReciboSaldo.Imprimir(regalo);
                }

                case 0 -> System.out.println("Saliendo...");

                default -> System.out.println("Opción inválida.");
            }
        }

    }

    private static int pedirEntero(Scanner sc, String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            System.out.println("Escribe un número entero válido.");
            sc.nextLine();
            System.out.print(msg);
        }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }

    private static double pedirDouble(Scanner sc, String msg) {
        System.out.print(msg);
        while (!sc.hasNextDouble()) {
            System.out.println("Escribe un número válido (ej. 150.50).");
            sc.nextLine();
            System.out.print(msg);
        }
        double val = sc.nextDouble();
        sc.nextLine();
        return val;
    }

    public static void main(String[] args) {

        Calendar apertura = Calendar.getInstance();
        apertura.set(2025, Calendar.MAY, 1);

        Calendar corte = Calendar.getInstance();
        corte.set(2025, Calendar.MAY, 31);

        // Crear banco
        Banco banco = new Banco("Generation Bank");

        // Crear cuentas
        CuentaDebito debito = new CuentaDebito(2000, "D8001", apertura.getTime(), "Juan Pérez");
        CuentaCredito credito = new CuentaCredito(0, "C7001", apertura.getTime(), "María Díaz",
                corte.getTime(), corte.getTime(), 0.35f, 3000);

        // Crear tarjeta regalo (Parte 2)
        TarjetaRegalo regalo = new TarjetaRegalo("Regalo Angie", 250);
        regalo.deposito(50);
        regalo.retiro(30);

        // Agregar al banco (Parte 3)
        banco.agregarCuenta(debito);
        banco.agregarCuenta(credito);

        // 4 operaciones (Parte 1)
        debito.deposito(500);
        debito.retiro(200);

        credito.retiro(1000);
        credito.deposito(300);

        // Imprimir saldos
        ReciboSaldo.Imprimir(debito);
        ReciboSaldo.Imprimir(credito);
        ReciboSaldo.Imprimir(regalo);

        // Reporte
        System.out.println(banco.generarReporte());

        // Menú interactivo (EXTRA)
        menuConsola(banco, regalo);
    }
}
