package com.example.vladislav.currencyconverter;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testing utility methods common to several classes of the application.
 */

public class CommonUtilsTest {

    @Test
    public void UtilsTestIsValidFilePath1() {
        assertFalse(CommonUtils.isFilePathValid(""));
    }

    @Test
    public void UtilsTestIsValidFilePath2() {
        assertFalse(CommonUtils.isFilePathValid("G:"));
    }

    @Test
    public void UtilsTestIsValidFilePath3() {
        assertFalse(CommonUtils.isFilePathValid("G:"));
    }

    @Test
    public void UtilsTestIsValidFilePath4() {
        assertTrue(CommonUtils.isFilePathValid("G:/programs/1"));
    }

    @Test
    public void UtilsTestIsValidFilePath5() {
        assertTrue(CommonUtils.isFilePathValid("G:/programs/1/1.1"));
    }

    @Test
    public void UtilsTestIsValidFilePath6() {
        assertTrue(CommonUtils.isFilePathValid("G:/programs/office/e.exe"));
    }


    @Test
    public void UtilsTestIsValidURL1() {
        assertFalse(CommonUtils.isURLValid("h"));
    }

    @Test
    public void UtilsTestIsValidURL2() {
        assertFalse(CommonUtils.isURLValid("http"));
    }

    @Test
    public void UtilsTestIsValidURL3() {
        assertFalse(CommonUtils.isURLValid("http:"));
    }

    @Test
    public void UtilsTestIsValidURL4() {
        assertFalse(CommonUtils.isURLValid("http:\\"));
    }

    @Test
    public void UtilsTestIsValidURL5() {
        assertFalse(CommonUtils.isURLValid("http://"));
    }

    @Test
    public void UtilsTestIsValidURL6() {
        assertTrue(CommonUtils.isURLValid("http://example.com"));
    }

    @Test
    public void UtilsTestIsValidURL7() {
        assertTrue(CommonUtils.isURLValid("http://www.cbr.ru/scripts/XML_daily.asp"));
    }
}
