package com.solvd.smarthome.enums;

import java.math.BigDecimal;
import java.util.Arrays;

public enum EnergyRating {

    A_PLUS_PLUS_PLUS("A+++", 100, new BigDecimal("320.00"), "DARK_GREEN"),
    A_PLUS_PLUS("A++", 150, new BigDecimal("270.00"), "GREEN"),
    A_PLUS("A+", 220, new BigDecimal("200.00"), "LIGHT_GREEN"),
    A("A", 300, new BigDecimal("120.00"), "YELLOW_GREEN"),
    B("B", 400, new BigDecimal("50.00"), "YELLOW"),
    C("C", 500, new BigDecimal("10.00"), "ORANGE"),
    D("D", 650, BigDecimal.ZERO, "RED"),
    E("E", 800, new BigDecimal("-50.00"), "DARK_RED"),
    F("F", 1000, new BigDecimal("-120.00"), "DARK_RED"),
    G("G", 1200, new BigDecimal("-200.00"), "DARK_RED");

    static {
        System.out.println("[EnergyRating] All " + EnergyRating.values().length + " rating tiers registered.");
    }

    private final String label;
    private final int maxKwhPerYear;
    private final BigDecimal annualSavingVsBaseline;
    private final String colour;

    {
        System.out.println("[EnergyRating] Registering rating constant (instance init block)...");
    }

    EnergyRating(String label, int maxKwhPerYear, BigDecimal annualSavingVsBaseline, String colour) {
        this.label = label;
        this.maxKwhPerYear = maxKwhPerYear;
        this.annualSavingVsBaseline = annualSavingVsBaseline;
        this.colour = colour;
    }

    public static EnergyRating recommend(int actualKwhPerYear) {
        return Arrays.stream(values())
                .filter(r -> actualKwhPerYear <= r.maxKwhPerYear)
                .findFirst()
                .orElse(G);
    }

    public BigDecimal annualCost(BigDecimal pricePerKwh) {
        return pricePerKwh.multiply(new BigDecimal(maxKwhPerYear));
    }

    public boolean isSubsidyEligible() {
        return this.ordinal() <= A_PLUS.ordinal();
    }

    public String getLabel() { return label; }
    public int getMaxKwhPerYear() { return maxKwhPerYear; }
    public BigDecimal getAnnualSavingVsBaseline() { return annualSavingVsBaseline; }
    public String getcolour() { return colour; }

    @Override
    public String toString() {
        return label + " (≤" + maxKwhPerYear + " kWh/yr, colour=" + colour + ")";
    }
}