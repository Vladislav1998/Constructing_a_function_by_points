package com.company;

import java.lang.Exception;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Scanner;

public class Function_F_XML {
    // сохраняем объект в XML файл
    public static void convObjXml(Function_F_x func, String filePaht) {
        try {
            JAXBContext context = JAXBContext.newInstance(Function_F_x.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // маршаллинг объекта в файл
            marshaller.marshal(func, new File(filePaht));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Function_F_x fromXmlToObject(String filePath) {
        Function_F_x function_f_x = new Function_F_x();
        try {

            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Function_F_x.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            function_f_x = (Function_F_x) unmarshaller.unmarshal(new File(filePath));

            try {
                try {
                    UnequalArrays.difArray(function_f_x);
                } catch (UnequalArrays unequalArrays) {
                    unequalArrays.printStackTrace();
                }
                IncorrectData.incorectValue((Function_F_x) unmarshaller.unmarshal(new File(filePath)));
            } catch (IncorrectData incData) {
                Function_F_x f_x = new Function_F_x();
                Scanner enter = new Scanner(System.in);
                System.out.println("The function Function_F_x is incorrect!" +
                        " Please enter the data again!");
                System.out.println("Number of points:");
                int num = enter.nextInt();
                System.out.println("Enter points");
                for (int i = 0; i < num; i++) {
                    double X = enter.nextDouble();
                    double Y = enter.nextDouble();
                    f_x.addingPoints(X, Y);
                }
                Function_F_XML.convObjXml(f_x, "Function_F_x.xml");
                f_x = Function_F_XML.fromXmlToObject("Function_F_x.xml");
            }
            return function_f_x;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deletePoint(Function_F_x f, double x, double y) {
        Integer j = null;
        if (f.amount() == 0) {
            return;
        }
        for (int i = 0; i < f.getAx().length; i++) {

            if (x == f.getX(i)) {
                j = i;
            }
        }
        double[] AX = new double[f.amount() - 1];
        double[] AY = new double[f.amount() - 1];
        if (j == 0) {
            System.arraycopy(f.getAx(), 1, AX, 0, j);
            System.arraycopy(f.getAy(), 1, AY, 0, j);
        }
        System.arraycopy(f.getAx(), 0, AX, 0, j);
        System.arraycopy(f.getAx(), j + 1, AX, j, f.amount() - (j - 1));

        System.arraycopy(f.getAy(), 0, AY, 0, j);
        System.arraycopy(f.getAy(), j + 1, AY, j, f.amount() - (j - 1));
        Function_F_x func = new Function_F_x();
        for (int i = 0; i < AX.length; i++) {
            func.addingPoints(AX[i], AY[i]);
        }
        convObjXml(func, "Function_F_x.xml");
    }

  /*  public static void main(String[] args) {
        //файл для сохранения
        String nameoffile = "Function_F_x.xml";
        //создаем объект  с какими-то данными

        Function_F_x func = new Function_F_x();
        func.addingPoints(1, 1);
        func.addingPoints(2, 2);
        func.addingPoints(3, 3);
        //сохр обьект в хмл файл

        convObjXml(func, nameoffile);

        //восстановление из хмл файда
        Function_F_x unmF_x = fromXmlToObject(nameoffile);
        if (unmF_x != null) {
            System.out.println(unmF_x.getY(2));
        }
        try {

            System.out.println(new DichotomyMethod().dichotomy(0, 6, unmF_x));
        } catch (Exception e) {
            System.out.println("Invalid limit entered");
        }
    }*/

}
