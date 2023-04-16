
package com.example.lab4;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.soap.MTOM;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
@MTOM
public interface EventService {
    @WebMethod
    EventList getEventsByDay(@WebParam(name = "date") String date);

    @WebMethod
    EventList getEventsByWeek(@WebParam(name = "week") Integer week);

    @WebMethod
    Event getEvent(@WebParam(name = "id") Long id);

    @WebMethod
    Event createEvent(@WebParam(name = "name") String name,
                      @WebParam(name = "description") String description,
                      @WebParam(name = "eventType") String eventType,
                      @WebParam(name = "eventDate") EventDate eventDate,
                      @WebParam(name = "week") Integer week,
                      @WebParam(name = "month") Integer month,
                      @WebParam(name = "year") Integer year
    );

    @WebMethod
    Event updateEvent(@WebParam(name = "id") Long id,
                      @WebParam(name = "name") String name,
                      @WebParam(name = "description") String description,
                      @WebParam(name = "eventType") String eventType,
                      @WebParam(name = "eventDate") EventDate eventDate,
                      @WebParam(name = "week") Integer week,
                      @WebParam(name = "month") Integer month,
                      @WebParam(name = "year") Integer year
    );

    @WebMethod
    DataHandler createPDF(@WebParam(name = "week") Integer week);
}
