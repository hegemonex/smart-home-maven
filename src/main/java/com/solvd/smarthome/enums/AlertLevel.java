package com.solvd.smarthome.enums;

public enum AlertLevel {

    INFO(0, "BLUE", false, false),
    WARNING(1, "YELLOW", false, true),
    CRITICAL(2, "ORANGE", true, true),
    EMERGENCY(3, "RED", true, true);

    static {
        System.out.println("[AlertLevel] Security alert level scale loaded ("
                + AlertLevel.values().length + " levels).");
    }

    private final int severity;
    private final String colour;
    private final boolean requiresImmediateAction;
    private final boolean soundAlarm;

    {
        System.out.println("[AlertLevel] Alert level constant initialising...");
    }

    AlertLevel(int severity, String colour, boolean requiresImmediateAction, boolean soundAlarm) {
        this.severity = severity;
        this.colour = colour;
        this.requiresImmediateAction = requiresImmediateAction;
        this.soundAlarm = soundAlarm;
    }

    public static AlertLevel fromSeverity(int severity) {
        for (AlertLevel level : values()) {
            if (level.severity == severity) return level;
        }
        return INFO;
    }

    public AlertLevel escalate() {
        AlertLevel[] all = values();
        int nextIndex = this.ordinal() + 1;
        return nextIndex < all.length ? all[nextIndex] : this;
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
        return name() + " [severity=" + severity + ", colour=" + colour
                + ", alarm=" + soundAlarm + "]";
    }
}
