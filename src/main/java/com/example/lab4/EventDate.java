package com.example.lab4;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;

public class EventDate {

    @XmlElement(name = "date", required = true)
    private String date;

    public EventDate() {
    }

    public EventDate(String date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return LocalDate.parse(date);
    }

    public void setDate(String date) {
        this.date = date;
    }
}
