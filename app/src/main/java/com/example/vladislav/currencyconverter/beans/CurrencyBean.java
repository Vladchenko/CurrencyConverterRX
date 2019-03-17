package com.example.vladislav.currencyconverter.beans;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by vladislav on 20.03.17.
 */

@Element(name="Valute")
public class CurrencyBean {

    /**
     * Temporary message to know the contents of an currency entry read from a web.
     *
     * <Valute ID="R01589">
     *      <NumCode>960</NumCode>
     *      <CharCode>XDR</CharCode>
     *      <Nominal>1</Nominal>
     *      <Name>��� (����������� ����� �������������)</Name>
     *      <Value>78,5631</Value>
     * </Valute>
     */

    @Attribute(name="ID")
    private String mID;              // say, "R01589"
    @Element(name="NumCode")
    private int mNumericCode;        // say, 960
    @Element(name="CharCode")
    private String mCharacterCode;   // say, XDR
    @Element(name="Name")
    private String mName;            // no idea what's the name of this currency.
    @Element(name="Value")
    private String mValue;           // say, 78,5631
    @Element(name="Nominal")
    private double mNominal;         // say, 1

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        this.mID = ID;
    }

    public int getNumericCode() {
        return mNumericCode;
    }

    public void setNumericCode(int numericCode) {
        this.mNumericCode = numericCode;
    }

    public String getCharacterCode() {
        return mCharacterCode;
    }

    public void setCharacterCode(String characterCode) {
        this.mCharacterCode = characterCode;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        this.mValue = value;
    }
}
