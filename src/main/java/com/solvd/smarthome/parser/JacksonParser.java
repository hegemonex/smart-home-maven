package com.solvd.smarthome.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.smarthome.district.house.SmartHome;

import java.io.File;

public class JacksonParser implements Parser {

    @Override
    public SmartHome parse(File file) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return mapper.readValue(file, SmartHome.class);

        } catch (Exception e) {
            throw new RuntimeException("Jackson parsing failed", e);
        }
    }
}