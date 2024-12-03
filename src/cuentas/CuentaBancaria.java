package cuentas;

import exceptions.SaldoInsuficienteException;

public class CuentaBancaria {
        private double saldo;
        private String titular;
        private String numeroCuenta;

    public CuentaBancaria(double saldo, String titular, String numeroCuenta) {
        this.saldo = saldo;
        this.titular = titular;
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    // métodos publicos

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void retirar(double valor) throws SaldoInsuficienteException {
        if(valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente. No se puede" +
                    " retirar más de lo disponible.");
        }
        saldo -= valor;
    }

    public double consultarSaldo() {
        return saldo;
    }

}
