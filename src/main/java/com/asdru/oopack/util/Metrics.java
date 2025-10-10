package com.asdru.oopack.util;

public class Metrics {
    private int lines, files;
    private static Metrics instance;

    public static Metrics getInstance() {
        if (instance == null) {
            instance = new Metrics();
        }
        return instance;
    }

    public static void register(){
       var metrics = getInstance();
       metrics.lines=0;
       metrics.files=0;
    }

    public static void get(){
        var metrics = getInstance();
        System.out.printf("Metrics: %n");
        System.out.printf("Lines Created: %s%n", metrics.lines);
        System.out.printf("Files Created: %s%n", metrics.files);
    }

    public static void update(String text){
        if(instance==null) return;
        var metrics = getInstance();
        metrics.lines+= text.split("\\R").length;
        metrics.files++;
    }

}
