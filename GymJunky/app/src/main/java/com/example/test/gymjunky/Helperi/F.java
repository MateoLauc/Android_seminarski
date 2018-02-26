package com.example.test.gymjunky.Helperi;

import android.app.Activity;
import android.view.View;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Adil on 3.6.2015.
 */
public class F
{
    public static <T> T findView(View view, int id, Class<T> tClass)
    {
        final View viewById = view.findViewById(id);
        return (T) viewById;
    }

    public static <T> T findView(Activity activity, int id, Class<T> tClass)
    {
        final View viewById = activity.findViewById(id);
        return (T) viewById;
    }


}
