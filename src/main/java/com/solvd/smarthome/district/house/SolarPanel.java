package com.solvd.smarthome.district.house;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.solvd.smarthome.timeadapter.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class SolarPanel {

    private String manufacturer;
    private BigDecimal outputKw;
    private int panelCount;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate installedDate;

    public SolarPanel(String manufacturer, BigDecimal outputKw, int panelCount, LocalDate installedDate) {
        this.manufacturer = manufacturer;
        this.outputKw = outputKw;
        this.panelCount = panelCount;
        this.installedDate = installedDate;
    }

    public SolarPanel() {
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getOutputKw() {
        return outputKw;
    }

    public void setOutputKw(BigDecimal outputKw) {
        this.outputKw = outputKw;
    }

    public int getPanelCount() {
        return panelCount;
    }

    public void setPanelCount(int panelCount) {
        this.panelCount = panelCount;
    }

    public LocalDate getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(LocalDate installedDate) {
        this.installedDate = installedDate;
    }

    public String solarInfo() {
        return "Solar Panel | Manufacturer: " + manufacturer + " | Output: " + outputKw + " kW | Panels: " + panelCount + " | Installed: " + installedDate;
    }
}