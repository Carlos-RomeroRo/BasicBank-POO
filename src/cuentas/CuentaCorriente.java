package cuentas;

import exceptions.SaldoInsuficienteException;

public class CuentaCorriente extends CuentaBancaria{
    private double limiteCredito;
    private double comision;
    public CuentaCorriente(double saldo, String titular, String numeroCuenta, double limiteCredito, double comision) {
        super(saldo, titular, numeroCuenta);
        if (limiteCredito < 0 || comision < 0){
            throw  new IllegalArgumentException("Ni el limite de crédito ni la comsión deben ser negativas.");
        }
        this.limiteCredito = limiteCredito;
        this.comision = comision;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    protected void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public void  retirar(double valor) throws SaldoInsuficienteException{
        validarRetiro(valor);
        if(valor>getSaldo() && valor<(getSaldo()+limiteCredito)){ // el usuario usará el crédito al cual se le descontará una comisión
            double restante = valor-getSaldo();
            setSaldo(0); // se descontó todo lo que había en el saldo
            setLimiteCredito(limiteCredito-(restante+comision));
        } else if (valor>(getSaldo()+limiteCredito)) {
            throw new SaldoInsuficienteException("Fondos insuficientes para completar la operación.");
        }else{ // llamar el método de la clase padre para retiros normales
            super.retirar(valor);
        }
    }

    private void validarRetiro(double valor){
        if (valor<=0){
            throw new IllegalArgumentException("El valor de la " +
                    "comisión debe de ser positiva.");
        }
    }
}
