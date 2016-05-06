package com.home.ubbs.lib.theme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;

import com.home.ubbs.lib.utils.ThemeConstants;

/**
 * Created by udyatbhanu-mac on 4/30/16.
 */
public abstract class BaseThemeActivity extends AppCompatActivity {





    protected @StyleRes int currentTheme;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setup Theme
        setUpTheme(ThemeSettings.style);
        setCurrentTheme(ThemeSettings.style);
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(ThemeConstants.THEME_PREFERENCE_KEY, Context.MODE_PRIVATE);
        storeTheme(ThemeSettings.style);  //default theme
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
     */
    @Override
    public void onResume(){
        super.onResume();
        if(currentTheme!=ThemeSettings.style){
            changeTheme();
        }

    }

    private void storeTheme(@StyleRes int style){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        @StyleRes int currentTheme = sharedpreferences.getInt(ThemeConstants.CURRENT_THEME, ThemeSettings.style);
        if(currentTheme == -1){
            currentTheme = R.style.AppThemeIndigo;
            ThemeSettings.style = R.style.AppThemeIndigo;
        }
        editor.putInt(ThemeConstants.PREVIOUS_THEME, currentTheme);
        editor.putInt(ThemeConstants.CURRENT_THEME, style);
    }


    /**
     *
     * @param theme
     */
    protected void setCustomTheme(final @StyleRes int style){
        ThemeSettings.style = style;
        storeTheme(style);
        changeTheme();

    }


    /**
     *
     */
    protected void cancelTheme(){
        @StyleRes int previousTheme = sharedpreferences.getInt(ThemeConstants.PREVIOUS_THEME, ThemeSettings.style);
        setCustomTheme(previousTheme);
    }
    /**
     *
     */
    private void changeTheme(){
        Intent intent = new Intent(getBaseContext(), this.getClass());
        startActivity(intent);
        finish();
    }

    public @StyleRes int getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(@StyleRes int currentTheme) {
        this.currentTheme = currentTheme;
    }
}
