package com.futurice.rxmvvmdi.services;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by osal on 3.5.2016.
 */
public class SystemMonitorService {

    public Observable<Float> getCpuUtilizationStream() {
        final Pattern pattern = Pattern.compile("\\d+");
        return Observable
                .interval(1000, TimeUnit.MILLISECONDS)
                .map(t -> executeTop())
                .filter(topOutput -> topOutput != null && topOutput.length() > 0)
                .map(topOutput -> pattern.matcher(topOutput))
                .flatMap(matcher -> {
                    List<Float> values = new ArrayList<Float>();
                    while (matcher.find()) {
                        values.add(Float.parseFloat(matcher.group()));
                    }
                    return Observable
                            .from(values)
                            .take(2)
                            .reduce(0F, (sum, next) -> sum + next);
                });
    }

    private static String executeTop() {
        java.lang.Process p = null;
        BufferedReader in = null;
        String returnString = null;
        try {
            p = Runtime.getRuntime().exec("top -n 1");
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while (returnString == null || returnString.contentEquals("")) {
                returnString = in.readLine();
            }
        } catch (IOException e) {
            Log.e("executeTop", "error in getting first line of top");
            e.printStackTrace();
        } finally {
            try {
                in.close();
                p.destroy();
            } catch (IOException e) {
                Log.e("executeTop",
                        "error in closing and destroying top process");
                e.printStackTrace();
            }
        }
        Log.d("CPU", returnString);
        return returnString;
    }
}
