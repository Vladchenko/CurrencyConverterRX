package com.example.vladislav.currencyconverter.view;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.vladislav.currencyconverter.CommonUtils;
import com.example.vladislav.currencyconverter.R;
import com.example.vladislav.currencyconverter.di.CurrencyComponent;
import com.example.vladislav.currencyconverter.di.CurrencyModule;
import com.example.vladislav.currencyconverter.di.DaggerCurrencyComponent;
import com.example.vladislav.currencyconverter.model.beans.CurrenciesContainer;
import com.example.vladislav.currencyconverter.model.beans.CurrencyBean;
import com.example.vladislav.currencyconverter.logic.CurrencyConverter;

import java.util.ArrayList;
import java.util.List;

import com.example.vladislav.currencyconverter.presenter.CurrencyPresenter;

import javax.inject.Inject;

// TODO
// 3. Make a methods smaller

/**
 * Holds the presentation and logic layer for the app.
 */
public class InitialActivity extends Activity implements CurrenciesView {

    @Inject
    CurrencyPresenter mCurrencyPresenter;

    private final String mUrl = "http://www.cbr.ru/scripts/XML_daily.asp";

    // Position of a USD currency in a currencies spinner's list
    private final int USD_POSITION = 10;
    // Position of a RUB currency in a currencies spinner's list
    private final int RUB_POSITION = 0;

    private static CurrencyComponent mComponent;

    private boolean mDownloadStatus = true;
    private CurrenciesContainer mCurrenciesContainer;
    private Spinner mInitialCurrencySpinner;        // Spinner for a initial currency (to convert from);
    private Spinner mResultingCurrencySpinner;      // Spinner for a resulting currency  (to convert to);
    private EditText mInitialCurrencyEditText;      // Edit text for a currency to convert from.
    private EditText mResultingCurrencyEditText;    // Edit text for a currency to convert to.
    private TextView mInitialCurrencyTextView;      // Quotation for a currency to convert from.
    private TextView mResultingCurrencyTextView;    // Quotation for a currency to convert to.
    private TextView mCurrenciesDownloadingErrorTextView;   // Error shown when a currencies downloading
    private TableLayout mCurrenciesTableLayout;
    private ProgressBar mProgressBar;
    private Button mConvertButton;
    private Resources mResources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mComponent = DaggerCurrencyComponent.builder()
                .currencyModule(new CurrencyModule(this))
                .build();
        mComponent.inject(this);

        mResources = getResources();
        setContentView(R.layout.initial_activity);

        if (!CommonUtils.isURLValid(mUrl)) {
            Log.e(getClass().getCanonicalName(), mResources.getString(R.string.wrong_URL));
            return;
        }

        mCurrenciesContainer = new CurrenciesContainer();

        findViews();

        mProgressBar.setVisibility(View.VISIBLE);
        mConvertButton.setVisibility(View.GONE);
        mCurrenciesDownloadingErrorTextView.setVisibility(View.GONE);
    }

    @Override
    public void displayCurrencies(CurrenciesContainer currenciesContainer) {
        mCurrenciesContainer = currenciesContainer;
        mDownloadStatus = true;
        mConvertButton.setText(R.string.convert_text);
        mProgressBar.setVisibility(View.GONE);
        mConvertButton.setVisibility(View.VISIBLE);
        mCurrenciesTableLayout.setVisibility(View.VISIBLE);
        mCurrenciesDownloadingErrorTextView.setVisibility(View.GONE);
        submitCurrenciesCharCodesToSpinners();
        setInitialCurrenciesInSpinners();
        setInitialCurrencyClickListener();
        initializeConvertButton();
        setSwapCurrenciesButtonClickListener();
    }

    @Override
    public void displayError() {
        mDownloadStatus = false;
        mProgressBar.setVisibility(View.GONE);
        mCurrenciesDownloadingErrorTextView.setText(R.string.currencies_downloading_failed);
        mCurrenciesDownloadingErrorTextView.setVisibility(View.VISIBLE);
        initializeConvertButton();
        mConvertButton.setText(R.string.retry_text);
        mConvertButton.setVisibility(View.VISIBLE);
    }

    private void findViews() {
        mProgressBar = (ProgressBar) findViewById(R.id.currencies_progressBar);
        mCurrenciesTableLayout = (TableLayout) findViewById(R.id.currencies_dashboard);
        mConvertButton = (Button) findViewById(R.id.convert_button);
        mInitialCurrencyEditText = (EditText) findViewById(R.id.initial_currency_edit_text);
        mResultingCurrencyEditText = (EditText) findViewById(R.id.resulting_currency_edit_text);
        mCurrenciesDownloadingErrorTextView = (TextView) findViewById(R.id.currencies_downloading_error_text_view);
        mInitialCurrencyTextView = (TextView) findViewById(R.id.initial_currency_quotation_text_view);
        mResultingCurrencyTextView = (TextView) findViewById(R.id.resulting_currency_quotation_text_view);
        mInitialCurrencySpinner = (Spinner) findViewById(R.id.initial_currency_spinner);
        mResultingCurrencySpinner = (Spinner) findViewById(R.id.resulting_currency_spinner);
    }

    private void convertCurrency() {
        String operationResult = CurrencyConverter.convertCurrency(
                Double.parseDouble(mInitialCurrencyEditText.getText().toString()),
                Double.parseDouble(mCurrenciesContainer.getCurrenciesList()
                        .get(mInitialCurrencySpinner.getSelectedItemPosition())
                        .getValue().replace(',', '.')),
                Double.parseDouble(mCurrenciesContainer.getCurrenciesList()
                        .get(mResultingCurrencySpinner.getSelectedItemPosition())
                        .getValue().replace(',', '.')));
        mResultingCurrencyEditText.setText(operationResult);
    }

    private void initializeConvertButton() {
        Button convertButton = (Button) findViewById(R.id.convert_button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDownloadStatus) {
                    if (mInitialCurrencySpinner.getSelectedItem().toString().equals(
                            mResultingCurrencySpinner.getSelectedItem().toString())) {
                        // Saying that currencies match
                        mResources.getString(R.string.same_currencies);
                    } else {
                        // Perform calculation only if an amount to be converted is present in a respective text_edit.
                        if (!mInitialCurrencyEditText.getText().toString().isEmpty()) {
                            try {
                                convertCurrency();
                            } catch (ArithmeticException | NumberFormatException ex) {
                                mResultingCurrencyEditText.setText(mResources.getString(
                                        R.string.currency_is_not_available));
                                CommonUtils.showToast(getApplicationContext(), mResources.getString(
                                        R.string.currency_is_wrong));
                            }
                        } else {
                            CommonUtils.showToast(getApplicationContext(),
                                    mResources.getString(R.string.currency_amount_absent));
                        }
                    }
                } else {
                    mCurrencyPresenter.provideCurrencies();
                }
            }
        });
    }

    /**
     * Since the list of a currencies loaded from inet does not have a RUB currency, this method
     * adds it to this list.
     */
    private CurrencyBean collectRUBCurrency() {
        CurrencyBean currencyBean = new CurrencyBean();
        currencyBean.setCharacterCode("RUB");
        currencyBean.setNumericCode(643);
        currencyBean.setValue("1");
        return currencyBean;
    }

    private void setInitialCurrencyClickListener() {
        mInitialCurrencyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mInitialCurrencyEditText.length() > 0) {
                    convertCurrency();
                } else {
                    mResultingCurrencyEditText.setText("");
                }
            }
        });
    }

    private void submitCurrenciesCharCodesToSpinners() {

        ArrayAdapter<String> currenciesAdapter;
        List<String> charCodeList = new ArrayList<>();

        CurrencyBean rubCurrencyBean = collectRUBCurrency();
        mCurrenciesContainer.getCurrenciesList().set(0, rubCurrencyBean);
        mInitialCurrencyTextView.setText(rubCurrencyBean.getCharacterCode());

        for (int i = 0; i < mCurrenciesContainer.getCurrenciesList().size(); i++) {
            charCodeList.add(mCurrenciesContainer.getCurrenciesList().get(i).getCharacterCode());
        }

        setListenersToSpinners();

        currenciesAdapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.item_spinner, charCodeList);
        currenciesAdapter.setDropDownViewResource(R.layout.item_spinner);
        mInitialCurrencySpinner.setAdapter(currenciesAdapter);
        mResultingCurrencySpinner.setAdapter(currenciesAdapter);
    }

    private void setListenersToSpinners() {
        mInitialCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mInitialCurrencyTextView.setText(mCurrenciesContainer.getCurrenciesList().get(
                        position).getValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mResultingCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mResultingCurrencyTextView.setText(mCurrenciesContainer.getCurrenciesList().get(
                        position).getValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Setting a currencies in a spinners when an application starts.
     */
    private void setInitialCurrenciesInSpinners() {
        mInitialCurrencySpinner.setSelection(USD_POSITION);
        mResultingCurrencySpinner.setSelection(RUB_POSITION);
    }

    private void setSwapCurrenciesButtonClickListener() {
        Button swapButton = (Button) findViewById(R.id.swap_button);
        swapButton.setOnClickListener(v -> {
            int swapperPosition;
            String swapperText;
            swapperPosition = mInitialCurrencySpinner.getSelectedItemPosition();
            mInitialCurrencySpinner.setSelection(mResultingCurrencySpinner.getSelectedItemPosition());
            mResultingCurrencySpinner.setSelection(swapperPosition);
            swapperText = mInitialCurrencyTextView.getText().toString();
            mInitialCurrencyTextView.setText(mResultingCurrencyTextView.getText());
            mResultingCurrencyTextView.setText(swapperText);
            if (mInitialCurrencyEditText.getText().length() > 0) {
                convertCurrency();
            } else {
                mResultingCurrencyEditText.setText("");
            }
        });
    }
}
