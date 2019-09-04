package com.cooksys.ftd.ticker.server;
import com.cooksys.ftd.ticker.api.StockApi;
import com.cooksys.ftd.ticker.api.StockUtils;
import com.cooksys.ftd.ticker.dto.QuoteField;
import com.cooksys.ftd.ticker.dto.QuoteRequest;

import pl.zankowski.iextrading4j.api.stocks.Quote;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

public class ClientHandler implements Runnable {

	private Socket socket;

	
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		while (true) {
	        try (
	                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
	        ) {
	            JAXBContext context = JAXBContext.newInstance(QuoteField.class, QuoteRequest.class);
	            Unmarshaller unmarshaller = context.createUnmarshaller();
	
	            // Create buffered reader and string reader to read a request from a client socket inputstream
	            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            StringReader stringReader = new StringReader(bufferedReader.readLine());
	
	            // Unmarshall stringReader to QuoteRequest object
	            QuoteRequest quoteRequest = (QuoteRequest) unmarshaller.unmarshal(stringReader);
	
	            // Fetch quotes for each
	            Set<Quote> quotes = StockApi.fetchQuotes(quoteRequest);
	
	            // Map quote results to a formatted string
	            String stringQuote = StockUtils.quotesToString(quotes, quoteRequest.getFields());
	
	            
	            // Write and flush formatted string to client socket
	            out.write(stringQuote);
	            out.flush();
	
	        } catch (IOException | JAXBException e) {
	            e.printStackTrace();
	        }
	        try {
				Thread.sleep(QuoteRequest.getInterval() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        
		}
        
	}

}
