package com.cooksys.ftd.ticker.client;


import com.cooksys.ftd.ticker.dto.QuoteField;
import com.cooksys.ftd.ticker.dto.QuoteRequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TickerClient {

    public static void main(String[] args) {

        // List of fields that the client is requesting on a quote
        Set<QuoteField> fields = new HashSet<>(Collections.singletonList((QuoteField.LATEST_PRICE)));

        // Stock ticker symbols client is requesting
        Set<String> symbols = new HashSet<>(Arrays.asList("NIO", "TWTR"));

        // Encapsulating request object
        QuoteRequest request = new QuoteRequest(fields, symbols);

        try (
                Socket socket = new Socket("localhost", 3000);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            JAXBContext context = JAXBContext.newInstance(QuoteField.class, QuoteRequest.class);
            Marshaller marshaller = context.createMarshaller();

            // Marshal request to stringWriter
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(request, stringWriter);

            // Write 'stringified' XML to socket
            out.println(stringWriter.toString());

            // Block and wait for a single response then exit
            boolean receivedResponse = false;
            while (!receivedResponse && socket.isConnected()) {
                String message = in.readLine();
                if (message != null) {
                    receivedResponse = true;
                    System.out.println(message);
                }
            }

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

    }

}
