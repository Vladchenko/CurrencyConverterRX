package com.example.vladislav.currencyconverter;

import com.example.vladislav.currencyconverter.model.datasource.CurrencyDownloader;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by vladislav on 23.03.17.
 */

public class CurrencyDownloaderTest {

    private CurrencyDownloader mCurrencyDownloader = new CurrencyDownloader(
            "http://www.cbr.ru/scripts/XML_daily.asp");

    public CurrencyDownloaderTest() throws IOException {
    }

    @Test(expected = IOException.class)
    public void getStreamFromURLTestWrongURL() throws IOException {
        mCurrencyDownloader.getStreamFromUrl("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getStreamFromURLTestWrongURL2() throws IOException {
        mCurrencyDownloader.getStreamFromUrl("http:");
    }

    @Test
    public void getStreamFromURLTestCorrectURL() throws IOException {
        mCurrencyDownloader.getStreamFromUrl("http://www.cbr.ru/scripts/XML_daily.asp");
    }

}
