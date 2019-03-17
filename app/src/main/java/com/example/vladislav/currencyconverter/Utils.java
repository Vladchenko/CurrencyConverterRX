package com.example.vladislav.currencyconverter;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vladislav on 22.03.17.
 */

public class Utils {

    public static void showToast(Context context, String messageToShow) {
        (new Toast(context).makeText(
                context,
                messageToShow,
                Toast.LENGTH_LONG)).show();
    }

    public static boolean isURLValid(String URL) {
        final String URL_PATTERN =
//                "/^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?/";
        "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(URL_PATTERN);
        Matcher matcher = pattern.matcher(URL);
        return (matcher.matches());
    }

    public static boolean isFilePathValid(String filePath) {
        final String FILE_PATTERN = "([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?";
        Pattern pattern = Pattern.compile(FILE_PATTERN);
        Matcher matcher = pattern.matcher(filePath);
        return (matcher.matches());
    }

}
