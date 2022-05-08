package com.example.juanmartinezleongreenflag;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    public String passwordRegEx = "^(?:(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*)[^\\s]{8,}$";
    public String emailRegEx = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public boolean validateStringWithRegex(String password, String regex) {

        CharSequence inputStr = password;

        Pattern pattern = Pattern.compile(regex,Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return false;
        else
            return true;
    }

    public boolean compareStrings(String string1, String string2){
        Log.d("stringC1", string1);
        Log.d("stringC2", string2);
        if (string1.equals(string2))
            return false;
        else
            return true;
    }
}
