package com.company;

public class DichotomyMethod {
    private  double x;
    private ExtFunction funcF;
    private ExtFunction funcG;

    public double getX() {
        return x;
    }

    public ExtFunction getFuncF() {
        return funcF;
    }
    public void setFuncF(ExtFunction funcF) {
        this.funcF = funcF;
    }
    public ExtFunction getFuncG() {
        return funcG;
    }
    public void setFuncG(ExtFunction funcG) {
        this.funcG = funcG;
    }
    public void setBothFunctions(ExtFunction funcF, ExtFunction funcG) {
        this.funcF = funcF;
        this.funcG = funcG;}

    public double dichotomy( double a, double b,double eps) throws ClassOfExeption {

        if (a >= b) {
            throw new ClassOfExeption(a, b);
            // throw new Exception("Entered an invalid value");
        }
        double l = a, r = b, x;
        double func1, func2;
        //final double eps = 0.00000000000001;
        int k = 0;
        do {
            x = (l + r) / 2;
            func1 = funcF.y(x - eps)-funcG.y(x-eps);
            func2 = funcF.y(x + eps)-funcG.y(x+eps);
            if (func1 < func2)
                l = x;
            else
                r = x;
            k++;
        } while (Math.abs(b - a) > eps && k < 20000);
        this.x=x;
        return funcF.y(x)-funcG.y(x);

    }


}
