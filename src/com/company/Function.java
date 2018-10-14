package com.company;

import javafx.collections.ObservableList;
import javafx.scene.image.WritableImage;

import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public abstract class Function implements ExtFunction {
    public abstract double[] getAx();

    public abstract double[] getAy();

    public abstract double getX(int i);

    public abstract double getY(int i);

    public abstract int amount();

    public abstract void addingPoints(double x, double y);

    public abstract void setPoint(int i, double x, double y);

    public abstract void deleteLast();

    public void saveReport(String fileName, ObservableList<Point> observableList, ObservableList<Point> observableList1, double a, double b, double maximum, WritableImage image) throws IOException {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"))) {
            out.printf("<html>%n");
            out.printf("<head>%n");
            out.printf("<meta http-equiv='Content-Type' content='text/html; " + "charset=UTF-8'>%n");
            out.printf("</head>%n");
            out.printf("<body>%n");
            out.printf("<h2>Report</h2>%n");
            out.printf("<h2> student of group KH-36E</h2>%n");
            out.printf("<h2>Shestopalov Vladislav</h2>%n");
            out.printf("<p>");
            out.println("The first function F(x):");
            out.printf(observableList.toString() + "\n");
            out.printf("<p>");
            out.println("The second function G(x):");
            out.printf(observableList1.toString() + "\n");
            out.printf("</p>");
            out.printf("</body>%n");
            out.println("MaxFunction(F(x)-G(x))=" + maximum + "\n");
            out.printf("<p>");
            out.println("On the interval [" + a + ";" + b + "]");
            out.printf("</p>");
            out.printf("<img src=\"mySchedule.png\">");
            out.printf("</p>");
            out.printf("</body>%n");
            out.printf("</html>%n");
        } catch (IOException e) {
            throw new IOException(fileName);
        }
    }

    @Override
    public String toString() {
        String s = "  ";
        for (int i = 0; i < amount(); i++) {
            s += "x=" + getX(i) + "\t y" + getY(i) + "\n";
        }
        return s + "\n";
    }
}
