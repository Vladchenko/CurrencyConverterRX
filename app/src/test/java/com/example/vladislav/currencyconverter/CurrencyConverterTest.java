package com.example.vladislav.currencyconverter;

import android.content.res.Configuration;
import android.content.res.Resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Locale;

import static com.example.vladislav.currencyconverter.logic.CurrencyConverter.convertCurrency;
import static java.security.AccessController.getContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing a currency convertion logic.
 */
public class CurrencyConverterTest {

    @Test
    public void convertCurrencyTestEquals() {
        assertEquals(convertCurrency(100,1,1),"100");
    }

    @Test
    public void convertCurrencyTestEqualsWithFractionalPart() {
        assertEquals(convertCurrency(200,2,3),"133,34");
    }

    @Test
    public void convertCurrencyTestEqualsWithFractionalPartAndEnglishLocale() {
        Locale.setDefault(new Locale("en", "EN"));
        assertEquals(convertCurrency(200,2,3),"133,34");
    }

    @Test
    public void convertCurrencyTestEqualsWithFractionalPartAndRussianLocale() {
        Locale.setDefault(new Locale("ru", "RU"));
        assertEquals(convertCurrency(200,2,3),"133,34");
    }

    @Test
    public void convertCurrencyTestEqualsWithFractionalPartAndSomeLocale() {
        Locale.setDefault(new Locale("in", "IN"));
        assertEquals(convertCurrency(200,2,3),"133,34");
    }
}