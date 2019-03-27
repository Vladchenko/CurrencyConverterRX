package com.example.vladislav.currencyconverter.di;

import com.example.vladislav.currencyconverter.presenter.CurrencyPresenter;
import com.example.vladislav.currencyconverter.presenter.CurrencyPresenterImpl;
import com.example.vladislav.currencyconverter.view.CurrenciesView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CurrencyModule {

    private CurrenciesView mView;

    public CurrencyModule(CurrenciesView view) {
        mView = view;
    }

    @Provides
    @Singleton
    CurrencyPresenter providesCurrencyPresenter(CurrenciesView currenciesView) {
        return new CurrencyPresenterImpl(currenciesView);
    }

    @Provides
    @Singleton
    CurrenciesView providesCurrenciesView() {
        return mView;
    }
}
