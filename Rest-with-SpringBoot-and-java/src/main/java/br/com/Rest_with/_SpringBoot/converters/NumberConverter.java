package br.com.Rest_with._SpringBoot.converters;

public class NumberConverter {

    public static Double convertToDouble(String strNumber) {
        if(strNumber == null) return 0D;
        String number = strNumber.replaceAll(",", ".");
        if(isMumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    public static boolean isMumeric(String strNumber) {
        if(strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
