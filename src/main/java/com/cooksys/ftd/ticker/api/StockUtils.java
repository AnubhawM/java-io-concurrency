package com.cooksys.ftd.ticker.api;

import com.cooksys.ftd.ticker.dto.QuoteField;
import pl.zankowski.iextrading4j.api.stocks.Quote;

import java.util.Set;

public class StockUtils {

    public static String quotesToString(Set<Quote> quotes, Set<QuoteField> fields) {
        StringBuilder quoteString = new StringBuilder();
        for (Quote quote : quotes) {
            quoteString.append("\n").append(quote.getSymbol()).append(" -");
            for (QuoteField field : fields) {
                switch (field) {
                    case HIGH:
                        quoteString.append(" High: ").append(quote.getHigh());
                        break;
                    case LOW:
                        quoteString.append(" Low: ").append(quote.getLow());
                        break;
                    case CLOSE:
                        quoteString.append(" Close: ").append(quote.getClose());
                        break;
                    case OPEN:
                        quoteString.append(" Open: ").append(quote.getOpen());
                        break;
                    case CHANGE_PERCENT:
                        quoteString.append(" Change Percent: ").append(quote.getChangePercent());
                        break;
                    case CHANGE:
                        quoteString.append(" Change: ").append(quote.getChange());
                        break;
                    case LATEST_PRICE:
                        quoteString.append(" Latest Price: ").append(quote.getLatestPrice());
                        break;
                }
            }
        }
        return quoteString.toString();
    }



}
