package com.company;

import sun.nio.ch.FileKey;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Scanner;

public class Function_G_XML {
    public static void convObjXml(Function_G_x f, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Function_G_x.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(f, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Function_G_x fromXmlToObj(String filePath) {
        Function_G_x function_g_x = new Function_G_x();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Function_G_x.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            function_g_x = (Function_G_x) unmarshaller.unmarshal(new File(filePath));
            try {

                IncorrectData.incorectValue((Function_G_x) unmarshaller.unmarshal(new File(filePath)));
            } catch (IncorrectData incData) {
                Function_G_x g_x = new Function_G_x();
                Scanner enter = new Scanner(System.in);
                System.out.println("The function Function_G_x is incorrect!" +
                        " Please enter the data again!");
                System.out.println("Number of points:");
                int num = enter.nextInt();
                System.out.println("Enter points");
                for (int i = 0; i < num; i++) {
                    double X = enter.nextDouble();
                    double Y = enter.nextDouble();
                    g_x.addingPoints(X, Y);
                }
                Function_G_XML.convObjXml(g_x, "Function_G_x.xml");
                g_x = Function_G_XML.fromXmlToObj("Function_G_x.xml");
            }
            return function_g_x;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deletePoint(Function_G_x g, double x, double y) {
        Integer j = null;
        if (g.amount() == 0) {
            return;
        }
        for (int i = 0; i < g.getAx().length; i++) {
            if (x == g.getX(i)) {
                j = i;
            }
        }
        double[] AX = new double[g.amount() - 1];
        double[] AY = new double[g.amount() - 1];
        if (j == 0) {
            System.arraycopy(g.getAx(), 1, AX, 0, j);
            System.arraycopy(g.getAy(), 1, AY, 0, j);
        }
        System.arraycopy(g.getAx(), 0, AX, 0, j);
        System.arraycopy(g.getAx(), j + 1, AX, j, g.amount() - (j - 1));

        System.arraycopy(g.getAy(), 0, AY, 0, j);
        System.arraycopy(g.getAy(), j + 1, AY, j, g.amount() - (j - 1));
        Function_G_x func = new Function_G_x();
        for (int i = 0; i < AX.length; i++) {
            func.addingPoints(AX[i], AY[i]);
        }
        convObjXml(func, "Function_G_x.xml");

    }
  /*  public static void main(String[] args) {
        String nameoffile = "Function_G_x.xml";

        Function_G_x function_g_x = new Function_G_x();
        function_g_x.addingPoints(-1, 1);
        function_g_x.addingPoints(0, 0);
        function_g_x.addingPoints(2, 4);

        convObjXml(function_g_x, nameoffile);
        Function_G_x unmG = fromXmlToObj(nameoffile);
        if (unmG != null) {
            System.out.println(unmG.toString());
        }
        try {
            System.out.println(new DichotomyMethod().dichotomy(0, 2, unmG));
        } catch (Exception e) {
            System.out.println("Invalid limit entered");
        }
    }*/
}
