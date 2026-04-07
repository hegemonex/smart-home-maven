package com.solvd.smarthome.lambdas;

import com.solvd.smarthome.district.house.devices.Device;

@FunctionalInterface
public interface DeviceFilter {

    boolean test(Device device);

    default DeviceFilter and(DeviceFilter other) {
        return device -> this.test(device) && other.test(device);
    }

    default DeviceFilter or(DeviceFilter other) {
        return device -> this.test(device) || other.test(device);
    }
}

