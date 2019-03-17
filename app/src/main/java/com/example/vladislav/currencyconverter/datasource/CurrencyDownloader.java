package com.example.vladislav.currencyconverter.datasource;

import android.support.annotation.VisibleForTesting;

import com.example.vladislav.currencyconverter.XMLParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Created by vladislav on 18.03.17.
 */

public class CurrencyDownloader {

    private static InputStream stream = null;
    private String mUrl = null;
    private static Logger log = getLogger(CurrencyDownloader.class.getName());

    public CurrencyDownloader(String Url) throws IOException {
        mUrl = Url;
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public InputStream getStreamFromUrl(String Url) throws IOException {

        URL url = null;
        url = new URL(Url);
        InputStream in = null;
        HttpURLConnection urlConnection = null;

        urlConnection = (HttpURLConnection) url.openConnection();
        // Why does it make a downloading work ?
        urlConnection.getRequestMethod();
        in = new BufferedInputStream(urlConnection.getInputStream());

        return in;
    }

    public InputStream getStreamFromUrl() throws IOException {

        URL url = null;
        url = new URL(mUrl);
        InputStream in = null;
        HttpURLConnection urlConnection = null;

        urlConnection = (HttpURLConnection) url.openConnection();
        // Why does it make a downloading work ?
        urlConnection.getRequestMethod();
        in = new BufferedInputStream(urlConnection.getInputStream());

        return in;
    }

    private void readStream(InputStream in) {

        InputStreamReader isw = new InputStreamReader(in);

        int data = 0;
        try {
            data = isw.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (data != -1) {
            char current = (char) data;
            try {
                data = isw.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.print(current);
        }
        try {
            isw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public void saveXMLToFile(InputStream inputStream, String filePath) throws IOException {

        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        File file = new File(filePath);
        PrintWriter printWriter = new PrintWriter(filePath);

        if (!file.exists()) {
            log.log(Level.WARNING, "File path specified doesn't exist.");
            if (file.isDirectory()) {
                log.log(Level.WARNING, "File path specified is a directory, make it point to a file.");
                return;
            }
            if (file.createNewFile()) {
                log.log(Level.WARNING, "File " + filePath + " created.");
            } else {
                log.log(Level.WARNING, "File " + filePath + " is NOT created.");
            }
        }
        log.log(Level.INFO, "Saving currency data to file named - " + filePath);

        String sCurrentLine;
        while ((sCurrentLine = bufferedReader.readLine()) != null) {
            printWriter.println(sCurrentLine);
        }
        log.log(Level.INFO, "Data is saved.");

        inputStream.close();
        printWriter.close();
    }

}
