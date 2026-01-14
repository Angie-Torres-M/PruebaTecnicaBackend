package org.generation.util.banco;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public abstract class Cuenta implements Imprimible {
    // Los números de cuenta del banco iniciarán después de 1000
    private static int consecutivo = 1000;

    protected double saldo;
    private int numeroCuenta;
    private String numeroCliente;
    private Date fechaApertura;
    private String nombreCliente;

    /**
     * @param saldo         double - Saldo inicial de la cuenta
     * @param numeroCliente String - Número del cliente, si se establece en "0" se genera uno aleatorio
     * @param fechaApertura Date   - Fecha de apertura de la cuenta
     * @param nombreCliente String - Nombre del cliente
     */
    public Cuenta(double saldo, String numeroCliente, Date fechaApertura, String nombreCliente) {
        this.saldo = saldo;

        Cuenta.consecutivo++;
        this.numeroCuenta = Cuenta.consecutivo;

        this.numeroCliente = numeroCliente;
        if (numeroCliente != null && numeroCliente.equals("0")) { // Se genera un número de cliente aleatorio
            this.numeroCliente = Integer.toString(Math.abs(new Random().nextInt()));
        }

        this.fechaApertura = fechaApertura;
        this.setNombreCliente(nombreCliente);
    }

    public Cuenta(double saldo) {
        this.saldo = saldo;
    }

    // Los métodos abstract deben ser implementados en la clase que hereda
    public abstract double retiro(double cantidad);

    public abstract double deposito(double cantidad);

    @Override
    public abstract double getSaldo();

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public static int getConsecutivo() {
        return consecutivo;
    }

    @Override
    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public String getFechaApertura() {
        if (fechaApertura == null) return "N/A";
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        return format.format(fechaApertura);
    }

    protected String getNombreCliente() {
        return nombreCliente;
    }

    protected void setNombreCliente(String nombreCliente) {
        this.nombreCliente = (nombreCliente == null) ? "" : nombreCliente.toUpperCase();
    }

    @Override
    public String toString() {
        return "Saldo=" + saldo + ", Número de Cuenta=" + getNumeroCuenta()
                + ", Número del Cliente=" + getNumeroCliente()
                + ", Fecha de Apertura=" + getFechaApertura()
                + ", Nombre del Cliente=" + getNombreCliente();
    }
}
