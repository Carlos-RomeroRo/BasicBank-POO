package main;
import cuentas.*;
import exceptions.SaldoInsuficienteException;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Crear una Cuenta de Ahorro con un saldo inicial y una tasa de interés
        CuentaAhorro cuentaAhorro = new CuentaAhorro(5000.0, "Juan Perez", "12345", 3.5);

        // Crear una Cuenta Corriente con un saldo inicial y un límite de descubierto
        CuentaCorriente cuentaCorriente = new CuentaCorriente(1000.0, "Ana García", "54321", 2000.0, 5);

        // Mostrar los saldos iniciales
        System.out.println("Saldo inicial Cuenta Ahorro: " + cuentaAhorro.consultarSaldo());
        System.out.println("Saldo inicial Cuenta Corriente: " + cuentaCorriente.consultarSaldo());

        // Realizar depósitos
        cuentaAhorro.depositar(1000.0);
        cuentaCorriente.depositar(500.0);

        // Mostrar los saldos después de los depósitos
        System.out.println("\nSaldo después de depósito Cuenta Ahorro: " + cuentaAhorro.consultarSaldo());
        System.out.println("Saldo después de depósito Cuenta Corriente: " + cuentaCorriente.consultarSaldo());

        // Intentar realizar un retiro en la Cuenta Ahorro con penalización
        try {
            LocalDate fechaRetiro = LocalDate.of(2024, 12, 2);
            cuentaAhorro.retirar(500.0, fechaRetiro); // Realizamos un retiro el 2 de diciembre
            System.out.println("\nSaldo después del retiro en Cuenta Ahorro: " + cuentaAhorro.consultarSaldo());
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error al retirar de Cuenta Ahorro: " + e.getMessage());
        }

        // Intentar realizar un retiro en la Cuenta Corriente
        try {
            cuentaCorriente.retirar(1500.0); // Realizamos un retiro de 1500
            System.out.println("\nSaldo después del retiro en Cuenta Corriente: " + cuentaCorriente.consultarSaldo());
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error al retirar de Cuenta Corriente: " + e.getMessage());
        }

        // Intentar realizar otro retiro en la Cuenta Ahorro para exceder el límite de retiros mensuales
        try {
            LocalDate fechaRetiro2 = LocalDate.of(2024, 12, 3); // Intentamos otro retiro en el mismo mes
            cuentaAhorro.retirar(200.0, fechaRetiro2); // Realizamos otro retiro el 3 de diciembre
            System.out.println("\nSaldo después del segundo retiro en Cuenta Ahorro: " + cuentaAhorro.consultarSaldo());
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error al retirar de Cuenta Ahorro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage()); // Excepción para la fecha incorrecta
        }
    }
}
