package com.example.lab4;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.soap.MTOM;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebService(endpointInterface = "com.example.lab4.EventService")
@MTOM
@HandlerChain(file = "event-validator-handler.xml")
public class EventServiceImpl implements EventService {

    @Resource
    WebServiceContext wsctx;

    private final List<Event> events = new ArrayList<>( );

    @Override
    public EventList getEventsByDay(String date) {
        LocalDate parseDate = LocalDate.parse(date);
        EventList eventList = new EventList();
        eventList.setEvents(events.stream()
                .filter(event -> event.getEventDate().getDate().isEqual(parseDate))
                .collect(Collectors.toList()));
        return eventList ;
    }

    @Override
    public EventList getEventsByWeek(Integer week) {
        EventList eventList = new EventList();
        eventList.setEvents(events.stream()
                .filter(event -> event.getWeek().equals(week))
                .collect(Collectors.toList()));
        return eventList ;

    }

    @Override
    public Event getEvent(Long id) {
        return events.stream()
                .filter(event -> event.getId().equals(id))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Event createEvent(String name,
                             String description,
                             String eventType,
                             EventDate eventDate,
                             Integer week,
                             Integer month,
                             Integer year) {
        long id = new Random().longs(1L,1000L).findAny().orElse(10002L);
//        LocalDate parseDate = LocalDate.parse(eventDate);
        Event event = new Event(id, name, description, EventType.valueOf(eventType), eventDate, week, month, year);
        events.add(event);
        return event;
    }

    @Override
    public Event updateEvent(Long id,
                             String name,
                             String description,
                             String eventType,
                             EventDate eventDate,
                             Integer week,
                             Integer month,
                             Integer year) {
        Event event = getEvent(id);
        event.setName(name);
        event.setDescription(description);
        event.setEventType(EventType.valueOf(eventType));
        event.setEventDate(eventDate);
        event.setWeek(week);
        event.setMonth(month);
        event.setYear(year);
        return event;
    }

    @Override
    public DataHandler createPDF(Integer week) {
        EventList eventsByWeek = getEventsByWeek(week);
        return generateEventsReportPDF(eventsByWeek.getEvents());
    }

    public DataHandler generateEventsReportPDF(List<Event> events) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfPTable table = new PdfPTable(4);
            addTableHeader(table);
            for (Event event : events) {
                addTableRow(table, event);
            }
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return new DataHandler(new InputStreamDataSource(inputStream, "application/pdf"));
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Name", "Type", "Date", "Description")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addTableRow(PdfPTable table, Event event) {
        table.addCell(event.getName());
        table.addCell(event.getEventType().name());
        table.addCell(event.getEventDate().toString());
        table.addCell(event.getDescription());
    }

    private static class InputStreamDataSource implements javax.activation.DataSource {
        private final InputStream inputStream;
        private final String contentType;

        public InputStreamDataSource(InputStream inputStream, String contentType) {
            this.inputStream = inputStream;
            this.contentType = contentType;
        }

        public InputStream getInputStream() {
            return inputStream;
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Cannot write to this read-only resource");
        }

        public String getContentType() {
            return contentType;
        }

        public String getName() {
            return "InputStreamDataSource";
        }
    }

}
