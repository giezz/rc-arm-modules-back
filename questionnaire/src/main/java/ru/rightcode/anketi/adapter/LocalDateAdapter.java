package ru.rightcode.anketi.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    @Override
    public LocalDate unmarshal(String value) {
        return LocalDate.parse(value, formatter);
    }

    @Override
    public String marshal(LocalDate value) {
        return formatter.format(value);
    }
}
