package com.example.vladislav.currencyconverter;

import com.example.vladislav.currencyconverter.model.beans.CurrenciesContainer;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;

import static java.util.logging.Logger.getLogger;

/**
 * Uploading, parsing and deserializing the currencies data into beans.
 */

public class CurrenciesDeserializer {

    public CurrenciesContainer parse(InputStream inputStream) throws Exception {
        CurrenciesContainer currenciesContainer;
        Serializer serializer = new Persister();
        currenciesContainer = serializer.read(CurrenciesContainer.class, inputStream);

        return currenciesContainer;
    }
}
