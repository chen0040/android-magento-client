package com.github.chen0040.androidmagentoclient;

/**
 * Created by chen0 on 4/7/2017.
 */

public class AndroidLogger {
    public void info(String text, Object... args) {
        text = text.replace("{}", "%s");
        System.out.println(String.format(text, args));
    }
}
