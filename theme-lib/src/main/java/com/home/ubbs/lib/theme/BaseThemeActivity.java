package com.home.ubbs.lib.theme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;

import com.home.ubbs.lib.theme.ThemeSettings;

/**
 * Created by udyatbhanu-mac on 4/30/16.
 */
public abstract class BaseThemeActivity extends AppCompatActivity {

    protected static final String THEME_KEY = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setup Theme
        setUpTheme(ThemeSettings.style);
        super.onCreate(savedInstanceState);
    }


    /**
     *
     * @param style
     */
    private void setUpTheme(final @StyleRes int style){
        if(style!=-1){
            setTheme(style);
        }

    }

    /**
     *
     * @param theme
     */
    protected void setTheme(final @StyleRes int style, BaseThemeActivity activity){
        ThemeSettings.style = style;
        Intent intent = new Intent(getBaseContext(),activity.getClass());
        startActivity(intent);
        finish();
    }
}
