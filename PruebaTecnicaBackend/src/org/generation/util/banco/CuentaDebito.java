package org.generation.util.banco;

import java.util.Date;

public class CuentaDebito extends Cuenta {

    private double montoMinimo;

    public CuentaDebito(double saldo, String numeroCliente, Date fechaApertura, String nombreCliente) {
        super(saldo, numeroCliente, fechaApertura, nombreCliente);
    }

    public double getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(double montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    @Override
    public double getSaldo() {
        return this.saldo;
    }

    @Override
    public double retiro(double cantidad) {
        if (cantidad <= 0) return this.saldo;

        if (this.saldo >= cantidad) {
            this.saldo -= cantidad;
        }
        return this.saldo;
    }

    @Override
    public double deposito(double cantidad) {
        if (cantidad <= 0) return this.saldo;

        this.saldo += cantidad;
        return this.saldo;
    }

    @Override
    public String toString() {
        return "Cuenta Débito [" + super.toString() +
                ", Monto mínimo=" + montoMinimo + "]";
    }
}
