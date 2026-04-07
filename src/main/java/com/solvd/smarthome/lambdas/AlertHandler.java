package com.solvd.smarthome.lambdas;

import com.solvd.smarthome.enums.AlertLevel;

@FunctionalInterface
public interface AlertHandler {

    void handle(String deviceName, AlertLevel level);

    default AlertHandler andAlso(AlertHandler other) {
        return (deviceName, level) -> {
            this.handle(deviceName, level);
            other.handle(deviceName, level);
        };
    }
}