package org.kainos.ea.cli;

public class SalesEmployee extends Employee{
    private double monthlySales;
    private float comissionRate;

    public SalesEmployee(int employeeId, String name, double salary, double monthlySales, float comissionRate) {
        super(employeeId, name, salary);
        setComissionRate(comissionRate);
        setMonthlySales(monthlySales);
    }
    @Override
    public double calcPay() {return getSalary()/12 + (getMonthlySales()*getComissionRate());}

    public double getMonthlySales() {
        return getMonthlySales();
    }

    public void setMonthlySales(double monthlySales) {
        this.monthlySales =monthlySales;
    }

    public float getComissionRate() {
        return comissionRate;
    }

    public void setComissionRate(float comissionRate) {
        this.comissionRate = comissionRate;
    }
}
