package com.example.vladislav.currencyconverter.logic;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Logic to convert the currency from one to another.
 */

public class CurrencyConverter {

    private static DecimalFormat sDecimalFormat = new DecimalFormat("#.##");

    public static String convertCurrency(double amount, double initialQuotation, double resultingQuotation) {

        if (amount <= 0
                || initialQuotation <=0
                || resultingQuotation <=0) {
            throw new ArithmeticException("Some argument is zero or negative.");
        }

        sDecimalFormat.setRoundingMode(RoundingMode.CEILING);
        double value = amount * initialQuotation / resultingQuotation;

        if (value == (int)value) {
            return Integer.toString((int)value);
        } else {
            return sDecimalFormat.format(value);
        }
    }

}
