package com.example.vladislav.currencyconverter;

import com.example.vladislav.currencyconverter.beans.CurrenciesContainer;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileReader;

/**
 * Created by vladislav on 20.03.17.
 */

public class XMLParser {

    public void parse() {

        Serializer serializer = new Persister();
        File source = new File(Consts.getCurrenciesFile());

        try {
            CurrenciesContainer example = serializer.read(CurrenciesContainer.class, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
