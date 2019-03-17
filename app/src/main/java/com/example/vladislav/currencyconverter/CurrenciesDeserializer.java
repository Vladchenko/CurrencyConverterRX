package com.example.vladislav.currencyconverter;

import com.example.vladislav.currencyconverter.beans.CurrenciesContainer;
import com.example.vladislav.currencyconverter.datasource.CurrenciesHandler;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Uploading, parsing and deserializing the currencies data into java beans.
 */

public class CurrenciesDeserializer {

    private static Logger sLog = getLogger(CurrenciesHandler.class.getName());

    public CurrenciesContainer parse(InputStream inputStream) throws Exception {
        CurrenciesContainer currenciesContainer;
        Serializer serializer = new Persister();
        currenciesContainer = serializer.read(CurrenciesContainer.class, inputStream);
        sLog.log(Level.INFO, "Deserialized from file: " + Consts.getCurrenciesFile());

        return currenciesContainer;
    }
}