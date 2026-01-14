package org.generation.util.banco;

import java.util.Date;

// Representa una cuenta de débito
public class CuentaDebito extends Cuenta {

    // Monto mínimo requerido en la cuenta
    private double montoMinimo;

    // Constructor de la cuenta de débito
    public CuentaDebito(double saldo, String numeroCliente, Date fechaApertura, String nombreCliente) {
        super(saldo, numeroCliente, fechaApertura, nombreCliente);
    }

    // Getter del monto mínimo
    public double getMontoMinimo() {
        return montoMinimo;
    }

    // Setter del monto mínimo
    public void setMontoMinimo(double montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    // Retorna el saldo actual
    @Override
    public double getSaldo() {
        return this.saldo;
    }

    // Realiza un retiro si el saldo es suficiente
    @Override
    public double retiro(double cantidad) {
        if (cantidad <= 0) return this.saldo;

        if (this.saldo >= cantidad) {
            this.saldo -= cantidad;
        }
        return this.saldo;
    }

    // Realiza un depósito en la cuenta
    @Override
    public double deposito(double cantidad) {
        if (cantidad <= 0) return this.saldo;

        this.saldo += cantidad;
        return this.saldo;
    }

    // Representación en texto de la cuenta
    @Override
    public String toString() {
        return "Cuenta Débito [" + super.toString() +
                ", Monto mínimo=" + montoMinimo + "]";
    }
}
