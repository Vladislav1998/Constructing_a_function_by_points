package com.company;

public class UnequalArrays extends Exception {
    public static void difArray(Function f) throws UnequalArrays {
        if (f.getAx().length < f.getAy().length || f.getAx().length > f.getAy().length) {
            throw new UnequalArrays();
        }
    }

}
