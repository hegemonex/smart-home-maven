package com.solvd.smarthome.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum AlertLevel {

    INFO(0, "BLUE", false, false),
    WARNING(1, "YELLOW", false, true),
    CRITICAL(2, "ORANGE", true, true),
    EMERGENCY(3, "RED", true, true);

    private static final Logger logger = LogManager.getLogger(AlertLevel.class);

    static {
        logger.info("AlertLevel enum loaded.");
    }

    private final int severity;
    private final String colour;
    private final boolean requiresImmediateAction;
    private final boolean soundAlarm;

    AlertLevel(int severity, String colour, boolean requiresImmediateAction, boolean soundAlarm) {
        this.severity = severity;
        this.colour = colour;
        this.requiresImmediateAction = requiresImmediateAction;
        this.soundAlarm = soundAlarm;
    }

    public static AlertLevel fromSeverity(int severity) {
        for (AlertLevel level : values()) {
            if (level.severity == severity) {
                return level;
            }
        }
        return INFO;
    }

    public AlertLevel escalate() {
        int nextIndex = this.ordinal() + 1;
        AlertLevel[] levels = values();
        return nextIndex < levels.length ? levels[nextIndex] : this;
    }

    public String dispatchMessage(String deviceName) {
        if (requiresImmediateAction) {
            return "!!! [" + name() + "] IMMEDIATE RESPONSE REQUIRED — device: " + deviceName + " !!!";
        }
        return "[" + name() + "] Notification — device: " + deviceName + " (no immediate action needed)";
    }

    public int getSeverity() {
        return severity;
    }

    public String getColour() {
        return colour;
    }

    public boolean requiresImmediateAction() {
        return requiresImmediateAction;
    }

    public boolean isSoundAlarm() {
        return soundAlarm;
    }

    @Override
    public String toString() {
        return name() +
                " [severity=" + severity +
                ", colour=" + colour +
                ", alarm=" + soundAlarm + "]";
    }
}