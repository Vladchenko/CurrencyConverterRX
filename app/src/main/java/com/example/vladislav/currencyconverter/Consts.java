package com.example.vladislav.currencyconverter;

/**
 * Holds the variables used throughout the application.
 */
public class Consts {

    private static String sUrl = "http://www.cbr.ru/scripts/XML_daily.asp";

    private Consts() {}

    public static String getUrl() {
        return sUrl;
    }

}
