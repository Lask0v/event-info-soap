package com.example.lab4;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@WebService
public class LoggerHandler implements SOAPHandler<SOAPMessageContext> {
    private static final Logger LOG = Logger.getLogger(LoggerHandler.class.getName());

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!isRequest) {
            SOAPMessage soapMsg = context.getMessage();
            try {
                LOG.info("Received request headers: " + context.get(MessageContext.HTTP_REQUEST_HEADERS));
                LOG.info("Received request method: " + context.get(MessageContext.HTTP_REQUEST_METHOD));
                LOG.info("Received request operation: " + context.get(MessageContext.WSDL_OPERATION));
            } catch (Exception e) {
                LOG.severe("Error while handling SOAP message: " + e.getMessage());
            }
        }
        return true;
    }

    @Override

    public boolean handleFault(SOAPMessageContext context) {
        LOG.info("Server : handleFault()......");
        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        LOG.info("Server : getHeaders()......");
        return new HashSet<>();
    }

    @Override
    public void close(MessageContext context) {
        LOG.info("Server : close()......");
    }
}
