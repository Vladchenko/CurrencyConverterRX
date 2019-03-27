package com.example.vladislav.currencyconverter.di;

import com.example.vladislav.currencyconverter.view.InitialActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CurrencyModule.class})
public interface CurrencyComponent {

    void inject(InitialActivity activity);
}
