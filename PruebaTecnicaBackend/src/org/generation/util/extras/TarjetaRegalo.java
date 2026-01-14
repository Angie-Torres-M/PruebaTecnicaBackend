package org.generation.util.extras;

import org.generation.util.banco.Imprimible;

public class TarjetaRegalo implements Imprimible {

    private static int consecutivo = 1002; 
    private final int numeroCuenta;
    private double saldo;

    public TarjetaRegalo(double saldoInicial) {
        this.saldo = saldoInicial;
        consecutivo++;
        this.numeroCuenta = consecutivo;
    }

    public TarjetaRegalo(String string, int i) {
		this.numeroCuenta = 1000;
		// TODO Auto-generated constructor stub
	}

	@Override
    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    public double deposito(double cantidad) {
        if (cantidad <= 0) return saldo;
        saldo += cantidad;
        return saldo;
    }

    public double retiro(double cantidad) {
        if (cantidad <= 0) return saldo;
        if (saldo >= cantidad) saldo -= cantidad;
        return saldo;
    }

    @Override
    public String toString() {
        return "Tarjeta Regalo [Saldo=" + saldo + ", Número=" + numeroCuenta + "]";
    }
}
