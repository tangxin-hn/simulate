package util;

//复数类，定义了复数的一系列操作

public class Complex
{
    private final double re;   // the real part
    private final double im;   // the imaginary part

    // create a new object with the given real and imaginary parts
    public Complex(double real, double imag)
    {
        re = real;
        im = imag;
    }

    // return a string representation of the invoking Complex object
    public String toString() {

        if (im == 0) return String.format("%.4f", re) + "";
        if (re == 0) return String.format("%.4f", im) + "i";
        if (im <  0) return String.format("%.4f", re) + "-" + String.format("%.4f", -im) + "i";
        return String.format("%.4f", re) + "+" + String.format("%.4f", im) + "i";
    }

    // return abs/modulus/magnitude and angle/phase/argument
    public double abs()   { return Math.hypot(re, im); }  // Math.sqrt(re*re + im*im)

    // return a new Complex object whose value is (this + b)
    public Complex plus(Complex b) {
        Complex a = this;             // invoking object
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    /**
     * return a new Complex object whose value is (this - b)
     *          减法
     * @param b
     * @return
     */
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    /**
     *  return a new Complex object whose value is (this * b)
     *    乘以一个复数
     * @param b  被乘的复数
     * @return
     */
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    /**
     *scalar multiplication
     * return a new object whose value is (this * alpha)</br>
     *                乘以一个实数
     */
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    /**
     *  return a new Complex object whose value is the conjugate of this</br>
     *   共轭复数
     * @return
     */
    public Complex conjugate() {  return new Complex(re, -im); }

    /**
     *  return a new Complex object whose value is the reciprocal of this</br>
     *     倒数 a +bi 的倒数  </br>
     *   a -bi</br>
     * —————————————</br>
     *   a^2  + b ^2</br>
     */
    public Complex reciprocal()
    {
        double scale = re*re + im*im;
        return new Complex(re / scale, -im / scale);
    }

    /**
     *  return the real part
     * @return
     */
    public double re() { return re; }
    /**
     *  imaginary part
     * @return
     */
    public double im() { return im; }

    /**
     *  return a / b
     */
    public Complex divides(Complex b)
    {
        Complex a = this;
        return a.times(b.reciprocal());
    }

    // return a new Complex object whose value is the complex sine of this
    public Complex sin()
    {
        return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    // return a new Complex object whose value is the complex cosine of this
    public Complex cos()
    {
        return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    //判断两个复数是否相等
    public boolean equal(Complex a)
    {
        if( Math.abs(this.re-a.re)<0.00000000000001 && Math.abs(this.im-a.im)<0.00000000000001 )
        {
            return true;
        }
        return false;
    }
}
