package org.generation.util.banco;

import java.util.Date;

public class CuentaCredito extends Cuenta {

    private double limite;
    private float tasa;
    private Date fechaCorte;
    private Date fechaLimitePago;

    public CuentaCredito(double saldo, String numeroCliente, Date fechaApertura, String nombreCliente,
                         Date fechaCorte, Date fechaLimitePago, float tasa, double limite) {
        super(saldo, numeroCliente, fechaApertura, nombreCliente);
        this.fechaCorte = fechaCorte;
        this.setFechaLimitePago(fechaLimitePago);
        this.tasa = tasa;
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public float getTasa() {
        return tasa;
    }

    public void setTasa(float tasa) {
        this.tasa = tasa;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    @Override
    public double getSaldo() {
        // cobra comisión de $3 por cada consulta
        this.saldo -= 3;
        return this.saldo;
    }

    @Override
    public double retiro(double cantidad) {
        if (cantidad <= 0) return this.saldo;

        // En crédito, saldo representa deuda; disponible = limite - deuda
        double disponible = this.limite - this.saldo;

        if (disponible < cantidad) {
            // no se puede: comisión $5
            this.saldo += 5;
            return this.saldo;
        }

        // sí se puede: aumenta deuda + comisión 5% del retiro
        double comision = cantidad * 0.05;
        this.saldo += (cantidad + comision);
        return this.saldo;
    }

    @Override
    public double deposito(double cantidad) {
        if (cantidad <= 0) return this.saldo;

        // pagar deuda (reduce saldo/deuda)
        this.saldo -= cantidad;
        return this.saldo;
    }

    @Override
    public String toString() {
        return "Cuenta Crédito [" + super.toString() +
                " Tasa=" + getTasa() + "%, Límite=" + getLimite() + "]";
    }

	public Date getFechaLimitePago() {
		return fechaLimitePago;
	}

	public void setFechaLimitePago(Date fechaLimitePago) {
		this.fechaLimitePago = fechaLimitePago;
	}
}
