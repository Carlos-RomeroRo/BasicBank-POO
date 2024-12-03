package cuentas;

import exceptions.SaldoInsuficienteException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CuentaAhorro extends CuentaBancaria {
    private double tasaInteres;
    private List<LocalDate> fechaRetiros;
    public CuentaAhorro(double saldo, String titular, String numeroCuenta, double tasaInteres){
        super(saldo, titular, numeroCuenta);
        this.tasaInteres = tasaInteres;
        this.fechaRetiros = new ArrayList<>();
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public void retirar(double valor, LocalDate fecha) throws SaldoInsuficienteException {
        validarFecha(fecha);
        int retirosEsteMes = (int) fechaRetiros.stream().
                filter(f -> f.getMonth() == fecha.getMonth() &&
                        f.getYear() == fecha.getYear()).count(); /* cuenta con programación funcional el número de
                                                                    retiros hechos el mismo mes, en caso de ser así existirá una penalización */
        if(retirosEsteMes >= 1) {
            double penalizacion = 5;
            System.out.println("Se aplicará una penalización de: "
                    +penalizacion+
                    " por exceder el limites de retiros mensuales.");
            valor += penalizacion;
        }
        if (getSaldo() < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente para cubrir el monto del retiro y la penalización.");
        }
        super.retirar(valor);
        fechaRetiros.add(fecha);
    }

    private void validarFecha (LocalDate fecha){
        if(fecha.isAfter(LocalDate.now())){
            throw  new IllegalArgumentException("La fecha del retiro no puede ser futura");
        }
    }
}
