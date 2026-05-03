package com.solvd.smarthome.parser;

import com.solvd.smarthome.district.house.SmartHome;

import java.io.File;

public interface Parser {
    SmartHome parse(File file);
}
