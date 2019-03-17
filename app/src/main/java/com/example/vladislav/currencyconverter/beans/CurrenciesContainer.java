package com.example.vladislav.currencyconverter.beans;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Contents for the root entry of a currency XML file.
 */

@Root(name="ValCurs")
public class CurrenciesContainer {

    @ElementList(entry = "Valute", inline=true)
    private List<CurrencyBean> mCurrenciesList = new ArrayList<>();

    @Attribute(name = "name")
    private String mName;

    @Attribute(name = "Date")
    private String mDate;

    public CurrenciesContainer() {}

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public List getProperties() {
        return getCurrenciesList();
    }

    public List<CurrencyBean> getCurrenciesList() {
        return mCurrenciesList;
    }

    public void setCurrenciesList(List<CurrencyBean> mCurrenciesList) {
        this.mCurrenciesList = mCurrenciesList;
    }

    public void addCurrencies(CurrenciesContainer currenciesContainer) {
        mCurrenciesList.addAll(currenciesContainer.getCurrenciesList());
    }

    public void addCurrency(CurrencyBean currencyBean) {
        mCurrenciesList.add(currencyBean);
    }

}
