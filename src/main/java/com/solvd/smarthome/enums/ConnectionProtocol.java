package com.solvd.smarthome.enums;

import jakarta.xml.bind.annotation.XmlEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Comparator;

@XmlEnum
public enum ConnectionProtocol {

    WIFI_24(2.4, 50, true, 300) {
        @Override
        public String connect(String deviceName) {
            logger.info("{} connecting via WIFI_24", deviceName);
            return deviceName + " joined 2.4 GHz Wi-Fi — good range, moderate speed. "
                    + "Sending 4-way WPA2 handshake...";
        }
    },

    WIFI_5(5.0, 20, true, 1200) {
        @Override
        public String connect(String deviceName) {
            logger.info("{} connecting via WIFI_5", deviceName);
            return deviceName + " joined 5 GHz Wi-Fi — shorter range but high throughput. "
                    + "Sending WPA3 handshake...";
        }
    },

    ZIGBEE(2.4, 30, true, 0.25) {
        @Override
        public String connect(String deviceName) {
            logger.info("{} connecting via ZIGBEE", deviceName);
            return deviceName + " joined Zigbee mesh — low power, daisy-chaining with neighbours. "
                    + "Sending IEEE 802.15.4 beacon...";
        }
    },

    ZWAVE(0.9, 40, true, 0.1) {
        @Override
        public String connect(String deviceName) {
            logger.info("{} connecting via ZWAVE", deviceName);
            return deviceName + " joined Z-Wave mesh — sub-GHz band, very reliable indoors. "
                    + "Assigning Node ID in the Z-Wave network...";
        }
    },

    BLUETOOTH(2.4, 10, true, 3) {
        @Override
        public String connect(String deviceName) {
            logger.info("{} connecting via BLUETOOTH", deviceName);
            return deviceName + " paired via Bluetooth — short range, low latency. "
                    + "Exchanging PIN / passkey...";
        }
    },

    ETHERNET(0, 999, true, 10000) {
        @Override
        public String connect(String deviceName) {
            logger.info("{} connecting via ETHERNET", deviceName);
            return deviceName + " connected via Ethernet — wired, maximum reliability. "
                    + "Negotiating link speed with switch...";
        }
    };

    private static  Logger logger = LogManager.getLogger(ConnectionProtocol.class);

    static {
        logger.info("ConnectionProtocol enum loaded.");
    }

    private  double frequencyGHz;
    private  int maxRangeMeters;
    private  boolean encrypted;
    private  double typicalSpeedMbps;

    ConnectionProtocol(double frequencyGHz, int maxRangeMeters, boolean encrypted, double typicalSpeedMbps) {
        this.frequencyGHz = frequencyGHz;
        this.maxRangeMeters = maxRangeMeters;
        this.encrypted = encrypted;
        this.typicalSpeedMbps = typicalSpeedMbps;
    }

    public static ConnectionProtocol fastest() {
        return Arrays.stream(values())
                .max(Comparator.comparingDouble(ConnectionProtocol::getTypicalSpeedMbps))
                .orElse(ETHERNET);
    }

    public abstract String connect(String deviceName);

    public boolean isLongRange() {
        return maxRangeMeters > 25;
    }

    public double getFrequencyGHz() {
        return frequencyGHz;
    }

    public int getMaxRangeMeters() {
        return maxRangeMeters;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public double getTypicalSpeedMbps() {
        return typicalSpeedMbps;
    }

    @Override
    public String toString() {
        return name() +
                " (freq=" + frequencyGHz +
                " GHz, range=" + maxRangeMeters +
                "m, speed=" + typicalSpeedMbps + " Mbps)";
    }
}