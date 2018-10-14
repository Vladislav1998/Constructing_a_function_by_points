package com.company;

import java.io.IOException;


public class ConsolProg {

    public static void main(String[] args) {
        DichotomyMethod dichotomyMethod = new DichotomyMethod();
        String nameoffile = "D:\\Курсовая(2)\\Function_F_x.xml";
        Function_F_XML F = new Function_F_XML();
 /* FFunction f=new FFunction();
     f.addPoint(-1,-1);
     f.addPoint(0,0);
     f.addPoint(1,1);
     f.addPoint(2,2);
     F.convertObjectToXml(f, fileName);*/
        Function_F_x func1 = F.fromXmlToObject(nameoffile);

        String nameoffile1 = "D:\\Курсовая(2)\\Function_G_x.xml";
        Function_G_XML G = new Function_G_XML();
 /* GFunction g=new GFunction();
     g.addPoint(-2,4);
     g.addPoint(0,0);
     g.addPoint(2,4);
     */
        Function_G_x func2 = G.fromXmlToObj(nameoffile1);
        dichotomyMethod.setBothFunctions(func1,func2);
        try {
            System.out.println(
                    "________INTERPOLATION____________\n"+
                    "___________OF THE________________\n"+
                    "_________DICHOTOMY_______________\n");
           System.out.println("Maximumu of function:"+dichotomyMethod.dichotomy(2,4,0.0001));
           // try {
                //new Function_F_x().saveReport("Report", func1, func2,2,4,dichotomyMethod.dichotomy(2,4,0.000001));
           // } catch (IOException e) {
          //      e.printStackTrace();
          //  }
        } catch (ClassOfExeption e) {
            e.printStackTrace();
            System.out.println("Invalid limit entered");
        }
    }
}
