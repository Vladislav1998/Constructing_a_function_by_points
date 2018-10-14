package com.company;

public class Point {
    public Double x;
    public Double y;

    public double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Point() {
    }

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point:" +
                "\n  x=" + x +
                "\n, y=" + y ;
    }

}