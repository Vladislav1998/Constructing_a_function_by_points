package com.company;

public class IncorrectData extends Exception {
    public static void incorectValue(Function function) throws IncorrectData {
        for (int i = 0; i < function.amount(); i++) {
            for (int j = 0; j < function.amount(); j++) {
                if (i != j) {
                   if (function.getX(i) == function.getX(j)) {
                       throw new IncorrectData();
                   }
                }
            }
        }
        int s = 0;
        for (int i = 0; i < function.amount(); i++) {
            if (function.getX(i) == 0) {
                s++;
            }
        }
        if (s == function.amount()) {
            throw new IncorrectData();
        }
        int s1 = 0;
        for (int i = 0; i < function.amount(); i++) {
            if (function.getX(i) == 0) {
                s1++;
            }
        }
        if (s1 == function.amount()) {
            throw new IncorrectData();
        }
    }
}
