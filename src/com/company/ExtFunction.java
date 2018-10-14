package com.company;

interface ExtFunction{
   abstract double y(double x);
}
/*public class InterpolarFunction {
    static double lagrangelFunc(Function arr, int n, double X) {
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            double F = 1.0;
            for (int j = 0;  j< n ; j++)
                if (j != i)
                    F *= (X - arr.getAx()[j]) / (arr.getAx()[i]
                            - arr.getAx()[j]);


                result += F * arr.getAy()[i];

        }
        return result;
    }*/

