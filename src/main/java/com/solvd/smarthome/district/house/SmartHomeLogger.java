package com.solvd.smarthome.district.house;

public class SmartHomeLogger implements AutoCloseable {

    public SmartHomeLogger() {
        System.out.println("Logger started");
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }

    @Override
    public void close() throws Exception {
        System.out.println("CLOSED");
    }
}
