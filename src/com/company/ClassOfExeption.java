package com.company;

public class ClassOfExeption extends Exception {
    public double left;
    public double right;

    public ClassOfExeption(double left, double right) {
        this.left = left;
        this.right = right;
    }

   /* public ClassOfExeption(String message, double left, double right) {
        super(message);
        this.left = left;
        this.right = right;
    }*/

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }
}
