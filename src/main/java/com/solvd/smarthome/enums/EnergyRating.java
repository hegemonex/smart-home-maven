package com.solvd.smarthome.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger logger = LogManager.getLogger(EnergyRating.class);

    private final String label;
    private final int maxKwhPerYear;
    private final BigDecimal annualSavingVsBaseline;
    private final String colour;

    static {
        logger.info("EnergyRating enum loaded.");
    }

    EnergyRating(String label, int maxKwhPerYear, BigDecimal annualSavingVsBaseline, String colour) {
        this.label = label;
        this.maxKwhPerYear = maxKwhPerYear;
        this.annualSavingVsBaseline = annualSavingVsBaseline;
        this.colour = colour;
    }

    public static EnergyRating recommend(int actualKwhPerYear) {
        EnergyRating result = Arrays.stream(values())
                .filter(r -> actualKwhPerYear <= r.maxKwhPerYear)
                .findFirst()
                .orElse(G);

        logger.debug("Recommended EnergyRating {} for {} kWh/year", result, actualKwhPerYear);
        return result;
    }

    public BigDecimal annualCost(BigDecimal pricePerKwh) {
        return pricePerKwh.multiply(BigDecimal.valueOf(maxKwhPerYear));
    }

    public boolean isSubsidyEligible() {
        return this.ordinal() <= A_PLUS.ordinal();
    }

    public String getLabel() {
        return label;
    }

    public int getMaxKwhPerYear() {
        return maxKwhPerYear;
    }

    public BigDecimal getAnnualSavingVsBaseline() {
        return annualSavingVsBaseline;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return label +
                " (≤" + maxKwhPerYear +
                " kWh/yr, colour=" + colour + ")";
    }
}