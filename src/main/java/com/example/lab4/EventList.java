package com.example.lab4;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType
public class EventList {
    private List<Event> events;

    @XmlElement
    public List<Event> getEvents(){
        return this.events;
    }

    public void setEvents(List<Event> events){
        this.events = events;
    }
}
