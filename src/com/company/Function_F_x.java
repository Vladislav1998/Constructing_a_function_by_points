package com.company;

import javax.xml.bind.annotation.*;

//отделяем корневой элемент
@XmlRootElement(name = "Function_F_x")
@XmlType(propOrder = {"ax", "ay"})
public class Function_F_x extends Function {
    private double[] ax = {};
    private double[] ay = {};

    @XmlElementWrapper(name = "x")
    public double[] getAx() {
        return ax;
    }

    void setAx(double[] ax) {
        this.ax = ax;
    }

    @XmlElementWrapper(name = "y")
    public double[] getAy() {
        return ay;
    }

    void setAy(double[] ay) {
        this.ay = ay;
    }

    public void setPoint(int i, double x, double y) {
        double[] A = {};
        double[] B = {};
        if (i < amount()) {
            A[i] = x;
            B[i] = y;
        }
        setAx(A);
        setAy(B);
    }

    @Override
    public double getX(int i) {
        return ax[i];
    }

    @Override
    public double getY(int i) {
        return ay[i];
    }

    @Override
    public int amount() {
        return ax.length;
    }  // Можна ay.length, вони однакові

    @Override
    public void addingPoints(double x, double y) {
        double[] ax1 = new double[ax.length + 1];
        System.arraycopy(ax, 0, ax1, 0, ax.length);
        ax1[ax.length] = x;
        ax = ax1;
        double[] ay1 = new double[ay.length + 1];
        System.arraycopy(ay, 0, ay1, 0, ay.length);
        ay1[ay.length] = y;
        ay = ay1;
    }

    @Override
    public void deleteLast() {
        if (amount() == 0) {
            return;
        }
        double[] ax1 = new double[ax.length - 1];
        System.arraycopy(ax, 0, ax1, 0, ax1.length);
        ax = ax1;
        double[] ay1 = new double[ay.length - 1];
        System.arraycopy(ay, 0, ay1, 0, ay1.length);
        ay = ay1;
    }

    public void deletePoint(double x, double y) {
        new Function_F_XML().deletePoint(this, x, y);
    }

    @Override
    public double y(double x) {
        double result = 0.0;
        for (int i = 0; i < getAx().length; i++) {
            double F = 1.0;
            for (int j = 0; j < getAx().length; j++)
                if (j != i)
                    F *= (x - getAx()[j]) / (getAx()[i]
                            - getAx()[j]);


            result += F * getAy()[i];

        }
        return result;
    }
}

