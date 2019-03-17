package com.example.vladislav.currencyconverter.datasource;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.vladislav.currencyconverter.Consts;

import java.io.IOException;

/**
 * Service that downloads currencies quotations and saves them to an XML file.
 */
public class CurrenciesHandlingService extends IntentService {

    public CurrenciesHandlingService() {
        super("CurrenciesHandlingService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent intentInformInitialActivity = new Intent();
        try {
            new CurrenciesHandler().persistCurrenciesToFile(
                    Consts.getUrl(),
                    Consts.getCurrenciesFile());
            intentInformInitialActivity.
                    setAction(Consts.SERVICE_REPLY).
                    putExtra(Consts.SERVICE_REPLY, Consts.SERVICE_SUCCESS);
            Log.e(getClass().getCanonicalName(),"Currencies were loaded successfully.");
        } catch (IOException e) {
            intentInformInitialActivity.
                    setAction(Consts.SERVICE_REPLY).
                    putExtra(Consts.SERVICE_REPLY, Consts.SERVICE_FAIL);
            Log.e(getClass().getCanonicalName(),e.getMessage());
        }
        // Sending a broadcast message of the outcome of a currencies downloading.
        sendBroadcast(intentInformInitialActivity);

    }

}
