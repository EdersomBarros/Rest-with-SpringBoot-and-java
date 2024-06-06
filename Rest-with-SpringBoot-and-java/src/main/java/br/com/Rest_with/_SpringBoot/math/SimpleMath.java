package br.com.Rest_with._SpringBoot.math;

public class SimpleMath {
    public double sum(double number1, double number2) {
        return number1 + number2;
    }
    public double subtract(double number1, double number2) {
        return number1 - number2;
    }
    public double multiply(double number1, double number2) {
        return number1 * number2;
    }
    public double divide(double number1, double number2) throws ArithmeticException {
        if (number2 == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida!");
        }
        return number1 / number2;
    }
    public double average(double number1, double number2) {
        return (number1 + number2) / 2;
    }
    public double squareRoot(double number) throws IllegalArgumentException {
        if (number < 0) {
            throw new IllegalArgumentException("Raiz quadrada de número negativo não é suportada!");
        }
        return Math.sqrt(number);
    }
}
