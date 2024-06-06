package br.com.Rest_with._SpringBoot.controller;

import br.com.Rest_with._SpringBoot.converters.NumberConverter;
import br.com.Rest_with._SpringBoot.exceptions.UnsupportedMathOperationException;
import br.com.Rest_with._SpringBoot.math.SimpleMath;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;



@RestController
public class MathController {

    private final AtomicLong counter = new AtomicLong();
    private final SimpleMath simpleMath = new SimpleMath();

    @RequestMapping(value = "/calculate/{operation}/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double calculate(
            @PathVariable(value = "operation")String operation,
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo)
    throws Exception{
        if(!NumberConverter.isMumeric(numberOne) || !NumberConverter.isMumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        double num1 = NumberConverter.convertToDouble(numberOne);
        double num2 = NumberConverter.convertToDouble(numberTwo);

        switch (operation.toLowerCase()){
            case "sum":
                return simpleMath.sum(num1, num2);
            case "subtraction":
                return simpleMath.subtract(num1, num2);
            case "multiplication":
                return simpleMath.multiply(num1, num2);
            case "division":
                return simpleMath.divide(num1, num2);
            case "average":
                return simpleMath.average(num1,num2);
            case "square_root":
                return simpleMath.squareRoot(num1);
            default:
                throw new UnsupportedMathOperationException("Invalid operation: " + operation);

        }
    }


}
