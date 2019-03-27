package com.example.vladislav.currencyconverter.presenter;

import com.example.vladislav.currencyconverter.CurrenciesDeserializer;

import java.io.InputStream;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import com.example.vladislav.currencyconverter.model.beans.CurrenciesContainer;
import com.example.vladislav.currencyconverter.model.datasource.CurrencyDownloader;
import com.example.vladislav.currencyconverter.view.CurrenciesView;

import javax.inject.Inject;

public class CurrencyPresenterImpl implements CurrencyPresenter {

    private final String mUrl = "http://www.cbr.ru/scripts/XML_daily.asp";

    private CurrenciesContainer mCurrenciesContainer;
    private CurrenciesView mCurrenciesView;
    private Disposable mSingle;

    @Inject
    public CurrencyPresenterImpl(CurrenciesView currenciesView) {
        mCurrenciesView = currenciesView;
        downloadCurrencies();
    }

    @Override
    public void provideCurrencies() {
        mCurrenciesView.displayCurrencies(mCurrenciesContainer);
    }

    @Override
    public void provideError() {
        mCurrenciesView.displayError();
    }

    private void downloadCurrencies() {
        mSingle = Single.create(
                emitter -> {
                    Thread thread = new Thread(() -> {
                        try {
                            InputStream inputStream = new CurrencyDownloader(mUrl).getStreamFromUrl();
                            mCurrenciesContainer = new CurrenciesDeserializer().parse(inputStream);
                            emitter.onSuccess(mCurrenciesContainer);
                        } catch (Exception e) {
                            emitter.onError(e);
                        }
                    });
                    // Imitating some work by putting thread to sleep
                    try {
                        Thread.sleep(3000);
                    } catch(InterruptedException ex) {
                        // No point in processing this exception, since it is just sleeping might
                        // be interrupted, but not some serious thread work
                    }
                    thread.start();
                }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnDispose(new Action() {
                    @Override
                    public void run() {
                        System.out.println("Currencies downloader disposed");
                    }
                })
                .subscribe(result -> mCurrenciesView.displayCurrencies(mCurrenciesContainer),
                        result -> mCurrenciesView.displayError());
    }
}
