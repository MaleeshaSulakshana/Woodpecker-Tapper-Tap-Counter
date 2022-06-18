package com.tapper.woodpecker_tapper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class DashboardActivity extends AppCompatActivity {

    private LinearLayout tapperLayout;
    private EditText textSetCount;
    private TextView textShowCount;
    private ImageButton btnPlayAds, btnExit;
    private Button btnClear;

    private AdView mAdView, mAdViewHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        Get components
        tapperLayout = (LinearLayout) this.findViewById(R.id.tapperLayout);
        textSetCount = (EditText) this.findViewById(R.id.textSetCount);
        textShowCount = (TextView) this.findViewById(R.id.textShowCount);
        btnPlayAds = (ImageButton) this.findViewById(R.id.btnPlayAds);
        btnExit = (ImageButton) this.findViewById(R.id.btnExit);
        btnClear = (Button) this.findViewById(R.id.btnClear);

//        mAdView = (AdView) this.findViewById(R.id.adView);


        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-8280641777282431/8584368383");

        AdView adView2 = new AdView(this);
        adView2.setAdSize(AdSize.BANNER);
        adView2.setAdUnitId("ca-app-pub-8280641777282431/2018960032");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
//                Toast.makeText(DashboardActivity.this, "initialization successfully", Toast.LENGTH_SHORT).show();

                mAdView = findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);

                mAdViewHeader = findViewById(R.id.adViewHeader);
                AdRequest adRequest2 = new AdRequest.Builder().build();
                mAdViewHeader.loadAd(adRequest2);


                mAdView.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
//                        Toast.makeText(DashboardActivity.this, "on Ad Loaded successfully", Toast.LENGTH_SHORT).show();
                        // Code to be executed when an ad finishes loading.
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
//                        Toast.makeText(DashboardActivity.this, "on Ad Failed To Load "+adError.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("************* "+ adError.toString());

                        // Code to be executed when an ad request fails.
                    }

                    @Override
                    public void onAdOpened() {
//                        Toast.makeText(DashboardActivity.this, "on Ad Opened successfully", Toast.LENGTH_SHORT).show();

                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                    }

                    @Override
                    public void onAdClicked() {
//                        Toast.makeText(DashboardActivity.this, "on Ad Clicked successfully", Toast.LENGTH_SHORT).show();

                        // Code to be executed when the user clicks on an ad.
                    }

                    @Override
                    public void onAdClosed() {
//                        Toast.makeText(DashboardActivity.this, "on Ad Closed successfully", Toast.LENGTH_SHORT).show();

                        // Code to be executed when the user is about to return
                        // to the app after tapping on an ad.
                    }
                });


                mAdViewHeader.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
//                        Toast.makeText(DashboardActivity.this, "on Ad Loaded successfully", Toast.LENGTH_SHORT).show();
                        // Code to be executed when an ad finishes loading.
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
//                        Toast.makeText(DashboardActivity.this, "on Ad Failed To Load "+adError.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("------------- "+ adError.toString());

                        // Code to be executed when an ad request fails.
                    }

                    @Override
                    public void onAdOpened() {
//                        Toast.makeText(DashboardActivity.this, "on Ad Opened successfully", Toast.LENGTH_SHORT).show();

                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                    }

                    @Override
                    public void onAdClicked() {
//                        Toast.makeText(DashboardActivity.this, "on Ad Clicked successfully", Toast.LENGTH_SHORT).show();

                        // Code to be executed when the user clicks on an ad.
                    }

                    @Override
                    public void onAdClosed() {
//                        Toast.makeText(DashboardActivity.this, "on Ad Closed successfully", Toast.LENGTH_SHORT).show();

                        // Code to be executed when the user is about to return
                        // to the app after tapping on an ad.
                    }
                });

            }
        });


        tapperLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                String txtSetCount = textSetCount.getText().toString();
                String count = textShowCount.getText().toString();

                if (txtSetCount.isEmpty() || txtSetCount.equals("") || txtSetCount.equals("0")) {
                    textSetCount.setText("1");
                    txtSetCount = "1";
                }

                if (count.equals("")) {
                    count = "1";
                }

                Integer intSetCount = Integer.parseInt(txtSetCount);
                Integer intCount = Integer.parseInt(count);
                Integer newCount = intCount + intSetCount;

                textShowCount.setText(String.valueOf(newCount));

            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textShowCount.setText("0");
            }
        });


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });


    }

    //    Hide status bar and navigation bar
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}