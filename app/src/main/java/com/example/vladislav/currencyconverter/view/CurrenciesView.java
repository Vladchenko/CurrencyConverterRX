package com.example.vladislav.currencyconverter.view;

import com.example.vladislav.currencyconverter.model.beans.CurrenciesContainer;

public interface CurrenciesView {

    void displayCurrencies(CurrenciesContainer currenciesContainer);

    void displayError();
}
