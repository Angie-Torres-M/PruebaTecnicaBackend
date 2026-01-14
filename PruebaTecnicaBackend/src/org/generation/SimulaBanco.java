package org.generation;

import java.util.Calendar;
import java.util.HashMap;

import org.generation.util.banco.Cuenta;
import org.generation.util.banco.CuentaCredito;
import org.generation.util.banco.CuentaDebito;
import org.generation.util.banco.ReciboSaldo;

public class SimulaBanco {
    public static void main(String[] args) {

        Calendar apertura = Calendar.getInstance();
        apertura.set(2025, Calendar.MAY, 1);

        Calendar corte = Calendar.getInstance();
        corte.set(2025, Calendar.MAY, 31);

        HashMap<Integer, Cuenta> cuentas = new HashMap<>();

        cuentas.put(1001, new CuentaDebito(2000, "D8001", apertura.getTime(), "Juan Pérez"));
        cuentas.put(1002, new CuentaCredito(0, "C7001", apertura.getTime(), "María Díaz",
                corte.getTime(), corte.getTime(), 0.35f, 3000));

        System.out.println(cuentas.get(1001));
        System.out.println(cuentas.get(1002));

        ReciboSaldo.Imprimir(cuentas.get(1001));
        ReciboSaldo.Imprimir(cuentas.get(1002));
        
     // 4 operaciones (2 depósitos + 2 retiros) antes de imprimir saldos
        cuentas.get(1001).deposito(500);     // Débito: +500
        cuentas.get(1001).retiro(200);       // Débito: -200

        cuentas.get(1002).retiro(1000);      // Crédito: retiro (aumenta deuda + comisión 5%)
        cuentas.get(1002).deposito(300);     // Crédito: pago (reduce deuda)

    }
}
