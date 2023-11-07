public class ComplexNumber {
    public double real;
    public double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public static double re(ComplexNumber z) {
        return z.real;
    }
    public static double  im(ComplexNumber z) {
        return z.imaginary;
    }
    public double getReal() {
        return this.real;
    }
    public double getImaginary() {
        return this.imaginary;
    }
    public boolean isNear(ComplexNumber z, double margin) {
        return (this.real + margin > z.getReal() && this.real - margin < z.getReal()) 
        && (this.imaginary + margin > z.getImaginary() && this.imaginary - margin < z.getImaginary());
    }
    public ComplexNumber sum(ComplexNumber other) {
        double newReal = this.real + other.real;
        double newImaginary = this.imaginary + other.imaginary;
        return new ComplexNumber(newReal, newImaginary);
    }
    public ComplexNumber sum(double x) {
        return new ComplexNumber(this.real + x, this.imaginary);
    }
    public ComplexNumber multiply(ComplexNumber other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(newReal, newImaginary);
    }



    @Override
    public String toString() {
        return real + " + " + imaginary + "i";
    }

    public static void main(String[] args) {
    }
}
