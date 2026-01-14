package org.generation.util.banco;

import java.util.HashMap;

public class Banco {

    private String nombre;
    private HashMap<Integer, Cuenta> cuentas;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    // 1) Agregar cuenta
    public boolean agregarCuenta(Cuenta cuenta) {
        if (cuenta == null) return false;

        int num = cuenta.getNumeroCuenta();
        if (cuentas.containsKey(num)) return false; // evita duplicados

        cuentas.put(num, cuenta);
        return true;
    }

    // 2) Acceder / buscar cuenta por número
    public Cuenta obtenerCuenta(int numeroCuenta) {
        return cuentas.get(numeroCuenta);
    }
  // Para listar 
    public java.util.Collection<Cuenta> listarCuentas() {
        return cuentas.values();
    }
    
    // 3) Modificar (ejemplo útil): actualizar nombre del cliente
        public boolean actualizarNombreCliente(int numeroCuenta, String nuevoNombre) {
        Cuenta c = cuentas.get(numeroCuenta);
        if (c == null) return false;

        c.setNombreCliente(nuevoNombre);
        return true;
    }

    // 4) Cancelar cuenta
    public boolean cancelarCuenta(int numeroCuenta) {
        return cuentas.remove(numeroCuenta) != null;
    }

    // Reporte solicitado:
    // - totalDebito: cuánto dinero real hay en débito
    // - deudaCredito: cuánto asciende la deuda total en crédito
    public String generarReporte() {
        double totalDebito = 0;
        double deudaCredito = 0;

        for (Cuenta c : cuentas.values()) {
            if (c instanceof CuentaDebito) {
                // En débito, saldo = dinero disponible
                totalDebito += c.getSaldo();
            } else if (c instanceof CuentaCredito) {
                // En crédito, saldo = deuda 
              deudaCredito += c.saldo;
            }
        }

        return "===== REPORTE BANCO: " + nombre + " =====\n"
             + "Total en cuentas débito: $" + totalDebito + "\n"
             + "Deuda total en cuentas crédito: $" + deudaCredito + "\n"
             + "Cuentas registradas: " + cuentas.size() + "\n"
             + "====================================";
    }
}
