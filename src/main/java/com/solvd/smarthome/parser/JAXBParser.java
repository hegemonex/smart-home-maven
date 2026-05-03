package com.solvd.smarthome.parser;

import com.solvd.smarthome.district.house.SmartHome;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JAXBParser implements Parser {

    @Override
    public SmartHome parse(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(SmartHome.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (SmartHome) unmarshaller.unmarshal(file);

        } catch (Exception e) {
            throw new RuntimeException("JAXB parsing failed", e);
        }
    }
}
