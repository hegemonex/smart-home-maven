package com.solvd.smarthome.records;

import com.solvd.smarthome.enums.AlertLevel;
import com.solvd.smarthome.enums.ConnectionProtocol;
import com.solvd.smarthome.enums.DeviceStatus;
import com.solvd.smarthome.enums.EnergyRating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record DeviceRecord(

        String deviceName,
        String deviceType,
        BigDecimal price,
        LocalDate installedDate,
        DeviceStatus status,
        EnergyRating energyRating,
        ConnectionProtocol protocol,
        AlertLevel lastAlertLevel,
        LocalDateTime snapshotTime

) {

    public DeviceRecord {
        if (deviceName == null || deviceName.isBlank()) {
            throw new IllegalArgumentException("DeviceRecord: deviceName must not be blank.");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("DeviceRecord: price must be zero or positive.");
        }
        if (snapshotTime == null) {
            throw new IllegalArgumentException("DeviceRecord: snapshotTime must not be null.");
        }
        if (status == null) {
            throw new IllegalArgumentException("DeviceRecord: status must not be null.");
        }
    }

    public static DeviceRecord quickSnapshot(String name, String type, BigDecimal price, LocalDate installedDate) {
        return new DeviceRecord(
                name,
                type,
                price,
                installedDate,
                DeviceStatus.ONLINE,
                EnergyRating.B,
                ConnectionProtocol.WIFI_24,
                AlertLevel.INFO,
                LocalDateTime.now()
        );
    }

    public String summary() {
        return "[" + snapshotTime.toLocalDate() + " " + snapshotTime.toLocalTime().withNano(0) + "] "
                + deviceName + " (" + deviceType + ")"
                + " | status=" + status.name()
                + " | alert=" + lastAlertLevel.name()
                + " | rating=" + energyRating.getLabel()
                + " | protocol=" + protocol.name();
    }

    public boolean needsAttention() {
        return !status.isOperational() && lastAlertLevel.getSeverity() >= AlertLevel.WARNING.getSeverity();
    }


    public DeviceRecord withStatus(DeviceStatus newStatus) {
        return new DeviceRecord(
                deviceName, deviceType, price, installedDate,
                newStatus,
                energyRating, protocol, lastAlertLevel,
                LocalDateTime.now()
        );
    }

    public DeviceRecord withEscalatedAlert() {
        return new DeviceRecord(
                deviceName, deviceType, price, installedDate,
                status, energyRating, protocol,
                lastAlertLevel.escalate(),
                LocalDateTime.now()
        );
    }
}