package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class CopyingX extends  Exception {
    public  static void wrongValue(Function function)throws CopyingX {
        int p = 0;
        ArrayList<Double> sortList = new ArrayList<>();
        for (int i = 0; i < function.amount(); i++) {
            sortList.add(function.getAx()[i]);
        }
        HashMap hm = new HashMap();
        for (int i = 0; i < sortList.size() - 1; i++) {
            for (int j = i + 1; j < sortList.size() ; j++) {
                if (sortList.get(i).equals(sortList.get(j))) {
                    p++;

                }

            }
        }

        if(p!=0)
        {
            throw new CopyingX();
        }
    }
}
