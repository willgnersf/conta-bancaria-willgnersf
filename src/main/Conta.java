package main;

public class Conta {
    private int numero;
    private double saldo;
    private double limite;
    private double[] extrato;
    private int operacoesRealizadas;

    public Conta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
        limite = 100;
        extrato = new double[100];
        operacoesRealizadas = 0;
    }


    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo+limite;
    }

    public double getLimite() {
        return limite;
    }

    public boolean sacar(double valor) {
        if(valor > 0 && (saldo+limite)-valor >= 0 && operacoesRealizadas <= 10){
            saldo -= valor;
            if(saldo < 0){
                limite += saldo;
                saldo = 0;
            }
            extrato[operacoesRealizadas] -= valor;
            operacoesRealizadas++;
            return true;
        }
        return false;
    }


    public boolean depositar(double valor) {
        if(valor > 0 && operacoesRealizadas <= 10){
            if(saldo == 0 && limite < 100) {
                limite += valor;
                if (limite > 100) {
                    saldo += (limite - 100);
                    limite = 100;
                }
            } else
                saldo += valor;
            extrato[operacoesRealizadas] += valor;
            operacoesRealizadas++;
            return true;
        }

        return false;
    }


    public boolean transferir(Conta destino, double valor) {
        return this.sacar(valor) && destino.depositar(valor);
    }


    public double[] verExtrato() {
        return extrato;
    }

    @Override
    public String toString() {
        return "main.Conta{" +
                "numero=" + numero +
                ", saldo=" + saldo +
                ", limite=" + limite +
                '}';
    }
}