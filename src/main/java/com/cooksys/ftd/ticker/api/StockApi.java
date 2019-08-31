package com.cooksys.ftd.ticker.api;

import com.cooksys.ftd.ticker.dto.QuoteRequest;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.IEXCloudTokenBuilder;
import pl.zankowski.iextrading4j.client.IEXTradingApiVersion;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

import java.util.Set;
import java.util.stream.Collectors;

public class StockApi {

    private static Quote fetchQuote(QuoteRequestBuilder quoteRequestBuilder) {
        final IEXCloudClient cloudClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_V1_SANDBOX,
                new IEXCloudTokenBuilder()
                        .withPublishableToken("Tpk_18dfe6cebb4f41ffb219b9680f9acaf2")
                        .withSecretToken("Tsk_3eedff6f5c284e1a8b9bc16c54dd1af3")
                        .build());
        return cloudClient.executeRequest(quoteRequestBuilder.build());
    }

    public static Set<Quote> fetchQuotes(QuoteRequest request) {
        return request.getSymbols()
                .stream()
                .map(s -> fetchQuote(new QuoteRequestBuilder().withSymbol(s)))
                .collect(Collectors.toSet());
    }

}
