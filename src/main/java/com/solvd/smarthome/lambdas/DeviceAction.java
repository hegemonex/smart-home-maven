package com.solvd.smarthome.lambdas;

import com.solvd.smarthome.district.house.devices.Device;

@FunctionalInterface
public interface DeviceAction {

    String execute(Device device);

    default DeviceAction andThen(DeviceAction next) {
        return device -> {
            this.execute(device);
            return next.execute(device);
        };
    }
}