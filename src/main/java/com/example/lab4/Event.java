package com.example.lab4;

public class Event {
    private Long id;
    private String name;
    private String description;
    private EventType eventType;
    private EventDate eventDate;
    private Integer week;
    private Integer month;
    private Integer year;

    public Event(Long id,
                 String name,
                 String description,
                 EventType eventType,
                 EventDate eventDate,
                 Integer week,
                 Integer month,
                 Integer year) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.week = week;
        this.month = month;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public EventDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(EventDate eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
